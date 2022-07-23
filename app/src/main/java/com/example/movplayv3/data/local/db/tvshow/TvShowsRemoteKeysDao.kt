package com.example.movplayv3.data.local.db.tvshow

import androidx.room.*
import com.example.movplayv3.data.model.tvshow.TvShowEntityType
import com.example.movplayv3.data.model.tvshow.TvShowsRemoteKeys
import com.example.movplayv3.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsRemoteKeysDao {
    @Query("SELECT * FROM TvShowsRemoteKeys WHERE type=:type AND language=:language")
    suspend fun getRemoteKey(type: TvShowEntityType, language: String): TvShowsRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: TvShowsRemoteKeys)

    @Query("DELETE FROM TvShowsRemoteKeys WHERE type=:type AND language=:language")
    suspend fun deleteRemoteKeysOfType(type: TvShowEntityType, language: String)
}