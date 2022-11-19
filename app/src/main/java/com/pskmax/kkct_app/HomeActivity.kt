package com.pskmax.kkct_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.pskmax.kkct_app.data.Customer
import com.pskmax.kkct_app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    var login_email:String = ""
    var login_pwd:String = ""
    var login_token:String = ""
    var profileCardView: CardView? = null
    var creditCardView: CardView? = null
    var logCardView: CardView? = null

    private fun getFromBackEnd(email:String,pwd:String): MutableList<String>{
        //////// id,email,pwd,token ///////
        val element = mutableListOf<String>("1234567890",email,pwd,"1579900999999")
        return element
    }

    private fun changeFragment(fragment : Fragment) {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()
        //fragmentTransaction.replace(R.id.frame_layout,fragment)
        //fragmentTransaction.commit()
        //val bundle = Bundle()

        //bundle.putString("usEmail",login_email)
        //bundle.putString("usPwd",login_pwd)
        //bundle.putString("usToken",login_token)
        //println("Sending data")
        //fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //supportFragmentManager.beginTransaction().replace(R.id.frame_layout,HomeFragment()).commit()
        supportActionBar?.hide()

        val user = Customer()
        var login_email:String = ""
        var login_pwd:String = ""
        var login_token:String = ""

        //// รับค่าจาก LoadActivity ////
        intent.extras?.get("ui_email")?.let {
            login_email = it.toString()
            println("${login_email } HomeActivity")
        }
        intent.extras?.get("ui_pwd")?.let {
            login_pwd = it.toString()
            println("${login_pwd } HomeActivity")
        }
        intent.extras?.get("token")?.let {
            login_token = it.toString()
            println("${login_token } HomeActivity")
        }
        //changeFragment(HomeFragment())
        ////// bundle ค่าจาก home ไป homefragment ////
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString("usEmail",login_email)
        bundle.putString("usPwd",login_pwd)
        bundle.putString("usToken",login_token)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()

        ////// api query ////
        user.fetchUserInfo(getFromBackEnd(login_email,login_pwd),login_token)
        //println("${user.getUserId()} ${user.getUserEmail()} ${user.getUserPwd()} ${user.getUserCitizenId()} ${user.getUserToken()}")

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> changeFragment(HomeFragment())
                R.id.log -> changeFragment(LogFragment())
                R.id.settings -> changeFragment(SettingsFragment())
            }
            true
        }


//        profileCardView = findViewById<CardView>(R.id.profileCardView)
//        creditCardView = findViewById<CardView>(R.id.creditCardView)
//        logCardView = findViewById<CardView>(R.id.logCardView)
//
//        profileCardView!!.setOnClickListener{
//            val intent = Intent(this@HomeActivity,ProfileActivity::class.java)
//            //// ส่งค่าไป ProfileActivity intent.putExtra(key,var) ////
//            intent.putExtra("user_email",user.getUserEmail())
//            intent.putExtra("user_citizen_id",user.getUserCitizenId())
//            startActivity(intent)
//        }
//
//        creditCardView!!.setOnClickListener{
//            val intent = Intent(this@HomeActivity,CreditActivity::class.java)
//            startActivity(intent)
//        }
//
//        logCardView!!.setOnClickListener{
//            val intent = Intent(this@HomeActivity,LogActivity::class.java)
//            startActivity(intent)
//        }

    }
}