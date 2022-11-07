package com.pskmax.kkct_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // splash screen
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, OverviewViewModel::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}