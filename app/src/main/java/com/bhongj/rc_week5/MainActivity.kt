package com.bhongj.rc_week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bhongj.rc_week5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.itemIconTintList = null

        initAdapter()
        initBottomNavigation()
    }

    private fun initAdapter() {
        //Adapter 안에 ViewPager2 상에 띄워줄 fragmentList 생성
        val fragmentList = listOf(SearchFragment01(), DiscountFragment02(), IssueFragment03(), MyProfileFragment04())

        //ViewPagerAdapter 초기화
        mainViewPagerAdapter = MainViewPagerAdapter(this)
        mainViewPagerAdapter.fragments.addAll(fragmentList)

        //ViewPager2와 Adapter 연동
        binding.vpMain.adapter = mainViewPagerAdapter

        binding.vpMain.isUserInputEnabled = false
    }

    private fun initBottomNavigation() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                //ViewPager2의 페이지 중 하나가 선택된 경우에 그에 대응하는 하단탭 체크
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {	//체크된 item의 Resource id값으로 어떤 아이템이 선택되었는지 구분
                R.id.btm_item_search -> {
                    binding.vpMain.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.btm_item_discount -> {
                    binding.vpMain.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.btm_item_issue -> {
                    binding.vpMain.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.btm_item_my_profile -> {
                    binding.vpMain.currentItem = FORTH_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
        const val FORTH_FRAGMENT = 3
    }
}