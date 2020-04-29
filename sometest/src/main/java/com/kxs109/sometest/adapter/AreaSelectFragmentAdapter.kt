package com.kxs109.sometest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @Description:    地区选择页面---省市区
 * @Author:         zhh
 * @CreateDate:     2020/4/29 11:43
 */
class AreaSelectFragmentAdapter(
    fm: FragmentManager,
    behavior: Int,
    private val listFragment: List<Fragment>,
    private val listTitle: List<String>
) :
    FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment = listFragment[position]

    override fun getCount(): Int = listFragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }
}