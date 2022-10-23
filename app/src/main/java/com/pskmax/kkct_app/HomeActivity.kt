package com.pskmax.kkct_app

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {

    var profileIcon: ImageView? = null
    var profileText: TextView? = null
    var creditIcon: ImageView? = null
    var creditText: TextView? = null
    var historyIcon: ImageView? = null
    var historyText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        profileIcon = findViewById<ImageView>(R.id.profileIcon)
        profileText = findViewById<TextView>(R.id.profileText)
        creditIcon = findViewById<ImageView>(R.id.creditIcon)
        creditText = findViewById<TextView>(R.id.creditText)
        historyIcon = findViewById<ImageView>(R.id.historyIcon)
        historyText = findViewById<TextView>(R.id.historyText)

        profileIcon!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,ProfileActivity::class.java)
            startActivity(intent)
        }

        profileText!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,ProfileActivity::class.java)
            startActivity(intent)
        }

        creditIcon!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,CreditActivity::class.java)
            startActivity(intent)
        }

        creditText!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,CreditActivity::class.java)
            startActivity(intent)
        }

        historyIcon!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,HistoryActivity::class.java)
            startActivity(intent)
        }

        historyText!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}