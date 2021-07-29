package ru.s1aks.picoftheday.model.repository

interface Repository {

    fun getRetrofitImpl(): PictureOfTheDayAPI
}