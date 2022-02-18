package ru.s1aks.picoftheday.model

import ru.s1aks.picoftheday.model.repository.DBResponseData

sealed class WorkNoteData {
    data class Success(val dbResponseData: DBResponseData) : WorkNoteData()
    data class Error(val error: Throwable) : WorkNoteData()
    data class Loading(val progress: Int?) : WorkNoteData()
}