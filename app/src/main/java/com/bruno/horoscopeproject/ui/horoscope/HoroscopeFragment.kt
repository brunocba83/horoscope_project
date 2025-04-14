package com.bruno.horoscopeproject.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruno.horoscopeproject.databinding.FragmentHoroscopeBinding
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo
import com.bruno.horoscopeproject.domain.model.HoroscopeModel
import com.bruno.horoscopeproject.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterHoroscope: HoroscopeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initUIState()
    }

    private fun initRecyclerView() {
        adapterHoroscope = HoroscopeAdapter (onItemSelected = {

            val type = when(it) {
                HoroscopeInfo.Acuario -> HoroscopeModel.Acuario
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricornio -> HoroscopeModel.Capricornio
                HoroscopeInfo.Escorpio -> HoroscopeModel.Escorpio
                HoroscopeInfo.Geminis -> HoroscopeModel.Geminis
                HoroscopeInfo.Leo -> HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Piscis -> HoroscopeModel.Piscis
                HoroscopeInfo.Sagitario -> HoroscopeModel.Sagitario
                HoroscopeInfo.Tauro -> HoroscopeModel.Tauro
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )

        })

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapterHoroscope
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.stateHoroscope.collect {
                    adapterHoroscope.updateList(it)

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun onSignSelected() {

    }


}