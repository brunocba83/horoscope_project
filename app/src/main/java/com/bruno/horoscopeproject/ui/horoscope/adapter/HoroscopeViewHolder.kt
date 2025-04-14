package com.bruno.horoscopeproject.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.bruno.horoscopeproject.databinding.ItemHoroscopeBinding
import com.bruno.horoscopeproject.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvHoroscope.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = context.getText(horoscopeInfo.name)
        binding.horoscopeParent.setOnClickListener {
            startRotationAnimation(
                binding.ivHoroscope,
                functionAfterRotation = { onItemSelected(horoscopeInfo) })

        }
    }


    private fun startRotationAnimation(view: View, functionAfterRotation: () -> Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { functionAfterRotation() }
            start()
        }
    }
}