package com.kxs109.testSpan

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.kxs109.commonlib.config.utils.ext.logE
import com.kxs109.viewlearn.R
import kotlin.random.Random

/*
 * @Author zhh
 * @Date 2022/1/28
 * @Des  测试span
 */
class TestSpanActivity : AppCompatActivity() {
    private val spanBuilder by lazy { SpannableStringBuilder() }
    private val mContentList = mutableListOf<SpecialContent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_learn_activity_test_span)
        val et = findViewById<EditText>(R.id.et)
        findViewById<Button>(R.id.btn).setOnClickListener {
            //选书
            selectBooks()
            //插入书名
            et.text.insert(et.selectionEnd, "kxs109")
            et.requestFocus()
            et.isCursorVisible = true
        }
        et.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                "kxs11  $s***$start***$after***$count".logE()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                "kxs22  $s***$start***$before***$count".logE()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun selectBooks() {
        val nextInt = Random.nextInt(1, 3)
        if (nextInt == 1){
            mContentList.add(SpecialContent("test my book"))
        }else if (nextInt == 2){
            mContentList.add(SpecialContent("test my book11"))
            mContentList.add(SpecialContent("test my book22"))
        }else{
            mContentList.add(SpecialContent("test my bookAA"))
            mContentList.add(SpecialContent("test my bookBB"))
            mContentList.add(SpecialContent("test my bookCC"))
        }
    }
}