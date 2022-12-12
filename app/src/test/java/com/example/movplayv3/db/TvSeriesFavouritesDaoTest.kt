package com.example.moviesapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.filters.SmallTest
import com.example.moviesapp.model.TvSeriesFavourite
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class TvSeriesFavouritesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_app_database")
    lateinit var database: AppDatabase

    private lateinit var favouriteTvSeriesDao: FavouritesTvSeriesDao

    @Before
    fun setup() {
        hiltRule.inject()
        favouriteTvSeriesDao = database.favouritesTvSeriesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun likeSingleTvSeries() = runTest {
        val favouriteTvSeries = TvSeriesFavourite(
            id = 0,
            posterPath = null,
            name = "Tv series name",
            addedDate = Date()
        )
        favouriteTvSeriesDao.likeTvSeries(favouriteTvSeries)
        val ids = favouriteTvSeriesDao.favouriteTvSeriesIds().first()

        assertThat(ids).contains(favouriteTvSeries.id)
    }

    @Test
    fun unlikeTvSeries() = runTest {
        val favouriteTvSeries = TvSeriesFavourite(
            id = 0,
            posterPath = null,
            name = "Tv series name",
            addedDate = Date()
        )
        favouriteTvSeriesDao.likeTvSeries(favouriteTvSeries)
        val idsAfterLike = favouriteTvSeriesDao.favouriteTvSeriesIds().first()

        assertThat(idsAfterLike).contains(favouriteTvSeries.id)

        favouriteTvSeriesDao.unlikeTvSeries(favouriteTvSeries.id)
        val idsAfterUnlike = favouriteTvSeriesDao.favouriteTvSeriesIds().first()

        assertThat(idsAfterUnlike).isEmpty()
    }

    @Test
    fun likeMultipleTvSeries() = runTest {
        val tvSeriesCount = 10
        val favouriteTvSeries = List(tvSeriesCount) { index ->
            TvSeriesFavourite(
                id = index,
                posterPath = null,
                name = "Tv series name $index",
                addedDate = Date()
            )
        }.toTypedArray()
        favouriteTvSeriesDao.likeTvSeries(*favouriteTvSeries)
        val ids = favouriteTvSeriesDao.favouriteTvSeriesIds().first()

        assertThat(ids).containsExactlyElementsIn(ids)
    }

    @Test
    fun addFavouriteTvSeriesConflict() = runTest {
        val favouriteTvSeries = TvSeriesFavourite(
            id = 0,
            posterPath = null,
            name = "Tv series name",
            addedDate = Date()
        )
        favouriteTvSeriesDao.likeTvSeries(favouriteTvSeries)

        val newFavouriteTvSeries = TvSeriesFavourite(
            id = 0,
            posterPath = null,
            name = "Tv series new name",
            addedDate = Date()
        )
        favouriteTvSeriesDao.likeTvSeries(newFavouriteTvSeries)

        val dataSource = favouriteTvSeriesDao.favouriteTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, 1)

        assertThat(items).containsExactly(newFavouriteTvSeries)
    }

    @Test
    fun favouriteMoviesFactory() = runTest {
        val tvSeriesCount = 20
        val favouriteTvSeries = List(tvSeriesCount) { index ->
            TvSeriesFavourite(
                id = index,
                posterPath = null,
                name = "Tv series name $index",
                addedDate = Date()
            )
        }
        favouriteTvSeriesDao.likeTvSeries(*favouriteTvSeries.toTypedArray())

        val dataSource = favouriteTvSeriesDao.favouriteTvSeries().create() as LimitOffsetDataSource
        val items: List<TvSeriesFavourite> = dataSource.loadRange(0, tvSeriesCount)

        val favouriteTvSeriesSortedByAddedDate = favouriteTvSeries.sortedByDescending { tvSeries ->
            tvSeries.addedDate.time
        }

        assertThat(items).containsExactlyElementsIn(favouriteTvSeriesSortedByAddedDate).inOrder()
    }

    @Test
    fun favouriteTvSeriesCount() = runTest {
        val tvSeriesCount = 10
        val favouriteTvSeries = List(tvSeriesCount) { index ->
            TvSeriesFavourite(
                id = index,
                posterPath = null,
                name = "Tv series name $index",
                addedDate = Date()
            )
        }.toTypedArray()
        favouriteTvSeriesDao.likeTvSeries(*favouriteTvSeries)
        val count = favouriteTvSeriesDao.favouriteTvSeriesCount().first()

        assertThat(favouriteTvSeries.count()).isEqualTo(count)
    }

}