package com.example.movplayv3.model

sealed class PresentableItemState {
    object Loading : PresentableItemState()
    object Error : PresentableItemState()
    data class Result(val presentable: Presentable) : PresentableItemState()
}
