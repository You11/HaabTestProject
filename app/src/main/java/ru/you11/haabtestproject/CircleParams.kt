package ru.you11.haabtestproject

import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class CircleParams(
    view: CircleView,
    stepDistance: Float
) {

    var id: Int = view.id
    val radius = view.width / 2
    var center = Center(0f, 0f)
    var xDist = 0f
    var yDist = 0f
    var angle = Random.nextFloat() * 360
        set(value) {
            field = value
            updateDistance()
        }
    var stepDistance: Float = stepDistance
        set(value) {
            field = value
            updateDistance()
        }

    init {
        updateDistance()
        updateCenter(view.x, view.y)
    }

    fun updateCenter(x: Float, y: Float) {
        center = Center(x + radius, y + radius)
    }

    fun updateAngle(side: Side) {
        angle = if (side == Side.VERTICAL) {
            360 - angle
        } else {
            if (angle <= 180) {
                180 - angle
            } else {
                540 - angle
            }
        }
    }

    private fun updateDistance() {
        updateXDist()
        updateYDist()
    }

    private fun updateXDist() {
        xDist = (stepDistance * sin(Math.toRadians(angle.toDouble()))).toFloat()
    }

    private fun updateYDist() {
        yDist = -(stepDistance * cos(Math.toRadians(angle.toDouble()))).toFloat()
    }

    enum class Side {
        VERTICAL, HORIZONTAL
    }

    class Center(
        val x: Float,
        val y: Float
    )
}