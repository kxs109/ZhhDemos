package com.kxs109.sometest.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.kxs109.commonlib.config.utils.ext.logE
import com.kxs109.mvvmbase.BaseActivity
import com.kxs109.sometest.R
import com.kxs109.sometest.adapter.MyPageAdapter
import com.kxs109.sometest.fragment.FragmentA
import com.kxs109.sometest.fragment.FragmentB
import com.kxs109.sometest.fragment.FragmentC
import com.kxs109.sometest.fragment.FragmentD
import com.kxs109.sometest.service.MixService
import kotlinx.android.synthetic.main.some_test_lazy_load_activity.*

/**
  *
  * @Author: zhh
  * @CreateDate: 2020/10/14 10:36 PM
  * @Description:    测试懒加载
 */
class TestLazyLoadActivity : BaseActivity() {
    val fragmentList = mutableListOf<Fragment>()
    override fun initData() {

    }

    override fun initView() {
        fragmentList.add(FragmentA())
        fragmentList.add(FragmentB())
        fragmentList.add(FragmentC())
        fragmentList.add(FragmentD())
        vp.adapter=MyPageAdapter(fragmentList,supportFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        vp.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                ("position:"+position).logE()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun getResLayout(): Int = R.layout.some_test_lazy_load_activity

}