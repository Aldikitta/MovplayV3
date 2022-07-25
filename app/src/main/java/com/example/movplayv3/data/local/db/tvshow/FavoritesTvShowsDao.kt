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
    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeTvShow(vararg tvShowDetails: TvShowFavorite)

    @Query("DELETE FROM TvShowFavorite WHERE id = :tvShowId")
    suspend fun unlikeTvShow(tvShowId: Int)

    @Query("SELECT id FROM TvShowFavorite")
    fun favoriteTvShowIds(): Flow<List<Int>>

    @Query("SELECT COUNT(id) FROM TvShowFavorite")
    fun favoriteTvShowCount(): Flow<Int>
}