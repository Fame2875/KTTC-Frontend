package com.pskmax.kkct_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    var loginKKCT: TextView? = null
    var editEmail: EditText? = null
    var editPassword: EditText? = null
    var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        loginKKCT = findViewById<TextView>(R.id.registerKKCT)
        editEmail = findViewById<EditText>(R.id.editEmail)
        editPassword = findViewById<EditText>(R.id.editPassword)

        /*
        btnLogin!!.setOnClickListener{
            //action after click login button
        }
        */
    }
}