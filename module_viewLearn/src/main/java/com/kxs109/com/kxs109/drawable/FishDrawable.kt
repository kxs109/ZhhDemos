package com.kxs109.com.kxs109.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.cos
import kotlin.math.sin

class FishDrawable : Drawable() {
    companion object {
        const val HEAD_RADIUS = 50f//头部圆形半径
        const val BODY_LENGTH = 3.2f * HEAD_RADIUS
        const val FINS_LENGTH = 1.3f * HEAD_RADIUS//鱼鳍起点到终点的直线长度
    }

    private val mPaint = Paint()
    private val mPath = Path()

    //重心点的坐标，整条鱼绕重心点旋转在屏幕占用的空间其实是一个正方形
    //这个重心点就是正方形的中心点
    private val middlePoint = PointF(4.19f * HEAD_RADIUS, 4.19f * HEAD_RADIUS)

    //与x轴的角度，默认90°，竖直向上
    private var middleAngle = 90.toDouble()

    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
        mPaint.isDither = true
        mPaint.color = Color.parseColor("#99010103")

    }

    override fun draw(canvas: Canvas) {

        val headPoint = calculatePoint(middlePoint, BODY_LENGTH / 2, middleAngle)
        val endPoint = calculatePoint(headPoint, BODY_LENGTH, middleAngle + 180)
        //绘制头部
        drawHead(canvas, headPoint)
        //绘制眼睛
        drawEyes(headPoint, canvas)
        //绘制鱼鳍
        drawFins(headPoint, canvas)
        //绘制主体
        dowMainBody(headPoint, endPoint, canvas)
        drawSegment1(endPoint, canvas)

    }

    private fun drawEyes(headPoint: PointF, canvas: Canvas) {
        val leftEyePoint = calculatePoint(headPoint, 0.5f * HEAD_RADIUS, middleAngle + 60)
        val rightEyePoint = calculatePoint(headPoint, 0.5f * HEAD_RADIUS, middleAngle - 60)
        mPaint.color = Color.BLACK
        canvas.drawCircle(leftEyePoint.x, leftEyePoint.y, 0.05F * HEAD_RADIUS, mPaint)
        canvas.drawCircle(rightEyePoint.x, rightEyePoint.y, 0.05F * HEAD_RADIUS, mPaint)
    }

    private fun drawHead(canvas: Canvas, headPoint: PointF) {
        canvas.drawCircle(headPoint.x, headPoint.y, HEAD_RADIUS, mPaint)
    }

    private fun drawFins(headPoint: PointF, canvas: Canvas) {
        val finsLeftStartPoint = calculatePoint(headPoint, HEAD_RADIUS * 0.9f, middleAngle + 110)
        val finsLeftEndPoint =
            calculatePoint(finsLeftStartPoint, HEAD_RADIUS * 1.3f, middleAngle + 180)
        val finsLeftControlPoint =
            calculatePoint(finsLeftStartPoint, HEAD_RADIUS * 1.8f, middleAngle + 115)
        drawFins(canvas, finsLeftStartPoint, finsLeftControlPoint, finsLeftEndPoint)
        //鱼鳍右边path需要的三个定点
        val finsRightStartPoint = calculatePoint(headPoint, HEAD_RADIUS * 0.9f, middleAngle - 110)
        val finsRightEndPoint =
            calculatePoint(finsRightStartPoint, HEAD_RADIUS * 1.3f, middleAngle - 180)
        val finsRightControlPoint =
            calculatePoint(finsRightStartPoint, HEAD_RADIUS * 1.8f, middleAngle - 115)
        drawFins(canvas, finsRightStartPoint, finsRightControlPoint, finsRightEndPoint)
    }

    private fun dowMainBody(
        headPoint: PointF,
        endPoint: PointF,
        canvas: Canvas
    ) {
        //主体左边path三个定位点
        val point1 = calculatePoint(headPoint, HEAD_RADIUS, middleAngle + 80)
        val point2 = calculatePoint(endPoint, HEAD_RADIUS * 0.7f, middleAngle + 90)
        val controlLeft = calculatePoint(headPoint, BODY_LENGTH * 0.56f, middleAngle + 130)
        //主体右边path三个定位点
        val point3 = calculatePoint(endPoint, HEAD_RADIUS * 0.7f, middleAngle - 90)
        val point4 = calculatePoint(headPoint, HEAD_RADIUS, middleAngle - 80)
        val controlRight = calculatePoint(headPoint, BODY_LENGTH * 0.56f, middleAngle - 130)
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
        canvas.drawCircle(endPoint.x, endPoint.y, 0.7f * HEAD_RADIUS, mPaint)
        val endPoint2 = calculatePoint(endPoint, 1.2f * HEAD_RADIUS, middleAngle - 180)
        canvas.drawCircle(endPoint2.x, endPoint2.y, 0.5f * HEAD_RADIUS, mPaint)
        val point1 = calculatePoint(endPoint, 0.7f * HEAD_RADIUS, middleAngle + 90)
        val point2 = calculatePoint(endPoint, 0.7f * HEAD_RADIUS, middleAngle - 90)
        val point3 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, middleAngle - 90)
        val point4 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, middleAngle + 90)
        mPath.reset()
        mPath.moveTo(point1.x, point1.y)
        mPath.lineTo(point2.x, point2.y)
        mPath.lineTo(point3.x, point3.y)
        mPath.lineTo(point4.x, point4.y)
        canvas.drawPath(mPath, mPaint)
        //1.39
        val endPoint3 = calculatePoint(endPoint2, 1.19f * HEAD_RADIUS, middleAngle + 180)
        drawSegment2(endPoint2, endPoint3, canvas)
        drawSegment3(endPoint2, canvas)
    }

    private fun drawSegment3(endPoint2: PointF, canvas: Canvas) {
        val point1 = calculatePoint(endPoint2, 1f * HEAD_RADIUS, middleAngle + 150)
        val point2 = calculatePoint(endPoint2, 1f * HEAD_RADIUS, middleAngle - 150)
        val point3 = calculatePoint(endPoint2, 1.2f * HEAD_RADIUS, middleAngle + 145)
        val point4 = calculatePoint(endPoint2, 1.2f * HEAD_RADIUS, middleAngle - 145)
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
        canvas.drawPath(mPath, mPaint)
    }

    private fun drawSegment2(endPoint2: PointF, endPoint3: PointF, canvas: Canvas) {
        val point1 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, middleAngle + 90)
        val point2 = calculatePoint(endPoint2, 0.5f * HEAD_RADIUS, middleAngle - 90)
        val point3 = calculatePoint(endPoint3, 0.2f * HEAD_RADIUS, middleAngle - 90)
        val point4 = calculatePoint(endPoint3, 0.2f * HEAD_RADIUS, middleAngle + 90)
        mPath.reset()
        mPath.moveTo(point1.x, point1.y)
        mPath.lineTo(point2.x, point2.y)
        mPath.lineTo(point3.x, point3.y)
        mPath.lineTo(point4.x, point4.y)
        canvas.drawPath(mPath, mPaint)

        canvas.drawCircle(endPoint3.x, endPoint3.y, 0.2f * HEAD_RADIUS, mPaint)
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
        mPaint.color = Color.parseColor("#a1010203")
        canvas.drawPath(mPath, mPaint)
    }

    private fun calculatePoint(startPoint: PointF, length: Float, angel: Double): PointF {
        val dataX = (cos(Math.toRadians(angel)) * length).toFloat()
        val dataY = (-sin(Math.toRadians(angel)) * length).toFloat()
        return PointF(startPoint.x + dataX, startPoint.y + dataY)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
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