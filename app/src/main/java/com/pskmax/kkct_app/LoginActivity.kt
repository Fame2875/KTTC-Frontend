package com.pskmax.kkct_app

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.pskmax.kkct_app.data.Customer
import com.pskmax.kkct_app.data.Login
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    var registerKKCT: TextView? = null
    var loginKKCT: TextView? = null
    var editEmail: EditText? = null
    var editPassword: EditText? = null
    var btnLogin: Button? = null
    var btnToRegister: Button? = null

    private val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    private fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginv2)
        supportActionBar?.hide()

        // ทดสอบ Animation
        val test_anim = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        //val title = findViewById(R.id.loginKKCT) as TextView
        //title.startAnimation(test_anim)
        //////////////////////////////////

        //loginKKCT = findViewById<TextView>(R.id.loginKKCT)
        editEmail = findViewById<TextInputEditText>(R.id.editEmail)
        editPassword = findViewById<TextInputEditText>(R.id.editPassword)
        btnToRegister = findViewById<AppCompatButton>(R.id.btnToRegister)
        btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)



        val loginScreen = Login()
        val dummy_token:String = "12345@a1234"

        // temporary correct email and password
        loginScreen.setDBEmail("test@hotmail.com")
        loginScreen.setDBPwd("12345")

        btnLogin!!.setOnClickListener{
            // match email and password
            loginScreen.set_Email_UI((editEmail?.text).toString())
            loginScreen.setUiPwd((editPassword?.text).toString())
            println(loginScreen.get_Email_UI())
            println(loginScreen.getUiPwd())
            if (!isEmailValid((editEmail?.text).toString())){
                println("Your Email is not correct")
            }
            else if(!loginScreen.isRegister((editEmail?.text).toString())) {
                println("You are dont have account yet")
            }
            else{
                if (!loginScreen.checkForLogin()){
                    println("You fucked up")
                }
            // if email and password are correct
                else {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    println("Nice work")
                    loginScreen.generateToken()
                    // pass ค่า user_email , user_password, token -> HomeActivity //
                    intent.putExtra("ui_email",(editEmail?.text).toString())
                    intent.putExtra("ui_pwd",(editPassword?.text).toString())
                    intent.putExtra("token",dummy_token)
                    //
                    startActivity(intent)
                }
            }
        }

        btnToRegister!!.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}