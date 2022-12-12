package com.example.moviesapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.filters.SmallTest
import com.example.moviesapp.model.RecentlyBrowsedTvSeries
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class RecentBrowsedTvSeriesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_app_database")
    lateinit var database: AppDatabase

    private lateinit var recentlyBrowsedTvSeriesDao: RecentlyBrowsedTvSeriesDao

    @Before
    fun setup() {
        hiltRule.inject()
        recentlyBrowsedTvSeriesDao = database.recentlyBrowsedTvSeries()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun recentBrowsedMoviesFactory() = runTest {
        val moviesCount = 20
        val recentBrowsedTvSeries = List(moviesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index,
                posterPath = null,
                name = "Test name $index",
                addedDate = Date()
            )
        }


        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(*recentBrowsedTvSeries.toTypedArray())

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, moviesCount)
        val recentBrowsedTvSeriesSortedByAddedDate =
            recentBrowsedTvSeries.sortedByDescending { tvSeries ->
                tvSeries.addedDate.time
            }

        assertThat(items).containsExactlyElementsIn(recentBrowsedTvSeriesSortedByAddedDate)
            .inOrder()
    }

    @Test
    fun addRecentlyBrowsedTvSeries() = runTest {
        val recentlyBrowsedTvSeries = RecentlyBrowsedTvSeries(
            id = 0,
            posterPath = null,
            name = "Test name",
            addedDate = Date()
        )
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(recentlyBrowsedTvSeries)

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, 1)

        assertThat(items).containsExactly(recentlyBrowsedTvSeries)
    }

    @Test
    fun addRecentlyBrowsedTvSeriesConflict() = runTest {
        val recentlyBrowsedTvSeries = RecentlyBrowsedTvSeries(
            id = 0,
            posterPath = null,
            name = "Test name",
            addedDate = Date()
        )
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(recentlyBrowsedTvSeries)

        val newRecentlyBrowsedTvSeries = RecentlyBrowsedTvSeries(
            id = 0,
            posterPath = null,
            name = "Test name new",
            addedDate = Date()
        )
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(newRecentlyBrowsedTvSeries)

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, 1)

        assertThat(items).containsExactly(newRecentlyBrowsedTvSeries)
    }

    @Test
    fun addMultipleRecentlyBrowsedTvSeries() = runTest {
        val tvSeriesCount = 20
        val recentBrowsedTvSeries = List(tvSeriesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index,
                posterPath = null,
                name = "Test name $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(*recentBrowsedTvSeries.toTypedArray())

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, tvSeriesCount)

        val recentBrowsedTvSeriesSortedByAddedDate =
            recentBrowsedTvSeries.sortedByDescending { tvSeries ->
                tvSeries.addedDate.time
            }

        assertThat(items).containsExactlyElementsIn(recentBrowsedTvSeriesSortedByAddedDate)
            .inOrder()
    }

    @Test
    fun recentBrowsedTvSeriesCount() = runTest {
        val tvSeriesCount = 20
        val recentBrowsedTvSeries = List(tvSeriesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index,
                posterPath = null,
                name = "Test name $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(*recentBrowsedTvSeries.toTypedArray())

        val count = recentlyBrowsedTvSeriesDao.recentlyBrowsedTvSeriesCount()

        assertThat(count).isEqualTo(tvSeriesCount)
    }

    @Test
    fun clearRecentBrowsedTvSeries() = runTest {
        val tvSeriesCount = 20
        val recentBrowsedTvSeries = List(tvSeriesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index,
                posterPath = null,
                name = "Test name $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(*recentBrowsedTvSeries.toTypedArray())

        recentlyBrowsedTvSeriesDao.clear()

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, tvSeriesCount)

        assertThat(items).isEmpty()
    }

    @Test
    fun limitNumberOfRecentBrowsedTvSeries() = runTest {
        val maxItems = 100
        val tvSeriesCount = 100
        val recentBrowsedTvSeries = List(tvSeriesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index,
                posterPath = null,
                name = "Test name $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(*recentBrowsedTvSeries.toTypedArray())

        val newTvSeriesCount = 50
        val newRecentBrowsedTvSeries = List(newTvSeriesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index + tvSeriesCount,
                posterPath = null,
                name = "Test title ${index + tvSeriesCount}",
                addedDate = Date()
            )
        }
        recentlyBrowsedTvSeriesDao.deleteAndAdd(
            *newRecentBrowsedTvSeries.toTypedArray(),
            maxItems = maxItems
        )

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, maxItems)

        val currentRecentBrowsedTvSeries = buildList {
            addAll(recentBrowsedTvSeries)
            addAll(newRecentBrowsedTvSeries)
        }.takeLast(maxItems)

        assertThat(items).containsExactlyElementsIn(currentRecentBrowsedTvSeries)
    }


    @Test
    fun deleteLastBrowsedTvSeries() = runTest {
        val tvSeriesCount = 20
        val recentBrowsedTvSeries = List(tvSeriesCount) { index ->
            RecentlyBrowsedTvSeries(
                id = index,
                posterPath = null,
                name = "Test name $index",
                addedDate = Date()
            )
        }

        recentlyBrowsedTvSeriesDao.addRecentlyBrowsedTvSeries(*recentBrowsedTvSeries.toTypedArray())
        recentlyBrowsedTvSeriesDao.deleteLast()

        val dataSource = recentlyBrowsedTvSeriesDao.recentBrowsedTvSeries().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, tvSeriesCount)

        val recentBrowsedTvSeriesWithoutOldest = recentBrowsedTvSeries.takeLast(tvSeriesCount - 1)

        assertThat(items).containsExactlyElementsIn(recentBrowsedTvSeriesWithoutOldest)
    }

}