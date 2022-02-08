package com.kxs109.testSpan

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
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
    private val foregroundSpan by lazy { ForegroundColorSpan(Color.RED) }

    private val mContentList = mutableListOf<SpecialContent>()//旧的书籍集合
    private val mNewContentList = mutableListOf<SpecialContent>()//返回来的新书籍集合

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_learn_activity_test_span)
        val et = findViewById<EditText>(R.id.et)
        findViewById<Button>(R.id.btn).setOnClickListener {
            //选书
            mContentList.run {
                clear()
                mContentList.addAll(mNewContentList)
            }
            selectBooks()

            var cursorEnd = et.selectionEnd
            spanBuilder.run {
                clear()
                clearSpans()
                append(et.text)
                mNewContentList.forEach {
                    append(it.bookTitle).append(", ")
                    setSpan(
                        CharacterStyle.wrap(foregroundSpan),
                        cursorEnd,
                        cursorEnd + it.bookTitle.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    cursorEnd += (it.bookTitle.length + 2)
                }
            }
            et.text = spanBuilder
            et.setSelection(cursorEnd)
            et.requestFocus()
            et.isCursorVisible = true
        }
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                "kxs11  $s***$start***$after***$count".logE()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                "kxs22  $s***$start***$before***$count".logE()
                val spans =  spanBuilder.getSpans(0, spanBuilder.length, ForegroundColorSpan::class.java)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun selectBooks() {
        mNewContentList.run {
            clear()
            val nextInt = Random.nextInt(1, 3)
            if (nextInt == 1) {
                add(SpecialContent("111","test my book"))
            } else if (nextInt == 2) {
                add(SpecialContent("222","test my book11"))
                add(SpecialContent("333","test my book22"))
            } else {
                add(SpecialContent("444","test my bookAA"))
                add(SpecialContent("555","test my bookBB"))
                add(SpecialContent("666","test my bookCC"))
            }
        }
    }
}