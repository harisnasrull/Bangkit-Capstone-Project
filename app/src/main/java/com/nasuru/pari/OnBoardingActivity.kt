package com.nasuru.pari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.nasuru.pari.adapter.OnBoardingAdapter
import com.nasuru.pari.databinding.ActivityOnBoardingBinding
import com.nasuru.pari.model.OnBoardingItem

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var indicatorTab: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnBoardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnBoardingItems() {
        onBoardingAdapter = OnBoardingAdapter(
            listOf(
                OnBoardingItem(
                    image = R.drawable.oboarding1,
                    title = "Aplikasi Untuk Petani",
                    desc = "Melalui aplikasi  ini kami ingin \n" +
                            "membantu petani padi untuk \n" +
                            "mendeteksi lebih awal permasalahan \n" +
                            "pada tanaman mereka",
                ),
                OnBoardingItem(
                    image = R.drawable.oboarding2,
                    title = "Cukup foto sehelai daun",
                    desc = "Ambil foto sampel daun padi\n" +
                            "dan aplikasi akan memrosesnya\n" +
                            "secara otomatis",
                )
            )
        )
        val onBoardingViewPager = binding.onboardingViewPager
        onBoardingViewPager.adapter = onBoardingAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.buttonNext.setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingAdapter.itemCount) {
                onBoardingViewPager.currentItem += 1
            }
            else {
                navigateMain()
            }
        }
        binding.buttonSkip.setOnClickListener {
            navigateMain()
        }
    }

    private fun setupIndicators() {
        indicatorTab = binding.indicator
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParameter: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParameter.setMargins(0, 0, 0, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParameter
                indicatorTab.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorTab.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorTab.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    private fun navigateMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}