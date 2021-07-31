package ru.s1aks.picoftheday.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.s1aks.picoftheday.model.repository.PODRetrofitImpl
import ru.s1aks.picoftheday.model.repository.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.s1aks.picoftheday.BuildConfig
import ru.s1aks.picoftheday.model.PictureOfTheDayData
import ru.s1aks.picoftheday.model.repository.Repository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(
    private val retrofitImpl: Repository = PODRetrofitImpl()
) : ViewModel(), LifecycleObserver {
    val liveData: MutableLiveData<PictureOfTheDayData> = MutableLiveData()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(key: Int): LiveData<PictureOfTheDayData> {
        val currentDateTime = LocalDateTime.now()
        when (key) {
            0 -> sendServerRequest(currentDateTime)
            1 -> sendServerRequest(currentDateTime.minusDays(1))
            2 -> sendServerRequest(currentDateTime.minusDays(2))
        }

        return liveData
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendServerRequest(date: LocalDateTime) {
        liveData.value = PictureOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey, date.format(DateTimeFormatter.ISO_DATE)).enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveData.value =
                            PictureOfTheDayData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveData.value =
                                PictureOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            liveData.value =
                                PictureOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveData.value = PictureOfTheDayData.Error(t)
                }
            })
        }
    }
}