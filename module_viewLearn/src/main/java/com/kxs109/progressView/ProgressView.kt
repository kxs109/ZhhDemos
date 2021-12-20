package com.kxs109.progressView

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.kxs109.viewlearn.R

class ProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //#823CFB #C77DFF #A778EE #823CFB
    //#3B2175
    //#823CFB #C767FE

    //外边框画笔
    private val mBorderPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    //底部画笔
    private val mSolidPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    //进度画笔
    private val mSecondSolidPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private var mSolidColor: Int = 0
    private var mBorderColors: Int = 0
    private var mSecondSolidColor: Int = 0
    private var mBorderWidth = 2f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressView,
            0, 0
        ).apply {
            try {
                mSolidColor = getColor(
                    R.styleable.ProgressView_solid_color,
                    ContextCompat.getColor(context, R.color.white)
                )
                mBorderColors = getColor(R.styleable.ProgressView_border_colors, 0)
                mSecondSolidColor = getColor(R.styleable.ProgressView_second_solid_color, 0)
                mBorderWidth = getDimension(R.styleable.ProgressView_border_width, 0f)
            } finally {
                recycle()
            }
        }
       val mShader = LinearGradient(0f, 0f, 500f, 500f, intArrayOf(mBorderColors), floatArrayOf(), Shader.TileMode.CLAMP)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {

        }
    }

}