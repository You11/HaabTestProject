package ru.you11.haabtestproject

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import android.view.ViewTreeObserver.OnGlobalLayoutListener


class MainFragment : Fragment(), CoroutineScope, MainContract.View {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val firstParams = CircleParams(firstCircle, 20.0f)
                val secondParams = CircleParams(secondCircle, 40.0f)

                firstCircle.circleParams = firstParams
                secondCircle.circleParams = secondParams

                presenter.setCircleParams(firstParams, secondParams)
                presenter.setCircleParentViewData(Point(rootLayout.width, rootLayout.height))

                launch(Dispatchers.IO) {
                    while (true) {
                        val newParams = presenter.getNewParams()
                        val circleViews = ArrayList<CircleView>()
                        circleViews.add(firstCircle)
                        circleViews.add(secondCircle)
                        newParams.forEach { param ->
                            circleViews.forEach { view ->
                                if (param.id == view.id) {
                                    view.circleParams = param
                                }
                            }
                        }
                        activity?.runOnUiThread {
                            circleViews.forEach {
                                it.move()
                            }
                        }
                        delay(25)
                    }
                }

                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}