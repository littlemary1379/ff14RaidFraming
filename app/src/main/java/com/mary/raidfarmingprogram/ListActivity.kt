package com.mary.raidfarmingprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mary.raidfarmingprogram.util.ActivityUtil

class ListActivity : AppCompatActivity() {

    private lateinit var textViewMake : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findView()
        setListener()
    }

    private fun findView() {
        textViewMake = findViewById(R.id.textViewMake)
    }

    private fun setListener() {
        textViewMake.setOnClickListener {
            ActivityUtil.startActivityWithoutFinish(this, PartyMakingActivity::class.java)
        }
    }
}