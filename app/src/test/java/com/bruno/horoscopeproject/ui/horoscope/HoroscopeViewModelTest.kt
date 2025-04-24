package com.bruno.horoscopeproject.ui.horoscope

import com.bruno.horoscopeproject.data.providers.HoroscopeProvider
import com.bruno.horoscopeproject.motherObject.HoroscopeMotherObject
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewmodel is created horoscope are loaded`() {
        every {
            horoscopeProvider.getHoroscope()
        } returns HoroscopeMotherObject.horoscopeInfoList

        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.stateHoroscope.value

        assertTrue(horoscopes.isNotEmpty())

    }


}