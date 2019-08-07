package ru.you11.haabtestproject

import android.graphics.Point

class MainPresenter(private val fragment: MainFragment) : MainContract.Presenter {

    init {
        fragment.presenter = this
    }

    private lateinit var circleParentViewSize: Point
    private val circleParams = ArrayList<CircleParams>()

    override fun setCircleParentViewData(viewSize: Point) {
        circleParentViewSize = Point(viewSize.x, viewSize.y)
    }

    override fun setCircleParams(vararg params: CircleParams) {
        circleParams.addAll(params)
    }

    override fun getNewParams(): List<CircleParams> {

        val updatedParams = ArrayList<CircleParams>()

        circleParams.forEach {
            val params = checkForWallCollision(it)
            if (params != null) updatedParams.add(params)
        }

//        checkForCirclesCollision(circleParams)
        
        return updatedParams
    }

    private fun checkForWallCollision(circleParams: CircleParams): CircleParams? {

        var wasUpdated = false
        
        with(circleParams) {
            if (isOutOfBounds(center.x, xDist, radius, circleParentViewSize.x)) {
                updateAngle(CircleParams.Side.VERTICAL)
                wasUpdated = true
            }
            if (isOutOfBounds(center.y, yDist, radius, circleParentViewSize.y)) {
                updateAngle(CircleParams.Side.HORIZONTAL)
                wasUpdated = true
            }
        }

        return if (wasUpdated) circleParams else null
    }

    private fun checkForCirclesCollision(circleParams: ArrayList<CircleParams>) {

    }

    private fun isOutOfBounds(centerCoordinate: Float, moveDistance: Float, radius: Int, parentSide: Int): Boolean =
        centerCoordinate + moveDistance + radius > parentSide || centerCoordinate - radius + moveDistance < 0
}