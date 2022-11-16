package com.pskmax.kkct_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pskmax.kkct_app.data.Login
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.textfield.TextInputEditText
import com.pskmax.kkct_app.myVolley.IVolley
import com.pskmax.kkct_app.myVolley.MyVolleyRequest
import org.json.JSONObject

class LoginActivity : AppCompatActivity(),IVolley{
    override fun onResponse(response: String) {
        println(response)
    }

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
        //val test_anim = AnimationUtils.loadAnimation(this,R.anim.fade_in);
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
            //customerLogin((editEmail?.text).toString(),(editPassword?.text).toString())
            // match email and password
            loginScreen.set_Email_UI((editEmail?.text).toString())
            loginScreen.setUiPwd((editPassword?.text).toString())
            println(loginScreen.get_Email_UI())
            println(loginScreen.getUiPwd())
            if (!isEmailValid((editEmail?.text).toString())){
                println("Your Email is not correct")
                Toast.makeText(applicationContext,"Your Email is not correct",Toast.LENGTH_SHORT).show()
            }
            else if(!loginScreen.isRegister((editEmail?.text).toString())) {
                println("You are dont have account yet")
                Toast.makeText(applicationContext,"You are dont have account yet",Toast.LENGTH_SHORT).show()
            }
            else{
                if (!loginScreen.checkForLogin()){
                    println("You fucked up")
                    Toast.makeText(applicationContext,"Your Email is not correct",Toast.LENGTH_SHORT).show()
                }
            // if email and password are correct
                else {
//                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    val intent = Intent(this@LoginActivity, LoadActivity::class.java)
                    println("Nice work")
                    Toast.makeText(applicationContext,"Please wait!",Toast.LENGTH_SHORT).show()
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

    private fun customerLogin(email: String  ,password : String ){
        val url = "http://10.0.2.2:8093/api/login?email=${email}&password=${password}"
        //test url
        //val url ="https://jsonplaceholder.typicode.com/todos/1"
        println(url)
        MyVolleyRequest.getInstance(this@LoginActivity,this@LoginActivity)
            .getRequest(url)
//        val regJson = JSONObject()
//        // รอค่า key ที่ database ที่ถูกต้องอีกที
//        regJson.put("email",email)
//        regJson.put("password",password)
//        println("email:${email}")
//        println("password:${password}")
//        // 10.0.2.2 คือค่า loopback ของ android studio , 8080 คือ port
//        val url = "http://10.0.2.2:8093/api/login?email=${email}&password=${password}"
//        println(url)
//        val getRequest = JsonObjectRequest(Request.Method.GET,url,null, Response.Listener { response ->  })
//
//
//
//        val queue = Volley.newRequestQueue(this)
//        queue.add(jsonRequest)
    }
}