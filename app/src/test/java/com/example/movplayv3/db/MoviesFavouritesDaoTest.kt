//package com.example.movplayv3.db
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.room.paging.LimitOffsetDataSource
//import androidx.test.filters.SmallTest
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.first
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import java.util.*
//import javax.inject.Inject
//import javax.inject.Named
//
//@ExperimentalCoroutinesApi
//@SmallTest
//@HiltAndroidTest
//class MoviesFavouritesDaoTest {
//
//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Inject
//    @Named("test_app_database")
//    lateinit var database: AppDatabase
//
//    private lateinit var favouritesMoviesDao: FavouritesMoviesDao
//
//    @Before
//    fun setup() {
//        hiltRule.inject()
//        favouritesMoviesDao = database.favouritesMoviesDao()
//    }
//
//    @After
//    fun teardown() {
//        database.close()
//    }
//
//    @Test
//    fun likeSingleMovie() = runTest {
//        val favouriteMovie = MovieFavourite(
//            id = 0,
//            posterPath = null,
//            title = "Test title",
//            originalTitle = "Test original title",
//            addedDate = Date()
//        )
//        favouritesMoviesDao.likeMovie(favouriteMovie)
//        val ids = favouritesMoviesDao.favouriteMoviesIds().first()
//
//        assertThat(ids).contains(favouriteMovie.id)
//    }
//
//    @Test
//    fun unlikeMovie() = runTest {
//        val favouriteMovie = MovieFavourite(
//            id = 0,
//            posterPath = null,
//            title = "Test title",
//            originalTitle = "Test original title",
//            addedDate = Date()
//        )
//        favouritesMoviesDao.likeMovie(favouriteMovie)
//        val idsAfterLike = favouritesMoviesDao.favouriteMoviesIds().first()
//
//        assertThat(idsAfterLike).contains(favouriteMovie.id)
//
//        favouritesMoviesDao.unlikeMovie(favouriteMovie.id)
//        val idsAfterUnlike = favouritesMoviesDao.favouriteMoviesIds().first()
//
//        assertThat(idsAfterUnlike).isEmpty()
//    }
//
//    @Test
//    fun likeMultipleMovies() = runTest {
//        val moviesCount = 10
//        val favouriteMovies = List(moviesCount) { index ->
//            MovieFavourite(
//                id = index,
//                posterPath = null,
//                title = "Test title $index",
//                originalTitle = "Test original title $index",
//                addedDate = Date()
//            )
//        }.toTypedArray()
//        favouritesMoviesDao.likeMovie(*favouriteMovies)
//        val ids = favouritesMoviesDao.favouriteMoviesIds().first()
//
//        assertThat(ids).containsExactlyElementsIn(ids)
//    }
//
//    @Test
//    fun addFavouriteMovieConflict() = runTest {
//        val favouriteMovie = MovieFavourite(
//            id = 0,
//            posterPath = null,
//            title = "Test title",
//            originalTitle = "Test original title",
//            addedDate = Date()
//        )
//        favouritesMoviesDao.likeMovie(favouriteMovie)
//
//        val newFavouriteMovie = MovieFavourite(
//            id = 0,
//            posterPath = null,
//            title = "Test new title",
//            originalTitle = "Test original new title",
//            addedDate = Date()
//        )
//        favouritesMoviesDao.likeMovie(newFavouriteMovie)
//
//        val dataSource = favouritesMoviesDao.favouriteMovies().run {
//            create() as LimitOffsetDataSource
//        }
//        val items = dataSource.loadRange(0, 1)
//
//        assertThat(items).containsExactly(newFavouriteMovie)
//    }
//
//    @Test
//    fun favouriteMoviesFactory() = runTest {
//        val moviesCount = 20
//        val favouriteMovies = List(moviesCount) { index ->
//            MovieFavourite(
//                id = index,
//                posterPath = null,
//                title = "Test title $index",
//                originalTitle = "Test original title $index",
//                addedDate = Date()
//            )
//        }
//        favouritesMoviesDao.likeMovie(*favouriteMovies.toTypedArray())
//
//        val dataSource = favouritesMoviesDao.favouriteMovies().create() as LimitOffsetDataSource
//        val items: List<MovieFavourite> = dataSource.loadRange(0, moviesCount)
//
//        val favouriteMoviesSortedByAddedDate = favouriteMovies.sortedByDescending { movie ->
//            movie.addedDate.time
//        }
//
//        assertThat(items).containsExactlyElementsIn(favouriteMoviesSortedByAddedDate).inOrder()
//    }
//
//    @Test
//    fun favouriteMoviesCount() = runTest {
//        val moviesCount = 10
//        val favouriteMovies = List(moviesCount) { index ->
//            MovieFavourite(
//                id = index,
//                posterPath = null,
//                title = "Test title $index",
//                originalTitle = "Test original title $index",
//                addedDate = Date()
//            )
//        }.toTypedArray()
//        favouritesMoviesDao.likeMovie(*favouriteMovies)
//        val count = favouritesMoviesDao.favouriteMoviesCount().first()
//
//        assertThat(favouriteMovies.count()).isEqualTo(count)
//    }
//
//}