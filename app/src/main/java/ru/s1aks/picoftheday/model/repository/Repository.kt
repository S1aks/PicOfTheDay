package ru.s1aks.picoftheday.model.repository

interface Repository {

    fun getRetrofitImpl(): PictureOfTheDayAPI
    fun saveWorkNoteToDB(dbResponseData: DBResponseData)
    fun getAllWorkListFromDB(): List<DBResponseData>
}