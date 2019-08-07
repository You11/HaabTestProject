package ru.you11.haabtestproject

import android.graphics.Point
import android.util.SparseArray

interface MainContract {

    interface View {

    }

    interface Presenter {

        fun setCircleParentViewData(viewSize: Point)

        fun setCircleParams(vararg params: CircleParams)

        fun getNewParams(): List<CircleParams>
    }
}