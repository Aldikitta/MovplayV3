package com.example.moviesapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.filters.SmallTest
import com.example.moviesapp.model.RecentlyBrowsedMovie
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
class RecentBrowsedMoviesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_app_database")
    lateinit var database: AppDatabase

    private lateinit var recentlyBrowsedMoviesDao: RecentlyBrowsedMoviesDao

    @Before
    fun setup() {
        hiltRule.inject()
        recentlyBrowsedMoviesDao = database.recentlyBrowsedMovies()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun recentBrowsedMoviesFactory() = runTest {
        val moviesCount = 20
        val recentBrowsedMovies = List(moviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index,
                posterPath = null,
                title = "Test title $index",
                addedDate = Date()
            )
        }


        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(*recentBrowsedMovies.toTypedArray())

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, moviesCount)

        val recentBrowsedMoviesSortedByAddedDate = recentBrowsedMovies.sortedByDescending { movie ->
            movie.addedDate.time
        }

        assertThat(items).containsExactlyElementsIn(recentBrowsedMoviesSortedByAddedDate).inOrder()
    }

    @Test
    fun addRecentlyBrowsedMovie() = runTest {
        val recentlyBrowsedMovie = RecentlyBrowsedMovie(
            id = 0,
            posterPath = null,
            title = "Test title",
            addedDate = Date()
        )
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(recentlyBrowsedMovie)

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, 1)

        assertThat(items).containsExactly(recentlyBrowsedMovie)
    }

    @Test
    fun addRecentlyBrowsedMovieConflict() = runTest {
        val recentlyBrowsedMovie = RecentlyBrowsedMovie(
            id = 0,
            posterPath = null,
            title = "Test title",
            addedDate = Date()
        )
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(recentlyBrowsedMovie)

        val newRecentlyBrowsedMovie = RecentlyBrowsedMovie(
            id = 0,
            posterPath = null,
            title = "Test title new",
            addedDate = Date()
        )
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(newRecentlyBrowsedMovie)

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, 1)

        assertThat(items).containsExactly(newRecentlyBrowsedMovie)
    }

    @Test
    fun addRecentlyBrowsedMovies() = runTest {
        val moviesCount = 20
        val recentBrowsedMovies = List(moviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index,
                posterPath = null,
                title = "Test title $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(*recentBrowsedMovies.toTypedArray())

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, moviesCount)

        val recentBrowsedMoviesSortedByAddedDate = recentBrowsedMovies.sortedByDescending { movie ->
            movie.addedDate.time
        }

        assertThat(items).containsExactlyElementsIn(recentBrowsedMoviesSortedByAddedDate).inOrder()
    }

    @Test
    fun recentBrowsedMovieCount() = runTest {
        val moviesCount = 20
        val recentBrowsedMovies = List(moviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index,
                posterPath = null,
                title = "Test title $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(*recentBrowsedMovies.toTypedArray())

        val count = recentlyBrowsedMoviesDao.recentlyBrowsedMovieCount()

        assertThat(count).isEqualTo(moviesCount)
    }

    @Test
    fun clearRecentBrowsedMovies() = runTest {
        val moviesCount = 20
        val recentBrowsedMovies = List(moviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index,
                posterPath = null,
                title = "Test title $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(*recentBrowsedMovies.toTypedArray())

        recentlyBrowsedMoviesDao.clear()

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, moviesCount)

        assertThat(items).isEmpty()
    }

    @Test
    fun limitNumberOfRecentBrowsedMovies() = runTest {
        val maxItems = 100
        val moviesCount = 100
        val recentBrowsedMovies = List(moviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index,
                posterPath = null,
                title = "Test title $index",
                addedDate = Date()
            )
        }
        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(*recentBrowsedMovies.toTypedArray())

        val newMoviesCount = 50
        val newRecentBrowsedMovies = List(newMoviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index + moviesCount,
                posterPath = null,
                title = "Test title ${index + moviesCount}",
                addedDate = Date()
            )
        }
        recentlyBrowsedMoviesDao.deleteAndAdd(
            *newRecentBrowsedMovies.toTypedArray(),
            maxItems = maxItems
        )

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, maxItems)

        val currentRecentBrowsedMovies = buildList {
            addAll(recentBrowsedMovies)
            addAll(newRecentBrowsedMovies)
        }.takeLast(maxItems)

        assertThat(items).containsExactlyElementsIn(currentRecentBrowsedMovies)
    }


    @Test
    fun deleteLastBrowsedMovie() = runTest {
        val moviesCount = 20
        val recentBrowsedMovies = List(moviesCount) { index ->
            RecentlyBrowsedMovie(
                id = index,
                posterPath = null,
                title = "Test title $index",
                addedDate = Date()
            )
        }

        recentlyBrowsedMoviesDao.addRecentlyBrowsedMovie(*recentBrowsedMovies.toTypedArray())
        recentlyBrowsedMoviesDao.deleteLast()

        val dataSource = recentlyBrowsedMoviesDao.recentBrowsedMovie().run {
            create() as LimitOffsetDataSource
        }
        val items = dataSource.loadRange(0, moviesCount)

        val recentBrowsedMoviesWithoutOldest = recentBrowsedMovies.takeLast(moviesCount - 1)

        assertThat(items).containsExactlyElementsIn(recentBrowsedMoviesWithoutOldest)
    }

}