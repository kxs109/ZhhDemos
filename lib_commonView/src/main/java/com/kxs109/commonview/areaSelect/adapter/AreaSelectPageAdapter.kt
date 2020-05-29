package com.kxs109.commonview.areaSelect.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
 /**
  * @Description:    省市区pageAdapter
  * @Author:         zhh
  * @CreateDate:     2020/4/28 17:33
  */
class AreaSelectPageAdapter(private val mListView: List<View>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mListView[position])
        return mListView[position]
    }

    override fun getCount(): Int {
        return mListView.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mListView[position])
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}
