package com.pskmax.kkct_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // ทดสอบ Animation
        val rotate = AnimationUtils.loadAnimation(this,R.anim.rotate)
        val fadein = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        val logo_up = findViewById<ImageView>(R.id.logo_up)
        val logo_down = findViewById<ImageView>(R.id.logo_down)
        logo_up.startAnimation(rotate)
        logo_down.startAnimation(fadein)
        //////////////////////////////////

        // splash screen
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}