package ru.you11.haabtestproject

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.*

class CircleView : View {

    lateinit var circleParams: CircleParams

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setBackgroundResource(R.drawable.circle_shape)
    }

    fun move() {
        x += circleParams.xDist
        y += circleParams.yDist

        circleParams.updateCenter(x, y)

        invalidate()
    }
}