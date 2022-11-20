package com.pskmax.kkct_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.pskmax.kkct_app.myVolley.IVolley
import com.pskmax.kkct_app.myVolley.MyVolleyRequest
import org.json.JSONObject

class LoginActivity : AppCompatActivity(),IVolley{

    var editEmail: EditText? = null
    var editPassword: EditText? = null
    var btnLogin: Button? = null
    var btnToRegister: Button? = null
    var res : String? = null

    //กันไม่ให้ย้อนกลับ
    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            moveTaskToBack(true)
            finishAffinity()
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    // รับ token
    override fun onResponse(response: String) {
        println(response)
         //JSONObject(response)
        res = JSONObject(response).getString("access_token")
        println(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginv2)
        supportActionBar?.hide()

        editEmail = findViewById<TextInputEditText>(R.id.editEmail)
        editPassword = findViewById<TextInputEditText>(R.id.editPassword)
        btnToRegister = findViewById<AppCompatButton>(R.id.btnToRegister)
        btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)

        btnLogin!!.setOnClickListener{
            // ส่งค่าไป Back
            customerLogin((editEmail?.text).toString(),(editPassword?.text).toString())
            Handler().postDelayed({
                //wait for token
                if(res != null){
                    val intent = Intent(this@LoginActivity, LoadActivity::class.java)
                    intent.putExtra("ui_email",(editEmail?.text).toString())
                        .putExtra("ui_pwd",(editPassword?.text).toString())
                        .putExtra("token",res)
//                    println((editEmail?.text).toString())
//                    println((editPassword?.text).toString())
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext,"Your email or password was incorrect", Toast.LENGTH_SHORT).show()
                }
            }, 1000)
        }

        btnToRegister!!.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun customerLogin(email: String  ,password : String ){
        val url = "http://10.0.2.2:8093/api/login?email=${email}&password=${password}"
        //test url
        //val url = "https://jsonplaceholder.typicode.com/todos/1"
        println(url)
        MyVolleyRequest.getInstance(this@LoginActivity,this@LoginActivity)
            .getRequest(url)

    }
}