package com.bhongj.rc_week5

import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bhongj.rc_week5.databinding.FragmentSearch01Binding
import com.bhongj.rc_week5.model.RestrntData


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

        for (len in 1..RestrntData.size/6) {
            var idx = 0
            RestrntData[(len-1)*6+idx].RATE = (((Math.random() * 30).toInt() + 21).toFloat()/10f)
            RestrntData[(len-1)*6+idx++].PIC = R.drawable.food1
            RestrntData[(len-1)*6+idx].RATE = (((Math.random() * 30).toInt() + 21).toFloat()/10f)
            RestrntData[(len-1)*6+idx++].PIC = R.drawable.food2
            RestrntData[(len-1)*6+idx].RATE = (((Math.random() * 30).toInt() + 21).toFloat()/10f)
            RestrntData[(len-1)*6+idx++].PIC = R.drawable.food3
            RestrntData[(len-1)*6+idx].RATE = (((Math.random() * 30).toInt() + 21).toFloat()/10f)
            RestrntData[(len-1)*6+idx++].PIC = R.drawable.food4
            RestrntData[(len-1)*6+idx].RATE = (((Math.random() * 30).toInt() + 21).toFloat()/10f)
            RestrntData[(len-1)*6+idx++].PIC = R.drawable.food5
            RestrntData[(len-1)*6+idx].RATE = (((Math.random() * 30).toInt() + 21).toFloat()/10f)
            RestrntData[(len-1)*6+idx++].PIC = R.drawable.food6
        }
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

        val RestrntDataFil = RestrntData.filter { it.SIGNGU_NM == "화성시" }.toMutableList()
        val adapter = MainFoodAdapter(RestrntDataFil)
        val foodRcyView = binding.rcvFood
        foodRcyView.layoutManager = GridLayoutManager(context, 2)
        foodRcyView.setHasFixedSize(true)
        foodRcyView.adapter = adapter

        val itemDecoration = PhOffsetItemDecoration(0)
        foodRcyView.addItemDecoration(itemDecoration)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
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

    class PhOffsetItemDecoration(private val mPadding: Int) : ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
            super.getItemOffsets(outRect, itemPosition, parent)
            outRect.top = mPadding
            outRect.bottom = mPadding
            outRect.left = mPadding
            outRect.right = mPadding
        }
    }
}