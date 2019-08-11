package edu.javeriana.fibinacci

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView

class SequenceList : AppCompatActivity() {

    private lateinit var sequenceLayout: LinearLayout
    private var sequenceNumber: Int = 0
    private val fibonacci = mutableListOf(0, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_list)
        this.setupUi()
    }

    private fun setupUi() {
        this.sequenceLayout = findViewById(R.id.linear_layout_squence)
        this.sequenceNumber = intent.getIntExtra("SequenceNumber", 0)
        this.populateSequence()
    }

    private fun populateSequence() {
        this.calculateSequence()
        for (e in this.fibonacci) {
            val element = TextView(this)
            element.text = e.toString()
            this.sequenceLayout.addView(element)
        }
    }

    private fun calculateSequence() {
        for (i in 1..this.sequenceNumber - 2) {
            val t1 = fibonacci[fibonacci.lastIndex]
            val t2 = fibonacci[fibonacci.lastIndex - 1]
            fibonacci += (t1 + t2)
        }
    }
}
