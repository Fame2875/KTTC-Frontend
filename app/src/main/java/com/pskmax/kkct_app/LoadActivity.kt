package com.pskmax.kkct_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class LoadActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        supportActionBar?.hide()
        var login_email:String = ""
        var login_pwd:String = ""
        var login_token:String = ""

        //// รับค่าจาก LoginActivity ////
        intent.extras?.get("ui_email")?.let {
            login_email = it.toString()
            println("${login_email } LoadActivity")
        }
        intent.extras?.get("ui_pwd")?.let {
            login_pwd = it.toString()
            println("${login_pwd } LoadActivity")
        }
        intent.extras?.get("token")?.let {
            login_token = it.toString()
            println("${login_token } LoadActivity")
        }

        val intent = Intent(this@LoadActivity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        // splash screen
        intent.putExtra("ui_email",login_email)
        intent.putExtra("ui_pwd",login_pwd)
        intent.putExtra("token",login_token)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 1000)
    }
}
