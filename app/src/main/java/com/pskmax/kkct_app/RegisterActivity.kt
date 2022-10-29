package com.pskmax.kkct_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import com.pskmax.kkct_app.data.Register

class RegisterActivity : AppCompatActivity() {

    var editEmail: EditText? = null
    var editPassword: EditText? = null
    var editCPassword: EditText? = null
    var editId: EditText? = null
    var btnRegister: Button? = null
    var btnToLogin: Button? = null

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        editEmail = findViewById<EditText>(R.id.editEmail)
        editPassword = findViewById<EditText>(R.id.editPassword)
        editCPassword = findViewById<EditText>(R.id.editCPassword)
        editId = findViewById<EditText>(R.id.editId)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        btnToLogin = findViewById<Button>(R.id.btnToLogin)

        val regScreen = Register()
        btnRegister!!.setOnClickListener{
            if (!isEmailValid((editEmail?.text).toString())){
                println("Your Email is not correct")
            }
            else if (editPassword?.length()!! < 8){
                println("Your pass must be between 8-15 characters")
            }
            else if (!checkPassword((editPassword?.text).toString())){
                println("Your password must have at least 1 Uppercase, Lowercase and Numeric")
            }
            else if ((editPassword?.text).toString() != (editCPassword?.text).toString()){
                println("Your confirm password is not correct")
            }
            else if (editId?.length()!! < 13){
                println("Your Citizen ID must have 13 characters")
            }
            else{
                val intent = Intent(this@RegisterActivity,HomeActivity::class.java)
                regScreen.updateUserInfo((editEmail?.text).toString(),(editPassword?.text).toString(),(editId?.text).toString())
                startActivity(intent)
            }
        }

        btnToLogin!!.setOnClickListener{
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}