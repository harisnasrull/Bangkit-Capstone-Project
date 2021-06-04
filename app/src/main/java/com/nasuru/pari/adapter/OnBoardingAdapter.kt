package com.nasuru.pari.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasuru.pari.databinding.OnboardingItemBinding
import com.nasuru.pari.model.OnBoardingItem

class OnBoardingAdapter(private val onBoardingItem: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {


    inner class OnBoardingViewHolder(val binding: OnboardingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(onBoardingItem: OnBoardingItem): Unit {
            binding.apply {
                binding.onboardingTitle.text = onBoardingItem.title
                binding.onboardingImage.setImageResource(onBoardingItem.image)
                binding.onboardingDesc.text = onBoardingItem.desc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val view = OnboardingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingItem[position])
    }

    override fun getItemCount(): Int = onBoardingItem.size
}