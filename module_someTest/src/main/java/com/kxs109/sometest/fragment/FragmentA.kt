package com.kxs109.sometest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kxs109.commonlib.config.utils.ext.logE
import com.kxs109.sometest.R
import com.kxs109.sometest.adapter.MyPageAdapterChild
import kotlinx.android.synthetic.main.some_test_lazy_load_activity.*

class FragmentA:Fragment() {
    val fragmentList = mutableListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.some_test_fragment_a,container,false)
    }

    override fun onResume() {
        (this.javaClass.simpleName+"--onResume").logE()
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (this.javaClass.simpleName+"--onCreate").logE()

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (this.javaClass.simpleName+"--onViewCreated").logE()
        fragmentList.add(FragmentChildA())
        fragmentList.add(FragmentChildB())
        fragmentList.add(FragmentChildC())
        fragmentList.add(FragmentChildD())
        vp.adapter= MyPageAdapterChild(fragmentList, this.childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                ("child---position:"+position).logE()
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (this.javaClass.simpleName+"--onActivityCreated").logE()

        super.onActivityCreated(savedInstanceState)
    }
    override fun onDestroy() {
        (this.javaClass.simpleName+"--onDestroy").logE()

        super.onDestroy()
    }

    override fun onDestroyView() {
        (this.javaClass.simpleName+"--onDestroyView").logE()

        super.onDestroyView()
    }
}