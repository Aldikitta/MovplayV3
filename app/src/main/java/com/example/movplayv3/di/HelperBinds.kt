package com.example.movplayv3.di

import com.example.movplayv3.utils.TextRecognitionHelper
import com.example.movplayv3.utils.TextRecognitionHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HelperBinds {
    @Binds
    fun provideTextRecognitionHelper(impl: TextRecognitionHelperImpl): TextRecognitionHelper
}