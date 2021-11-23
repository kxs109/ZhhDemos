package com.kxs109.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.abs

/*
 * @Author zhh
 * @Date 2021/10/20
 * @Des  描边的textView
 */
class StrokeText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val fm = paint.fontMetrics
        //测试baseLine

         /*paint.color = Color.BLACK
        canvas?.drawLine(
            0f,
            0f-fm.top,
            width.toFloat(),
            0f-fm.top,
            paint
        )
        paint.color = Color.RED
        canvas?.drawLine(
            0f,
            0f-fm.top+fm.ascent,
            width.toFloat(),
            0f-fm.top+fm.ascent,
            paint
        )
        paint.color = Color.BLUE
        canvas?.drawLine(
            0f,
            0f-fm.top+fm.descent,
            width.toFloat(),
            0f-fm.top+fm.descent,
            paint
        )*/


//测试居中，xml布局要设置MATCH_PARENT
        val baseX = width / 2 - paint.measureText(text.toString()) / 2
        val baseY = height / 2 - (paint.descent() + paint.ascent()) / 2
        paint.color = Color.BLACK
        canvas?.drawText(text.toString(), baseX, baseY, paint)
        paint.color = Color.BLACK
        canvas?.drawLine(
            0f,
            baseY,
            width.toFloat(),
            baseY,
            paint
        )
        paint.color = Color.RED
        canvas?.drawLine(
            0f,
            baseY+fm.ascent,
            width.toFloat(),
            baseY +fm.ascent,
            paint
        )
        paint.color = Color.BLUE
        canvas?.drawLine(
            0f,
            baseY+fm.descent,
            width.toFloat(),
            baseY +fm.descent,
            paint
        )
        paint.color = Color.RED
        canvas?.drawLine(
            0f,
            baseY+fm.ascent + fm.descent/*-(fm.ascent + fm.descent)/2*/,
            width.toFloat(),
            baseY+fm.ascent + fm.descent/*-(fm.ascent + fm.descent)/2*/,
            paint
        )
        paint.color = Color.GRAY
        canvas?.drawLine(
            0f,
            height.toFloat() / 2,
            width.toFloat(),
            height.toFloat() / 2,
            this.paint
        )
    }
}