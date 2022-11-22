package com.pskmax.kkct_app.data

import androidx.appcompat.app.AppCompatActivity
import com.pskmax.kkct_app.myVolley.IVolley
import com.pskmax.kkct_app.myVolley.MyVolleyRequest

class Login(private var email:String? = null,
            private var password:String? = null) {

    fun setUserEmail(email: String){
        this.email = email
    }

    fun setUserPwd(pwd: String){
        this.password = pwd
    }

    fun getUserEmail():String?{
        return this.email
    }

    fun getUserPwd():String?{
        return this.password
    }

    fun setLogin(email:String, pwd:String){
        setUserEmail(email)
        setUserPwd(pwd)

    }

    fun login(context: AppCompatActivity  ,volley : IVolley ){
        val url = "http://10.0.2.2:8093/api/login?email=${getUserEmail()}&password=${getUserPwd()}"
        println(url)
        MyVolleyRequest.getInstance(context,volley)
            .getRequest(url)
        println("Login Done")
    }

}