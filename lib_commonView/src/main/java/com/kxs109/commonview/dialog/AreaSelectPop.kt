package com.kxs109.commonview.dialog

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flyco.tablayout.SlidingTabLayout
import com.flyco.tablayout.listener.OnTabSelectListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kxs109.commonlib.config.utils.ext.fromJsonFile
import com.kxs109.commonlib.config.utils.ext.screenHeight
import com.kxs109.commonlib.config.utils.ext.screenWidth
import com.kxs109.commonview.R
import com.kxs109.commonview.areaSelect.adapter.AreaSelectAdapter
import com.kxs109.commonview.areaSelect.adapter.AreaSelectPageAdapter
import com.kxs109.commonview.areaSelect.model.Province
import com.kxs109.commonview.widget.NoScrollableViewPager
import razerdp.basepopup.BasePopupWindow

/**
  * @Description:    地区选择框
  * @Author:         zhh
  * @CreateDate:     2020/5/7 17:54
  */
class AreaSelectPop(
    context: Activity, selectAreaComplete: (Province?, Province?, Province?) -> Unit
) : BasePopupWindow(context) {
    var mSelectProvincePosition: Int = -1
    var mSelectCityPosition: Int = -1
    var mSelectAreaPosition: Int = -1
    var mTabLayout: SlidingTabLayout? = null
    var mVp: NoScrollableViewPager? = null
    var mBtnClose: FrameLayout? = null
    var mAdapterProvince: AreaSelectAdapter? = null
    var mAdapterCity: AreaSelectAdapter? = null
    var mAdapterArea: AreaSelectAdapter? = null
    var mListProvince: MutableList<Province>? = null
    var mLastPagePosition = 0
    private fun readAreaList() {
        mListProvince = fromJsonFile<MutableList<Province>>(
            Gson(),
            context,
            "area.json",
            object : TypeToken<MutableList<Province>>() {}.type
        )
    }

    init {
        popupGravity = Gravity.BOTTOM
        setBackground(0)
        width = context.screenWidth
        height = context.screenHeight * 0.75.toInt()
        setOutSideDismiss(false)
        isOutSideTouchable = false
        setBackPressEnable(false)
        setBackgroundColor(Color.parseColor("#aa000000"))
        readAreaList()
        mBtnClose = findViewById(R.id.btn_close)
        mBtnClose?.setOnClickListener {
            dismiss()
            if (mSelectProvincePosition>=0&&mSelectCityPosition>=0&&mSelectAreaPosition>=0){
                selectAreaComplete(mAdapterProvince!!.data[mSelectProvincePosition],
                    mAdapterCity!!.data[mSelectCityPosition],
                    mAdapterArea!!.data[mSelectAreaPosition]
                )
            }

        }
        mVp = findViewById(R.id.vp_area_selection)
        mTabLayout = findViewById(R.id.tab_area_select)
        val childViewProvince = View.inflate(context, R.layout.view_list_area, null)
        val childViewCity = View.inflate(context, R.layout.view_list_area, null)
        val childViewArea = View.inflate(context, R.layout.view_list_area, null)
        mAdapterProvince = AreaSelectAdapter()
        mAdapterCity = AreaSelectAdapter()
        mAdapterArea = AreaSelectAdapter()

        val rcvProvince = childViewProvince.findViewById<RecyclerView>(R.id.rcv_pop_area_selection)
        mAdapterProvince?.setNewInstance(mListProvince)
        mAdapterProvince?.setOnItemClickListener { adapter, view, position ->
            if (mSelectProvincePosition != position) {
                mSelectProvincePosition = position
                mAdapterProvince?.setSelect(mSelectProvincePosition)
                mAdapterCity?.setNewInstance(mListProvince!![mSelectProvincePosition].cityList)

                mSelectCityPosition = -1
                updateTabTitleStatus()

            }

        }
        rcvProvince.layoutManager = LinearLayoutManager(context)
        rcvProvince.adapter = mAdapterProvince
        /////////////////////////////////////////////
        val rcvCity = childViewCity.findViewById<RecyclerView>(R.id.rcv_pop_area_selection)
        mAdapterCity?.setOnItemClickListener { adapter, view, position ->
            if (mSelectCityPosition != position) {
                mSelectCityPosition = position
                mAdapterCity?.setSelect(mSelectCityPosition)
                mAdapterArea?.setNewInstance(mListProvince!![mSelectProvincePosition].cityList!![mSelectCityPosition].areaList)
                mSelectAreaPosition = -1
                updateTabTitleStatus()
            }

        }
        rcvCity.layoutManager = LinearLayoutManager(context)
        rcvCity.adapter = mAdapterCity
        /////////////////////////////////////////////
        val rcvArea = childViewArea.findViewById<RecyclerView>(R.id.rcv_pop_area_selection)
        mAdapterArea?.setOnItemClickListener { adapter, view, position ->
            if (mSelectAreaPosition != position) {
                mSelectAreaPosition = position
                mAdapterArea?.setSelect(mSelectAreaPosition)
                updateTabTitleStatus()
            }
        }
        rcvArea.layoutManager = LinearLayoutManager(context)
        rcvArea.adapter = mAdapterArea
        ////////////////////////////////////////////
        val listView = mutableListOf<View>()
        listView.add(childViewProvince)
        listView.add(childViewCity)
        listView.add(childViewArea)
        mVp?.adapter =
            AreaSelectPageAdapter(
                listView
            )
        val listTitle = mutableListOf<String>()

        listTitle.add(context.resources.getString(R.string.area_select_default))
        listTitle.add(context.resources.getString(R.string.area_select_default))
        listTitle.add(context.resources.getString(R.string.area_select_default))
        mVp?.setScroll(false)
        mTabLayout?.setViewPager(mVp, listTitle.toTypedArray())
        updateTabTitleStatus()
        mTabLayout?.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                if (position == 1) {
                    mLastPagePosition = if (mSelectProvincePosition == -1) {
                        0
                    } else {
                        1
                    }
                } else if (position == 2) {
                    if (mSelectCityPosition != -1) {
                        mLastPagePosition = 2
                    }
                } else {
                    mLastPagePosition = 0
                }
                mVp?.currentItem = mLastPagePosition
            }

            override fun onTabReselect(position: Int) {
            }
        })
    }

    private fun updateTabTitleStatus() {
        if (mSelectProvincePosition == -1) {
            mSelectCityPosition = -1
            mSelectAreaPosition = -1
            mAdapterProvince?.setSelect(mSelectProvincePosition)
            mAdapterCity?.setSelect(mSelectCityPosition)
            mAdapterArea?.setSelect(mSelectAreaPosition)
        } else {
            if (mSelectCityPosition == -1) {
                mSelectAreaPosition = -1
                mAdapterCity?.setSelect(mSelectCityPosition)
                mAdapterArea?.setSelect(mSelectAreaPosition)
            } else {
                if (mSelectAreaPosition == -1) {
                    mAdapterArea?.setSelect(mSelectAreaPosition)
                }
            }
        }

        if (mSelectProvincePosition == -1) {
            mTabLayout?.getTitleView(0)?.text =
                context.resources.getString(R.string.area_select_default)
            mTabLayout?.getTitleView(1)?.visibility = View.INVISIBLE
            mTabLayout?.getTitleView(2)?.visibility = View.INVISIBLE
        } else {
            mTabLayout?.getTitleView(0)?.text =
                mListProvince!![mSelectProvincePosition].provinceName
            mTabLayout?.getTitleView(1)?.visibility = View.VISIBLE
        }
        if (mSelectCityPosition == -1) {
            mTabLayout?.getTitleView(1)?.text =
                context.resources.getString(R.string.area_select_default)
            mTabLayout?.getTitleView(2)?.visibility = View.INVISIBLE

        } else {
            mTabLayout?.getTitleView(1)?.text =
                mListProvince!![mSelectProvincePosition].cityList!![mSelectCityPosition].cityName
            mTabLayout?.getTitleView(2)?.visibility = View.VISIBLE
        }
        if (mSelectAreaPosition == -1) {
            mTabLayout?.getTitleView(2)?.text =
                context.resources.getString(R.string.area_select_default)
        } else {
            mTabLayout?.getTitleView(2)?.text =
                mListProvince!![mSelectProvincePosition].cityList!![mSelectCityPosition].areaList!![mSelectAreaPosition].areaName
        }
    }

    override fun onCreateContentView(): View = createPopupById(R.layout.pop_area_select)
    fun setStyle(isStudent: Boolean) {
        if (isStudent) {
            mTabLayout?.indicatorColor = ContextCompat.getColor(context, R.color.color_theme1_p2)
            mAdapterProvince?.setStyle(true)
            mAdapterCity?.setStyle(true)
            mAdapterArea?.setStyle(true)
        }
    }
}