package ru.you11.haabtestproject

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.*

class Circle : View {

    var angle = 0.0
    var isMoving = false

    private val parentWidth by lazy { (parent as View).width }
    private val parentHeight by lazy { (parent as View).height }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundResource(R.drawable.circle_shape)
    }

    fun moveCircle(stepDistance: Float) {
        var xDist = getXDist(stepDistance)
        var yDist = getYDist(stepDistance)

        if (isXOutOfBounds(xDist)) {
            angle = 360 - angle
            xDist = getXDist(stepDistance)
        }

        if (isYOutOfBounds(yDist)) {
            angle = if (angle <= 180) {
                180 - angle
            } else {
                540 - angle
            }
            yDist = getYDist(stepDistance)
        }

        x += xDist
        y += yDist

//        log("angle: $angle")
//        log("cos: ${cos(Math.toRadians(angle))}")
//        log("sin: ${sin(Math.toRadians(angle))}")
//        log("x dist: $xDist")
//        log("y dist: $yDist")

        invalidate()
    }

    private fun getXDist(stepDistance: Float) = (stepDistance * sin(Math.toRadians(angle))).toFloat()
    private fun getYDist(stepDistance: Float) = -(stepDistance * cos(Math.toRadians(angle))).toFloat()

    private fun isXOutOfBounds(moveDistance: Float) = isOutOfBounds(x, moveDistance, width, parentWidth)
    private fun isYOutOfBounds(moveDistance: Float) = isOutOfBounds(y, moveDistance, height, parentHeight)

    private fun isOutOfBounds(coordinate: Float, moveDistance: Float, viewSide: Int, parentSide: Int): Boolean =
        coordinate + moveDistance + viewSide > parentSide || coordinate + moveDistance < 0

    private fun log(message: String) {
        Log.d("meow", message)
    }

}