package com.example.movplayv3.data.local.db.tvshow

import androidx.room.*
import com.example.movplayv3.data.model.tvshow.TvShowDetailsRemoteKey
import com.example.movplayv3.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsDetailsRemoteKeysDao {
    @Query("SELECT * FROM TvShowDetailsRemoteKey WHERE language=:language LIMIT 1")
    suspend fun getRemoteKey(language: String): TvShowDetailsRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: TvShowDetailsRemoteKey)

    @Query("DELETE FROM TvShowDetailsRemoteKey WHERE language=:language")
    suspend fun deleteKeys(language: String)
}