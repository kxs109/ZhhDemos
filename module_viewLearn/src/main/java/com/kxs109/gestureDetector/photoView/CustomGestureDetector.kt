package com.kxs109.gestureDetector.photoView

import android.content.Context
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewConfiguration
import java.lang.Exception

/*
 * @Author zhh
 * @Date 2021/12/16
 * @Des  ScaleGestureDetector
 */
const val INVALID_POINTER_ID = -1

class CustomGestureDetector(context: Context, gestureListener: OnGestureListener) {
    private var mTouchSlop: Int = 0
    private var mMinVelocity: Int = 0
    private var mLastFocusX: Float = 0f
    private var mLastFocusY: Float = 0f
    private var mScaleGestureDetector: ScaleGestureDetector? = null

    //
    private var mActivePointerId = INVALID_POINTER_ID

    init {
        ViewConfiguration.get(context).run {
            mTouchSlop = scaledTouchSlop
            mMinVelocity = scaledMinimumFlingVelocity
        }
        val scaleGestureListener = object : ScaleGestureDetector.OnScaleGestureListener {
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                val scaleFactor = detector?.scaleFactor
                if (scaleFactor == null || scaleFactor.isInfinite() || scaleFactor.isNaN()) {
                    return false
                }
                detector.run {
                    gestureListener.onScale(
                        scaleFactor,
                        focusX,
                        focusY,
                        focusX - mLastFocusX,
                        focusY - mLastFocusY
                    )
                }
                return true
            }

            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                mLastFocusX = detector?.focusX ?: 0f
                mLastFocusY = detector?.focusY ?: 0f
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector?) {
            }
        }
        mScaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        return try {
            mScaleGestureDetector?.onTouchEvent(event)
            processMotionEvent(event)
        } catch (e: Exception) {
            true
        }
    }

    private fun processMotionEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mActivePointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                mActivePointerId = INVALID_POINTER_ID
            }
            MotionEvent.ACTION_CANCEL -> {
                mActivePointerId = INVALID_POINTER_ID

            }
            MotionEvent.ACTION_POINTER_UP -> {
                val pointerIndex = event.actionIndex
                val pointerId = event.getPointerId(pointerIndex)
                //如果松开的手指是第一个按下的手指，那么要将mActivePointerId替换留在屏幕的手指
                if (pointerId == mActivePointerId) {
                    val newPointerIndex = if (pointerIndex == 0) {
                        1
                    } else {
                        0
                    }
                    mActivePointerId = event.findPointerIndex(newPointerIndex)
                }
            }
        }
        return true
    }
}