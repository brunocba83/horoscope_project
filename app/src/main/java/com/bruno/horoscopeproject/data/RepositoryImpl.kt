package com.bruno.horoscopeproject.data

import android.util.Log
import com.bruno.horoscopeproject.data.network.HoroscopeApiService
import com.bruno.horoscopeproject.domain.Repository
import com.bruno.horoscopeproject.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val horoscopeApiService: HoroscopeApiService) :
    Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching {
            horoscopeApiService.getHoroscope(sign)
        }.onSuccess { return it.toDomain() }
        .onFailure { Log.i("horoscopeApp", "Ocurrio un error ${it.message}") }
        return null
    }
}