package com.kxs109.gestureDetector.photoView

interface OnGestureListener {
    fun onDrag(dx: Float, dy: Float)
    fun onFlying(startX: Float, startY: Float, velocityX: Float, velocityY: Float)
    fun onScale(scaleFactor: Float, focusX: Float, focusY: Float)
    fun onScale(scaleFactor: Float, focusX: Float, focusY: Float, dx: Float, dy: Float)
}