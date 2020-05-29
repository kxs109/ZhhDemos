package com.kxs109.commonview.areaSelect.model

/**
 * @Description:    地区选择--- 省
 * @Author:         zhh
 * @CreateDate:     2020/4/28 16:33
 */
data class Province(
    var provinceID: Int? = null,
    var first: String? = null,
    var provinceName: String? = null,
    var level: Int? = null,
    var cityList: MutableList<Province>? = null,

//
    var cityID:Int?=null,
    var parentID:Int?=null,
    var cityName:String?=null,
    var areaList:MutableList<Province>?=null,

    //
    var areaID:Int?=null,
    var areaName:String?=null
)