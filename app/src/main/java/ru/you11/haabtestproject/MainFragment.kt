package ru.you11.haabtestproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainFragment : Fragment(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstCircle.angle = 120.0
        secondCircle.angle = 300.0
    }

    override fun onStart() {
        super.onStart()

        firstCircle.setOnClickListener {
            moveCircle(firstCircle)
        }

        secondCircle.setOnClickListener {
            moveCircle(secondCircle)
        }
    }

    private fun moveCircle(circle: Circle) {
        launch(Dispatchers.IO) {
            while (true) {
                activity?.runOnUiThread { circle.moveCircle(25f) }
                delay(25)
            }
        }
    }

    private fun getViewModel() = ViewModelProviders.of(this).get(MainViewModel::class.java)
}