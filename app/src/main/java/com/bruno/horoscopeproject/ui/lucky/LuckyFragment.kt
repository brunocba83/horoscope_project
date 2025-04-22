package com.bruno.horoscopeproject.ui.lucky

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.bruno.horoscopeproject.R
import com.bruno.horoscopeproject.R.*
import com.bruno.horoscopeproject.databinding.FragmentLuckyBinding
import com.bruno.horoscopeproject.ui.core.listeners.OnSwipeTouchListener
import com.bruno.horoscopeproject.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject


@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private var _binding : FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        currentLuck?.let { luck ->
            val luckText = getString(luck.text)
            binding.tvlucky.text = luckText
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvShare.setOnClickListener { shareResult(luckText) }
        }
    }

    private fun shareResult(luckText: String) {
        val sendIntent : Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, luckText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    private fun initListeners() {

        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()){
            override fun onSwipeLeft() {
                spinRoulette()
            }

            override fun onSwipeRight() {
                spinRoulette()
            }
        })

    }

    private fun spinRoulette() {

        val random = Random()
        val degree = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degree.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.ivReverse.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        binding.ivReverse.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), anim.grow_card)
        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivReverse.isVisible = false
                showPremonition()

            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        binding.ivReverse.startAnimation(growAnimation)
    }

    private fun showPremonition() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

        binding.preview.startAnimation(disappearAnimation)

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 100

        binding.prediction.startAnimation(appearAnimation)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}