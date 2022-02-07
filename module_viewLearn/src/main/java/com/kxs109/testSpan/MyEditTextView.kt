package com.kxs109.testSpan

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.kxs109.commonlib.config.utils.ext.logE

/*
 * @Author zhh
 * @Date 2022/1/28
 * @Des  自定义editText
 */
class MyEditTextView constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {


    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        "kxs  onSelectionChanged: $selStart-----$selEnd>>>".logE()
        val b1 = selStart in 5..10 || selStart in 15..20
        val b2 = selEnd in 5..10 || selEnd in 15..20
        if (b1 || b2){
            if (10 < (text?.length?:0)){
                if (b1) {
                    setSelection(10, selEnd)
                }else{
                    setSelection(selStart, 10)
                }
            }else if (20 < (text?.length?:0)){
                if (b1) {
                    setSelection(20, selEnd)
                }else{
                    setSelection(selStart, 20)
                }
            }
        }
    }

}