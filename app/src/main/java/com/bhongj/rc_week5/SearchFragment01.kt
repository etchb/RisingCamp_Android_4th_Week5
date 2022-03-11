package com.bhongj.rc_week5

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bhongj.rc_week5.databinding.FragmentSearch01Binding

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment01.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment01 : Fragment() {
    private lateinit var _binding: FragmentSearch01Binding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AdResourseData.add(R.drawable.ad1)
        AdResourseData.add(R.drawable.ad2)
        AdResourseData.add(R.drawable.ad3)
        AdResourseData.add(R.drawable.ad4)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearch01Binding.inflate(layoutInflater, container, false)

        binding.txtRegion.paintFlags = binding.txtRegion.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        val pagerAdapter = AdSlidePagerAdapter(requireActivity())

        val mPager = binding.vpAd
        mPager.adapter = pagerAdapter
        mPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val mIndicator = binding.vpAdIndi
        mIndicator.setViewPager(mPager)
        mIndicator.createIndicators(pagerAdapter.itemCount, 0)

        mPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffsetPixels == 0) {
                    mPager.currentItem = position
                }
                Log.d("TEST", "DDD")
                Log.d("TEST", position.toString())
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mIndicator.animatePageSelected(position % pagerAdapter.itemCount)
                Log.d("TEST", "SSS")
            }
        })

        val talkListRcyView = binding.rcvFood
        talkListRcyView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private inner class AdSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = AdResourseData.size

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                in 0 until this.itemCount -> {
                    adSlideFragment(AdResourseData[position])
                }
                else -> adSlideFragment(R.drawable.ad1)
            }
        }
    }
}