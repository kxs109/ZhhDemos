package com.kxs109.com.kxs109.drawable

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.kxs109.com.kxs109.drawable.FishDrawable2.Companion.HEAD_RADIUS
import com.kxs109.commonlib.config.utils.ext.logE

class FishRelativeLayout @JvmOverloads constructor(
    context: Context,
    val attributeSet: AttributeSet
) :
    RelativeLayout(context, attributeSet) {
    private val ctx = context
    private var touchX = 0F
    private var touchY = 0F
    private var repple = 0f
    private var reppleAlpha = 0f
    private var mPath: Path? = null
    private val mPaint by lazy { Paint() }
    private val fishDrawable by lazy { FishDrawable2() }
    private lateinit var ivFish: ImageView
    private lateinit var animator: ValueAnimator
    private lateinit var mCanvas: Canvas

    init {
        setWillNotDraw(false)
        mPaint.color = Color.GREEN
        mPaint.isDither = true
        mPaint.isAntiAlias = true
        mPaint.style=Paint.Style.STROKE
        mPaint.strokeWidth=3f
        ivFish = ImageView(ctx)
        ivFish.setBackgroundColor(Color.LTGRAY)
        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        ivFish.layoutParams = lp
        ivFish.setImageDrawable(fishDrawable)
        this.addView(ivFish)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        mPaint.alpha = reppleAlpha.toInt()
        canvas?.drawCircle(touchX, touchY, repple * 120, mPaint)

        mPath?.let {
            canvas?.drawPath(it, mPaint)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        touchX = event!!.x
        touchY = event!!.y
        val vm = ObjectAnimator.ofFloat(this, "repple", 0f, 1f).setDuration(1000)
        vm.start()

        makeTrail(PointF(touchX, touchY))

        return super.onTouchEvent(event)
    }

    /**
     * 鱼头是第一控点，中点和头与中点和点击点的夹角的一半是第二个控制点角度
     *
     * @param touch
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun makeTrail(touch: PointF) {
        val deltaX: Float = fishDrawable.getMiddlePoint().x
        val deltaY: Float = fishDrawable.getMiddlePoint().y
        mPath = Path()
        val fishMiddle = PointF(ivFish.getX() + deltaX, ivFish.getY() + deltaY)
        val fishHead = PointF(
            ivFish.getX() + fishDrawable.getHeadPoint().x,
            ivFish.getY() + fishDrawable.getHeadPoint().y
        )
        //把贝塞尔曲线起始点平移到Imageview的左上角
        mPath?.moveTo(fishMiddle.x - deltaX, fishMiddle.y - deltaY)
        val angle: Float =
            includedAngle(fishMiddle, fishHead, touch)
        val delta: Float =
            calcultatAngle(fishMiddle, fishHead)
        val controlF: PointF = fishDrawable.calculatePoint(
            fishMiddle,
            1.6f * HEAD_RADIUS,
            (angle / 2 + delta).toDouble()
        )
        //把贝塞尔曲线的所有控制点和结束点都做平移处理
        mPath?.cubicTo(
            fishHead.x - deltaX,
            fishHead.y - deltaY,
            controlF.x - deltaX,
            controlF.y - deltaY,
            touch.x - deltaX,
            touch.y - deltaY
        )
        invalidate()
        val tan = FloatArray(2)
        val pathMeasure = PathMeasure(mPath, false)
        animator = ObjectAnimator.ofFloat(ivFish, "x", "y", mPath)
        animator.setDuration(2 * 1000.toLong())
        animator.setInterpolator(AccelerateDecelerateInterpolator())
        //动画启动和结束时设置鱼鳍摆动动画，同时控制鱼身摆动频率
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
//                fishDrawable.setWaveFrequence(1f)
            }

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
//                fishDrawable.setWaveFrequence(2f)
//                val finsAnimator: ObjectAnimator = fishDrawable.getFinsAnimator()
//                finsAnimator.repeatCount = Random().nextInt(3)
//                finsAnimator.duration = ((Random().nextInt(1) + 1) * 500).toLong()
//                finsAnimator.start()
            }
        })
        animator.addUpdateListener(AnimatorUpdateListener { animation ->
            val fraction = animation.animatedFraction
            pathMeasure.getPosTan(pathMeasure.length * fraction, null, tan)
            val angle = Math.toDegrees(
                Math.atan2(
                    (-tan[1]).toDouble(),
                    tan[0].toDouble()
                )
            ).toFloat()
            Log.e("**-**-**-**", "onAnimationUpdate: $angle")
            fishDrawable.setAngle(angle)
        })
        animator.start()
    }

    /**
     * 线和x轴夹角
     *
     * @param start
     * @param end
     * @return
     */
    fun calcultatAngle(start: PointF, end: PointF?): Float {
        return includedAngle(
            start,
            PointF(start.x + 1, start.y),
            end!!
        )
    }

    /**
     * 这个是计算向量夹角和鱼头转动方向的
     */
   private fun includedAngle(o: PointF, a: PointF, b: PointF): Float {
        val aob = (a.x - o.x) * (b.x - o.x) + (a.y - o.y) * (b.y - o.y)
        val angleCos = (aob /
                (Math.sqrt((a.x - o.x) * (a.x - o.x) + (a.y - o.y) * (a.y - o.y).toDouble())
                        * Math.sqrt((b.x - o.x) * (b.x - o.x) + (b.y - o.y) * (b.y - o.y).toDouble()))).toFloat()
        ("" + a + "===" + o + "===" + b).logE()
        val temAngle =
            Math.toDegrees(Math.acos(angleCos.toDouble())).toFloat()
        (""+temAngle).logE()
        //判断方向  正左侧  负右侧 0线上,但是Android的坐标系Y是朝下的，所以左右颠倒一下
        val direction =
            (o.x - b.x) * (a.y - b.y) - (o.y - b.y) * (a.x - b.x)
        return if (direction == 0f) {
            if (aob >= 0) {
                0f
            } else 180f
        } else {
            if (direction > 0) { //右侧顺时针为负
                -temAngle
            } else {
                temAngle
            }
        }
    }

    fun setRepple(repple: Float) {
        this.reppleAlpha = (1 - repple) * 150
        this.repple = repple

        invalidate()
    }

}