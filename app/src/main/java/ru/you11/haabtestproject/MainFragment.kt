package ru.you11.haabtestproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import android.view.ViewTreeObserver.OnGlobalLayoutListener


class MainFragment : Fragment(), CoroutineScope, MainContract.View {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Job()

    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstCircle.angle = 120.0
        secondCircle.angle = 300.0

        view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                moveCircle(firstCircle)
                moveCircle(secondCircle)
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun moveCircle(circle: Circle) {
        launch(Dispatchers.IO) {
            while (true) {
                activity?.runOnUiThread { circle.moveCircle(25f) }
                delay(25)
            }
        }
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}