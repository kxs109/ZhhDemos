package com.kxs109.sometest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPageAdapterChild : FragmentPagerAdapter {
     var fragmentList: MutableList<Fragment>

    constructor(
        fragmentList: MutableList<Fragment>,
        supportChildFragmentManager: FragmentManager,
        behavior: Int
    ) : super(
        supportChildFragmentManager,
        behavior
    ) {
        this.fragmentList = fragmentList
    }

    override fun getItem(position: Int): Fragment {
       return fragmentList[position]
    }

    override fun getCount(): Int {
       return fragmentList.size
    }

}