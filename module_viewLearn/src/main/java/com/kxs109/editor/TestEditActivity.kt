package com.kxs109.editor


import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.kxs109.viewlearn.R


/**
 * @des:
 * @author: zhh
 * @date: 1/20/22 23
 */
class TestEditActivity: AppCompatActivity(), OnSpannableClickListener {
    private var mEditText: EditText? = null
    private var mEditTextNormal: EditText? = null
    private  var mEditTextPro:EditText? = null
    private var mTvServer2Local: TextView? = null
    private  var mTvLocal2Server:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_learn_activity_test_edit)
        mEditText = findViewById<View>(R.id.edittext) as EditText
        mEditTextNormal = findViewById<View>(R.id.edittextNormal) as EditText
        mEditTextPro = findViewById<View>(R.id.edittextPro) as EditText
        mTvServer2Local = findViewById<View>(R.id.server2Local) as TextView
        mTvLocal2Server = findViewById<View>(R.id.local2Server) as TextView
        mTvServer2Local!!.movementMethod = LinkMovementMethod.getInstance()
        mTvServer2Local!!.highlightColor = resources.getColor(android.R.color.transparent)
        RichParserManager.getManager().registerParser(PoiRichParser(this))
        RichParserManager.getManager().registerParser(NormalRichParser(this))
        val builder = StringBuilder()
        var jsonStr = "" +
                "{" +
                "    \"id\":1," +
                "    \"latitude\":116.46," +
                "    \"longitude\":39.92" +
                "}"
        var text = String.format("#[位置][%s]测试#", jsonStr)
        builder.append(text)
        builder.append("普通的一句话没有富文本")
        jsonStr = "" +
                "{" +
                "    \"id\":2," +
                "    \"latitude\":116.46," +
                "    \"longitude\":39.92" +
                "}"
        text = String.format("#[位置][%s]测试#", jsonStr, jsonStr)
        builder.append(text)
        /*mEditTextNormal!!.setText(
            RichParserManager.getManager().parseStr2Spannable(this, builder.toString())
        )*/
        mEditTextPro?.setText(
            RichParserManager.getManager().parseStr2Spannable(this, builder.toString())
        )
        mEditTextPro?.requestFocus()
        mEditText!!.setText(builder)


        /*mEditTextPro?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })*/
    }

    fun server2Local(view: View?) {
        val text = mEditText!!.text.toString()
        val ssb = RichParserManager.getManager().parseStr2Spannable(this, text)
        mTvServer2Local!!.text = ssb
    }

    fun local2Server(view: View?) {
        val ss = mTvServer2Local!!.text as SpannableString
        val ssb = SpannableStringBuilder(ss)
        val str = RichParserManager.getManager().parseSpannable2Str(ssb)
        mTvLocal2Server?.setText(str)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun t(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun v(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            Log.v("spannableStr", msg)
        }
    }

    fun onClick(
        parser: AbstractRichParser?,
        type: String?,
        content: Pair<String?, String?>?,
        sourceStr: String?
    ) {
        Toast.makeText(
            this,
            java.lang.String.format("sourceStr: %s,type: %s,content: %s", sourceStr, type, content),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onClick(
        parser: AbstractRichParser?,
        type: String?,
        content: android.util.Pair<String, String>?,
        sourceStr: String?
    ) {
        Toast.makeText(this, String.format("sourceStr: %s,type: %s,content: %s", sourceStr, type, content), Toast.LENGTH_SHORT).show();
    }
}