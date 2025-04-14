package com.bruno.horoscopeproject.data.providers

import com.bruno.horoscopeproject.domain.model.HoroscopeInfo
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {

    fun getHoroscope(): List<HoroscopeInfo> {
        return listOf(
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
}