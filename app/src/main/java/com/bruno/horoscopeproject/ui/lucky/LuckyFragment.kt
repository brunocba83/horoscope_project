package com.bruno.horoscopeproject.ui.lucky

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bruno.horoscopeproject.R
import com.bruno.horoscopeproject.databinding.FragmentLuckyBinding


class LuckyFragment : Fragment() {

    private var _binding : FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}