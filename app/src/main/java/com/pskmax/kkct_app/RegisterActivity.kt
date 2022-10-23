package com.pskmax.kkct_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText

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

        btnRegister!!.setOnClickListener{
            if (editPassword?.length()!! < 8){
                println("Your pass must be between 8-15 characters")
            }
            else if (!checkPassword((editPassword?.text).toString())){
                println("Your password must have at least 1 Uppercase, Lowercase and Numeric")
            }
            else if ((editPassword?.text).toString() != (editCPassword?.text).toString()){
                println("Your confirm password is not correct")
            }
            else{
                val intent = Intent(this@RegisterActivity,HomeActivity::class.java)
                startActivity(intent)
            }
        }

        btnToLogin!!.setOnClickListener{
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}