package com.bruno.horoscopeproject.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno.horoscopeproject.domain.model.HoroscopeModel
import com.bruno.horoscopeproject.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    private lateinit var horoscopeModel : HoroscopeModel

    fun getHoroscope(sign: HoroscopeModel) {
        horoscopeModel = sign
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            withContext(Dispatchers.IO) {
                val result = getPredictionUseCase(sign.name)
                if (result != null ) {
                    _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscopeModel)
                } else {
                    _state.value = HoroscopeDetailState.Error("Ha ocurrido un error")
                }
            }
        }
    }


}