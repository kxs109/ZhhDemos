package com.kxs109.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kxs109.viewlearn.R


/*
 * @Author zhh
 * @Date 2021/10/20
 * @Des  描边的textView
 */
class StrokeText2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {
    private var strokePaint: TextPaint? = null

    override fun onDraw(canvas: Canvas?) {
        if (strokePaint == null){
            strokePaint = TextPaint()
        }
        strokePaint?.run {
            textSize = paint.textSize
            typeface = paint.typeface
            flags = paint.flags
            alpha = paint.alpha
            style = Paint.Style.STROKE
            color = ContextCompat.getColor(context, R.color.colorAccent)
            strokeWidth = 3f
        }
        val text = text.toString()
        val gravity = gravity
        /*when {
            (gravity and Gravity.START) == Gravity.START -> {
                canvas?.drawText(
                    text, 0f,
                    baseline.toFloat(), strokePaint!!
                )
            }
            gravity and Gravity.END == Gravity.END -> {
                canvas?.drawText(
                    text, width - strokePaint!!.measureText(text),
                    baseline.toFloat(), strokePaint!!
                )
            }
            else -> {
                canvas?.drawText(
                    text, (width - strokePaint!!.measureText(text)) / 2,
                    baseline.toFloat(), strokePaint!!
                )
            }
        }*/


        if (!TextUtils.isEmpty(text)) {
            // 优化描边位置的计算，同时支持左、右padding
            when {
                gravity and Gravity.START == Gravity.START -> {
                    canvas!!.drawText(
                        text, compoundPaddingLeft.toFloat(),
                        baseline.toFloat(), strokePaint!!
                    )
                }
                gravity and Gravity.END == Gravity.END -> {
                    canvas!!.drawText(
                        text, width - compoundPaddingRight - paint.measureText(text),
                        baseline.toFloat(), strokePaint!!
                    )
                }
                else -> {
                    // 除去左、右padding后，在剩下的空间中paint落笔的位置
                    val xInLeftSpace = (width - compoundPaddingRight - compoundPaddingLeft
                            - paint.measureText(text)) / 2
                    // 最终落笔点位置 [x = paddingLeft + xInLeftSpace, y = getBaseLine()]
                    canvas!!.drawText(
                        text, paddingLeft + xInLeftSpace,
                        baseline.toFloat(), strokePaint!!
                    )
                }
            }
        }

        super.onDraw(canvas)

    }
}