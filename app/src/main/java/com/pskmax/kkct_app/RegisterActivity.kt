package com.pskmax.kkct_app

//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.JsonObjectRequest
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pskmax.kkct_app.data.Register
import com.pskmax.kkct_app.myVolley.IVolley
import com.pskmax.kkct_app.myVolley.MyVolleyRequest
import org.json.JSONObject


class RegisterActivity : AppCompatActivity(), IVolley {

    var editEmail: EditText? = null
    var editPassword: EditText? = null
    var editCPassword: EditText? = null
    var editId: EditText? = null
    var btnRegister: Button? = null
    var btnToLogin: Button? = null
    var url:String = "http://localhost:8080/api/signup"

    override fun onResponse(response: String) {
        //ส่ง response กลับมา
        println(response)
    }

    private fun checkPassword(string: String) : Boolean{
        val (letters , numeric) = string.partition { it.isLetter() }
        val (upperCases, lowerCases) = letters.partition { it.isUpperCase() }
        if (upperCases.isNotEmpty() && lowerCases.isNotEmpty() && numeric.isNotEmpty()){
            return  true
        }
        return  false
    }

    private val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    private fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        url = "http://10.0.2.2:8093/api/signup"
        editEmail = findViewById<EditText>(R.id.editEmail)
        editPassword = findViewById<EditText>(R.id.editPassword)
        editCPassword = findViewById<EditText>(R.id.editCPassword)
        editId = findViewById<EditText>(R.id.editID)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        btnToLogin = findViewById<Button>(R.id.btnToLogin)

        val regScreen = Register()
        btnRegister!!.setOnClickListener{
            //ส่งค่าไป Back
            register00((editEmail?.text).toString(),(editPassword?.text).toString(), (editId?.text).toString())

            if (!isEmailValid((editEmail?.text).toString())){
                println("Your Email is not correct")
                Toast.makeText(applicationContext,"Your Email is not correct", Toast.LENGTH_SHORT).show()
            }
            else if (editPassword?.length()!! < 8){
                println("Your pass must be between 8-15 characters")
                Toast.makeText(applicationContext,"Your pass must be between 8-15 characters", Toast.LENGTH_SHORT).show()
            }
            else if (!checkPassword((editPassword?.text).toString())){
                println("Your password must have at least 1 Uppercase, Lowercase and Numeric")
                Toast.makeText(applicationContext,"Your password must have at least 1 Uppercase, Lowercase and Numeric", Toast.LENGTH_SHORT).show()

            }
            else if ((editPassword?.text).toString() != (editCPassword?.text).toString()){
                println("Your confirm password is not correct")
                Toast.makeText(applicationContext,"Your confirm password is not correct", Toast.LENGTH_SHORT).show()
            }
            else if (editId?.length()!! < 13){
                println("Your Citizen ID must have 13 characters")
                Toast.makeText(applicationContext,"Your Citizen ID must have 13 characters", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                regScreen.updateUserInfo((editEmail?.text).toString(),(editPassword?.text).toString(),(editId?.text).toString())
                Toast.makeText(applicationContext,"Sign Up Complete!", Toast.LENGTH_SHORT).show()
                // ลบ Stack ของ Intent///////////////
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        btnToLogin!!.setOnClickListener{

            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            // ลบ Stack ของ Intent///////////////
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            /////////////////////////////////////
            startActivity(intent)
        }
    }
    //ของโอ๊ต
    private fun register00(name: String,password : String,citizenID:String){

        val regJson = JSONObject()
        regJson.put("email",name)
        regJson.put("password",password)
        regJson.put("citizenID", citizenID)

        //println(regJson.keys())
        MyVolleyRequest.getInstance(this@RegisterActivity,this@RegisterActivity)
            .postRequestWithBody(url,regJson)
    }

}