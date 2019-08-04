package ru.you11.haabtestproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstCircle.angle = 165.0
        firstCircle.setOnClickListener {
            firstCircle.setMoving(25f)
        }
    }
}
