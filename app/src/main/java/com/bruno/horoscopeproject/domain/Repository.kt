package com.bruno.horoscopeproject.domain

import com.bruno.horoscopeproject.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign : String) : PredictionModel?
}