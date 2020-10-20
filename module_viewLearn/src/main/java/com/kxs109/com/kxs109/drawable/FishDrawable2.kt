package com.kxs109.com.kxs109.drawable

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import com.kxs109.commonlib.config.utils.ext.logE
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class FishDrawable2 : Drawable() {
    companion object {
        const val HEAD_RADIUS = 50f//头部圆形半径
        const val BODY_LENGTH = 3.2f * HEAD_RADIUS
        const val FINS_LENGTH = 1.3f * HEAD_RADIUS//鱼鳍起点到终点的直线长度
    }

    private var currentValue = 0.toDouble()
    private var finsAngel = 0f
    private val mPaint = Paint()
    private val mPath = Path()
    //与x轴的角度，默认90°，竖直向上
    private var middleAngle = 90.toDouble()
    private var changeAngle = 0.toDouble()
    //重心点的坐标，整条鱼绕重心点旋转在屏幕占用的空间其实是一个正方形
    //这个重心点就是正方形的中心点
    private val middlePoint = PointF(4.19f * HEAD_RADIUS, 4.19f * HEAD_RADIUS)
    private lateinit var headPoint:PointF
//    private  var headPoint:PointF=calculatePoint(middlePoint, BODY_LENGTH / 2, changeAngle)


    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
        mPaint.isDither = true
        mPaint.color = Color.parseColor("#99010103")


        val finsAnimator = ObjectAnimator.ofFloat(this, "finsAngel", 0f, 1f, 0f)
        finsAnimator.repeatMode = ObjectAnimator.REVERSE
        finsAnimator.repeatCount = Random.nextInt(3)

        val animator = ValueAnimator.ofInt(0, 360)
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
        animator.duration = 2300
        animator.addUpdateListener {
            currentValue = (it.animatedValue as Int).toDouble()
//            middleAngle=currentValue
            invalidateSelf()
        }
        /*animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationRepeat(animation: Animator?) {
                super.onAnimationRepeat(animation)
                finsAnimator.start()
            }
        })*/
        animator.start()
    }

    override fun draw(canvas: Canvas) {
        changeAngle = middleAngle + sin(Math.toRadians(currentValue)) * 5
        headPoint = calculatePoint(middlePoint, BODY_LENGTH / 2, changeAngle)

        //sin参数为弧度值
        //现有角度=原始角度+ sin（域值[-1，1]）*可摆动的角度   sin作用是控制周期摆动
        val endPoint = calculatePoint(headPoint, BODY_LENGTH, changeAngle + 180)
        //绘制头部
        drawHead(canvas, headPoint)
        //绘制眼睛
        drawEyes(headPoint, canvas)
        //绘制主体
        drawMainBody(headPoint, endPoint, canvas)
        //绘制鱼鳍
        drawFins(headPoint, canvas)
        drawSegment1(endPoint, canvas)


        val horizontalPoint = calculatePoint(middlePoint, FishDrawable.BODY_LENGTH / 2, middleAngle-90)
        mPaint.color=Color.RED
        canvas.drawLine(middlePoint.x,middlePoint.y,headPoint.x,headPoint.y,mPaint)
        canvas.drawLine(middlePoint.x,middlePoint.y,horizontalPoint.x,horizontalPoint.y,mPaint)
    }

    private fun drawEyes(headPoint: PointF, canvas: Canvas) {
        val leftEyePoint = calculatePoint(headPoint, 0.8f * HEAD_RADIUS, middleAngle + 60)
        val rightEyePoint = calculatePoint(headPoint, 0.8f * HEAD_RADIUS, middleAngle - 60)
        mPaint.color = Color.BLACK
        canvas.drawCircle(leftEyePoint.x, leftEyePoint.y, 0.1F * HEAD_RADIUS, mPaint)
        canvas.drawCircle(rightEyePoint.x, rightEyePoint.y, 0.1F * HEAD_RADIUS, mPaint)
    }

    private fun drawHead(canvas: Canvas, headPoint: PointF) {
        mPaint.color = Color.parseColor("#88000000")
        canvas.drawCircle(headPoint.x, headPoint.y, HEAD_RADIUS, mPaint)
    }

    private fun drawFins(headPoint: PointF, canvas: Canvas) {
        val finsLeftStartPoint = calculatePoint(headPoint, HEAD_RADIUS * 0.9f, changeAngle + 110)
        val finsLeftEndPoint =
            calculatePoint(finsLeftStartPoint, HEAD_RADIUS * 1.3f, changeAngle + 180)
        val finsLeftControlPoint =
            calculatePoint(finsLeftStartPoint, HEAD_RADIUS * 1.8f, changeAngle + 115)
        drawFins(canvas, finsLeftStartPoint, finsLeftControlPoint, finsLeftEndPoint)
        //鱼鳍右边path需要的三个定点
        val finsRightStartPoint = calculatePoint(headPoint, HEAD_RADIUS * 0.9f, changeAngle - 110)
        val finsRightEndPoint =
            calculatePoint(finsRightStartPoint, HEAD_RADIUS * 1.3f, changeAngle - 180)
        val finsRightControlPoint =
            calculatePoint(finsRightStartPoint, HEAD_RADIUS * 1.8f, changeAngle - 115)
        drawFins(canvas, finsRightStartPoint, finsRightControlPoint, finsRightEndPoint)
    }

    private fun drawMainBody(
        headPoint: PointF,
        endPoint: PointF,
        canvas: Canvas
    ) {
//        ("" + currentValue + "---" + changeAngle).logE()
        //主体左边path三个定位点
        val point1 = calculatePoint(headPoint, HEAD_RADIUS, changeAngle + 80)
        val point2 = calculatePoint(endPoint, HEAD_RADIUS * 0.7f, changeAngle + 90)
        val controlLeft = calculatePoint(headPoint, BODY_LENGTH * 0.56f, changeAngle + 130)
        //主体右边path三个定位点
        val point3 = calculatePoint(endPoint, HEAD_RADIUS * 0.7f, changeAngle - 90)
        val point4 = calculatePoint(headPoint, HEAD_RADIUS, changeAngle - 80)
        val controlRight = calculatePoint(headPoint, BODY_LENGTH * 0.56f, changeAngle - 130)
        mPath.reset()
        mPath.moveTo(point1.x, point1.y)
        mPath.quadTo(controlLeft.x, controlLeft.y, point2.x, point2.y)
        mPath.lineTo(point3.x, point3.y)
        mPath.quadTo(controlRight.x, controlRight.y, point4.x, point4.y)
        mPath.lineTo(point1.x, point1.y)
        mPaint.color = Color.parseColor("#77000000")
        canvas.drawPath(mPath, mPaint)
    }

    private fun drawSegment1(endPoint: PointF, canvas: Canvas) {
        changeAngle = middleAngle + cos(Math.toRadians(currentValue * 2)) * 20
        mPaint.color = Color.parseColor("#94000000")
        canvas.drawCircle(endPoint.x, endPoint.y, 0.7f * HEAD_RADIUS, mPaint)
        val endPoint2 = calculatePoint(endPoint, 1.2f * HEAD_RADIUS, changeAngle - 180)
        canvas.drawCircle(endPoint2.x, endPoint2.y, 0.5f * HEAD_RADIUS, mPaint)
        val point1 = calculatePoint(endPoint, 0.7f * HEAD_RADIUS, changeAngle + 90)
        val point2 = calculatePoint(endPoint, 0.7f * HEAD_RADIUS, changeAngle - 90)
        val point3 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, changeAngle - 90)
        val point4 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, changeAngle + 90)
        mPath.reset()
        mPath.moveTo(point1.x, point1.y)
        mPath.lineTo(point2.x, point2.y)
        mPath.lineTo(point3.x, point3.y)
        mPath.lineTo(point4.x, point4.y)
        mPaint.color = Color.parseColor("#a1000000")
        canvas.drawPath(mPath, mPaint)

        drawSegment2(endPoint2, canvas)
        drawSegment3(endPoint2, canvas)
    }

    private fun drawSegment2(endPoint2: PointF, canvas: Canvas) {
        changeAngle = middleAngle + sin(Math.toRadians(currentValue * 3)) * 30

        val endPoint3 = calculatePoint(endPoint2, 1.19f * HEAD_RADIUS, changeAngle + 180)

        val point1 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, changeAngle + 90)
        val point2 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, changeAngle - 90)
        val point3 = calculatePoint(endPoint3, 0.2f * HEAD_RADIUS, changeAngle - 90)
        val point4 = calculatePoint(endPoint3, 0.2f * HEAD_RADIUS, changeAngle + 90)
        mPath.reset()
        mPath.moveTo(point1.x, point1.y)
        mPath.lineTo(point2.x, point2.y)
        mPath.lineTo(point3.x, point3.y)
        mPath.lineTo(point4.x, point4.y)
        mPaint.color = Color.parseColor("#99000000")
        canvas.drawPath(mPath, mPaint)
        canvas.drawCircle(endPoint3.x, endPoint3.y, 0.2f * HEAD_RADIUS, mPaint)
    }

    /**
     * 尾部两个三角形
     */
    private fun drawSegment3(endPoint2: PointF, canvas: Canvas) {
        changeAngle = middleAngle + sin(Math.toRadians(currentValue * 3)) * 30
        val point1 = calculatePoint(endPoint2, 1f * HEAD_RADIUS, changeAngle + 150)
        val point2 = calculatePoint(endPoint2, 1f * HEAD_RADIUS, changeAngle - 150)
        val point3 = calculatePoint(endPoint2, 1.2f * HEAD_RADIUS, changeAngle + 145)
        val point4 = calculatePoint(endPoint2, 1.2f * HEAD_RADIUS, changeAngle - 145)
        mPath.reset()
        mPath.moveTo(endPoint2.x, endPoint2.y)
        mPath.lineTo(point1.x, point1.y)
        mPath.lineTo(point2.x, point2.y)
        mPath.lineTo(endPoint2.x, endPoint2.y)
        canvas.drawPath(mPath, mPaint)

        mPath.reset()
        mPath.moveTo(endPoint2.x, endPoint2.y)
        mPath.lineTo(point3.x, point3.y)
        mPath.lineTo(point4.x, point4.y)
        mPath.lineTo(endPoint2.x, endPoint2.y)
        mPaint.color = Color.parseColor("#55000000")

        canvas.drawPath(mPath, mPaint)
    }


    /**
     * 绘制鱼鳍
     */
    private fun drawFins(
        canvas: Canvas,
        startPoint: PointF,
        controlPoint: PointF,
        endPoint: PointF
    ) {
        mPath.reset()
        mPath.moveTo(startPoint.x, startPoint.y)
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y)
        mPath.lineTo(startPoint.x, startPoint.y)
        mPaint.color = Color.parseColor("#28010203")
        canvas.drawPath(mPath, mPaint)
    }

    fun calculatePoint(startPoint: PointF, length: Float, angel: Double): PointF {
        val dataX = (cos(Math.toRadians(angel)) * length).toFloat()
        val dataY = (-sin(Math.toRadians(angel)) * length).toFloat()
        return PointF(startPoint.x + dataX, startPoint.y + dataY)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    private fun setFinsAngel(angel: Float) {
        this.finsAngel = angel
    }

    fun getMiddlePoint():PointF {
        return middlePoint
    }
    fun getHeadPoint():PointF{
        return headPoint
    }
    fun setAngle(angle:Float){
        this.middleAngle=angle.toDouble()
    }


    //决定绘制的部分是否遮住Drawable下边的东西，有点抽象，有几种模式
    //PixelFormat.UNKNOWN
    //PixelFormat.TRANSLUCENT 只有绘制的地方才盖住下边
    //PixelFormat.TRANSPARENT 透明，不显示绘制内容
    //PixelFormat.OPAQUE 完全盖住下边内容
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getIntrinsicHeight(): Int {
        return (8.38 * HEAD_RADIUS).toInt()
    }

    override fun getIntrinsicWidth(): Int {
        return (8.38 * HEAD_RADIUS).toInt()
    }
}