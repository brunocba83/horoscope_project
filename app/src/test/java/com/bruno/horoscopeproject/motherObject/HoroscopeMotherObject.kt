package com.bruno.horoscopeproject.motherObject

import com.bruno.horoscopeproject.data.network.response.PredictionResponse
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Acuario
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Aries
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Cancer
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Capricornio
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Escorpio
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Geminis
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Leo
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Libra
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Piscis
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Sagitario
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Tauro
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.Virgo

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "texto", "tauro")

    val horoscopeInfoList = listOf(
        Aries,
        Tauro,
        Geminis,
        Cancer,
        Leo,
        Virgo,
        Libra,
        Escorpio,
        Sagitario,
        Capricornio,
        Acuario,
        Piscis
    )
}