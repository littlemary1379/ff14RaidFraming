package com.mary.raidfarmingprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ListActivity : AppCompatActivity() {

    private lateinit var textViewMake : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findView()
    }

    private fun findView() {

    }
}