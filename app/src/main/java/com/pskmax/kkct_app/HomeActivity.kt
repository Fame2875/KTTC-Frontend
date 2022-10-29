package com.pskmax.kkct_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.pskmax.kkct_app.data.Customer

class HomeActivity : AppCompatActivity() {

    var profileCardView: CardView? = null
    var creditCardView: CardView? = null
    var logCardView: CardView? = null

    private fun getFromBackEnd(email:String,pwd:String): MutableList<String>{
        //////// id,email,pwd,token ///////
        val element = mutableListOf<String>("1234567890",email,pwd,"1579900999999")
        return element
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val user = Customer()
        var login_email:String = ""
        var login_pwd:String = ""
        var login_token:String = ""

        //// รับค่าจาก LoginActivity ////
        intent.extras?.get("ui_email")?.let {
            login_email = it.toString()
        }
        intent.extras?.get("ui_pwd")?.let {
            login_pwd = it.toString()
        }
        intent.extras?.get("token")?.let {
            login_token = it.toString()
        }

        ////// api query ////
        user.fetchUserInfo(getFromBackEnd(login_email,login_pwd),login_token)
        println("${user.getUserId()} ${user.getUserEmail()} ${user.getUserPwd()} ${user.getUserCitizenId()} ${user.getUserToken()}")

        profileCardView = findViewById<CardView>(R.id.profileCardView)
        creditCardView = findViewById<CardView>(R.id.creditCardView)
        logCardView = findViewById<CardView>(R.id.logCardView)

        profileCardView!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,ProfileActivity::class.java)
            //// ส่งค่าไป ProfileActivity intent.putExtra(key,var) ////
            intent.putExtra("user_email",user.getUserEmail())
            intent.putExtra("user_citizen_id",user.getUserCitizenId())
            startActivity(intent)
        }

        creditCardView!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,CreditActivity::class.java)
            startActivity(intent)
        }

        logCardView!!.setOnClickListener{
            val intent = Intent(this@HomeActivity,LogActivity::class.java)
            startActivity(intent)
        }

    }
}