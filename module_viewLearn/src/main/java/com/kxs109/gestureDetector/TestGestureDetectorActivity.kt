package com.kxs109.gestureDetector

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.kxs109.commonlib.config.utils.ext.logE
import com.kxs109.viewlearn.R

/*
 * @Author zhh
 * @Date 2021/12/6
 * @Des  测试手势activity
 */
class TestGestureDetectorActivity : AppCompatActivity(), View.OnTouchListener {
    private var mGestureDetector: GestureDetector? = null
    private var textView: TextView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_gesture_detector)
        textView = findViewById<TextView>(R.id.tv)
        textView?.setOnTouchListener(this)
        initGestureDetector()
    }

    private fun initGestureDetector() {
        mGestureDetector = GestureDetector(this, object : GestureDetector.OnGestureListener {
            override fun onDown(e: MotionEvent?): Boolean {
                "zhh---onDown".logE()
                return true
            }

            override fun onShowPress(e: MotionEvent?) {
                "zhh---onShowPress()".logE()
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                "zhh---onSingleTapUp".logE()
                return false
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                textView?.layout(((textView?.left?.toFloat()?:0f)+distanceX).toInt(), ((textView?.top?.toFloat()?:0f)+distanceY).toInt(),
                    ((textView?.right?.toFloat()?:0f)+distanceX).toInt(), ((textView?.left?.toFloat()?:0f)+distanceY).toInt())
                "zhh---onScroll>>>${distanceX}>>>${distanceY}".logE()
                return false
            }

            override fun onLongPress(e: MotionEvent?) {
                "zhh---onLongPress".logE()
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                textView?.x = velocityX
                textView?.y = velocityY
                "zhh---onFling>>>${velocityX}>>>${velocityY}".logE()
                return false
            }

        })
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return mGestureDetector?.onTouchEvent(event) ?: false
    }
}