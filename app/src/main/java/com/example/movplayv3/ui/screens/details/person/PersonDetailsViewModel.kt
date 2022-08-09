package com.example.movplayv3.ui.screens.details.person

import androidx.lifecycle.SavedStateHandle
import com.example.movplayv3.BaseViewModel
import com.example.movplayv3.domain.usecase.GetCombinedCreditsUseCaseImpl
import com.example.movplayv3.domain.usecase.GetDeviceLanguageUseCaseImpl
import com.example.movplayv3.domain.usecase.GetPersonDetailsUseCaseImpl
import com.example.movplayv3.domain.usecase.GetPersonExternalIdsUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getPersonDetailsUseCase: GetPersonDetailsUseCaseImpl,
    private val getCombinedCreditsUseCase: GetCombinedCreditsUseCaseImpl,
    private val getPersonExternalIdsUseCase: GetPersonExternalIdsUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
//    private val navArgs: PersonDetailsScreenArgs =
}