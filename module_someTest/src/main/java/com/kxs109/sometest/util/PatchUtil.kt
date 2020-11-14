package com.kxs109.sometest.util

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.Array
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 *
 * @Author: zhh
 * @CreateDate: 2020/11/12 5:16 PM
 * @Description:    反射类
 */


fun findField(instance: Any, name: String): Field? {
    var clz: Class<*>? = instance.javaClass
    var field:Field?=null
    while (clz != null) {
        try {
            if (clz.getDeclaredField(name) != null) {
                field= clz.getDeclaredField(name)
                field.isAccessible = true
                println("field1----"+field)
                break
            }
        } catch (e: Exception) {

        }
        clz= clz.superclass
    }
    println("field---"+field)
    return field

}

//
fun findMethod(instance: Any?, name: String, vararg parameterType: Class<*>): Method? {
    var clz: Class<*>? = instance!!.javaClass
    var method:Method?=null
    while (clz != null) {
        try {
            if (clz.getDeclaredMethod(name, *parameterType) != null) {
                method=clz.getDeclaredMethod(name,*parameterType)
                method.isAccessible = true
                break
            }
        } catch (e: Exception) {

        }
        clz = clz.superclass

    }
    return method
}


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun installPatch(application: Application, patchFile: File) {
    if (!patchFile.exists()) {
        return
    }

    var classLoader = application.classLoader
    //获取pathList
    var pathListField = findField(classLoader, "pathList")
    var pathList = pathListField?.get(classLoader)
    //获取dexElements
    var dexElementsField = findField(pathList!!, "dexElements")
    var dexElements = dexElementsField?.get(pathList) as kotlin.Array<Any>
//将patchFile转化成dexElements
    var fileList = arrayListOf<File>(patchFile)
    val odexFile = application.codeCacheDir
    var ioExceptionList = arrayListOf<IOException>()
    val method = findMethod(
        pathList,
        "makePathElements",
        List::class.java,
        File::class.java,
        List::class.java
    )
    val patchElements =
        method?.invoke(null, fileList, odexFile, ioExceptionList) as kotlin.Array<Any>
    val newElementsArray = Array.newInstance(
        dexElements.javaClass.componentType,
        patchElements.size + dexElements.size
    )
    System.arraycopy(patchElements, 0, newElementsArray, 0, patchElements.size)
    System.arraycopy(dexElements, 0, newElementsArray, patchElements.size, dexElements.size)
    dexElementsField.set(pathList, newElementsArray)
}
