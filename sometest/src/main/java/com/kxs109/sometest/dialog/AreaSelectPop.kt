package com.kxs109.sometest.dialog

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.eaglesoul.commonlibrary.utils.ToastUtils
import com.eaglesoul.commonlibrary.utils.ext.screenHeight
import com.eaglesoul.commonlibrary.utils.ext.screenWidth
import com.eaglesoul.commonview.R
import com.kxs109.sometest.adapter.AreaSelectAdapter
import com.eaglesoul.commonview.adapter.AreaSelectPageAdapter
import com.kxs109.sometest.model.Province
import com.flyco.tablayout.SlidingTabLayout
import razerdp.basepopup.BasePopupWindow

/**
 * @Description:  地区选择弹框
 * @Author:         zhh
 * @CreateDate:     2020/4/28 14:14
 */
class AreaSelectPop(
    context: Activity,
    fm: FragmentManager,
    listProvince: MutableList<Province>?,
    listCity: MutableList<MutableList<Province>>?,
    listArea: MutableList<MutableList<MutableList<Province>>>?
) : BasePopupWindow(context) {
    var mSelectProvince: String? = null
    var mSelectCity: String? = null
    var mSelectArea: String? = null

    init {
        popupGravity = Gravity.BOTTOM
        setBackground(0)
        width = context.screenWidth
        height = context.screenHeight * 0.75.toInt()
        setOutSideDismiss(true)
        isOutSideTouchable = true
        setBackPressEnable(true)
        setBackgroundColor(Color.parseColor("#aa000000"))

        val vp = findViewById<ViewPager>(R.id.vp_area_selection)
        val tab = findViewById<SlidingTabLayout>(R.id.tab_area_select)
        val childViewProvince = View.inflate(context, R.layout.fragment_list_area, null)
        val childViewCity = View.inflate(context, R.layout.fragment_list_area, null)
        val childViewArea = View.inflate(context, R.layout.fragment_list_area, null)
        val rcv = childViewProvince.findViewById<RecyclerView>(R.id.rcv_pop_area_selection)
        rcv.layoutManager = LinearLayoutManager(context)
        val _adapterProvince = AreaSelectAdapter()
        _adapterProvince.setNewInstance(listProvince)
        _adapterProvince.setOnItemClickListener { adapter, view, position ->
            _adapterProvince.setSelect(position)
        }
        rcv.adapter = _adapterProvince
        /////////////////////////////////////////////
        val rcv2 = childViewCity.findViewById<RecyclerView>(R.id.rcv_pop_area_selection)
        rcv2.setBackgroundColor(context.resources.getColor(R.color.color_f27e88))
        val _adapterCity = AreaSelectAdapter()
        _adapterCity.setNewInstance(listProvince)
        _adapterCity.setOnItemClickListener { adapter, view, position ->
            _adapterCity.setSelect(position)
        }
        rcv2.layoutManager = LinearLayoutManager(context)
        rcv2.adapter = _adapterCity
        /////////////////////////////////////////////
        val rcv3 = childViewArea.findViewById<RecyclerView>(R.id.rcv_pop_area_selection)
        rcv3.setBackgroundColor(context.resources.getColor(R.color.color_009944))
        val _adapterArea = AreaSelectAdapter()
        _adapterArea.setNewInstance(listProvince)
        _adapterArea.setOnItemClickListener { adapter, view, position ->
            _adapterArea.setSelect(position)
        }
        rcv3.layoutManager = LinearLayoutManager(context)
        rcv3.adapter = _adapterArea
        ////////////////////////////////////////////
        val listView = mutableListOf<View>()
        listView.add(childViewProvince)
        listView.add(childViewCity)
        listView.add(childViewArea)
        vp.adapter = AreaSelectPageAdapter(listView)
        val listTitle= mutableListOf<String>()
        listTitle.add("请选择")
        listTitle.add("请选择")
        listTitle.add("请选择")
        tab.setViewPager(vp,listTitle.toTypedArray())
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
            }
        })
    }

    override fun onCreateContentView(): View = createPopupById(R.layout.pop_area_select)
}