package com.example.movplayv3.data.paging

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import com.example.movplayv3.data.model.Config
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import com.example.movplayv3.data.remote.api.others.TmdbOthersApiHelper
import com.example.movplayv3.data.remote.api.tvshow.TmdbTvShowsApiHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ConfigDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val apiTvShowHelper: TmdbTvShowsApiHelper,
    private val apiOtherHelper: TmdbOthersApiHelper
) {
    private val _config: MutableStateFlow<Config?> = MutableStateFlow(null)

    @SuppressLint("QueryPermissionsNeeded")
    val speechToTextAvailable: Flow<Boolean> = flow {
        val packageManager = context.packageManager
        val activities: List<*> = packageManager.queryIntentActivities(
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
            0
        )

        emit(activities.isNotEmpty())
    }.flowOn(defaultDispatcher)
}