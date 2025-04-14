package com.bruno.horoscopeproject.ui.horoscope

import androidx.lifecycle.ViewModel
import com.bruno.horoscopeproject.data.providers.HoroscopeProvider
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) :
    ViewModel() {

    private var _stateHoroscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val stateHoroscope: StateFlow<List<HoroscopeInfo>> = _stateHoroscope

    init {
        _stateHoroscope.value =
            horoscopeProvider.getHoroscope()
    }


}