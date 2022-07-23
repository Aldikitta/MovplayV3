package com.example.movplayv3.data.local.db.tvshow

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movplayv3.data.model.tvshow.TvShowFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesTvShowsDao {
    @Query("SELECT * FROM TvShowFavorite ORDER BY added_date DESC ")
    fun favouriteTvShows(): DataSource.Factory<Int, TvShowFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeTvShow(vararg tvSeriesDetails: TvShowFavorite)
//
//    @Query("DELETE FROM TvSeriesFavourite WHERE id = :tvSeriesId")
//    suspend fun unlikeTvSeries(tvSeriesId: Int)
//
//    @Query("SELECT id FROM TvSeriesFavourite")
//    fun favouriteTvSeriesIds(): Flow<List<Int>>
//
//    @Query("SELECT COUNT(id) FROM TvSeriesFavourite")
//    fun favouriteTvSeriesCount(): Flow<Int>
}