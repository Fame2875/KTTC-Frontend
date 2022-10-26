package com.pskmax.kkct_app.data

class Customer (var email : String,var pwd : String){
    private val lg = Login(email,"test@gg.com",pwd,"Aa1234567")

    fun checkLogin() : Boolean{
        return lg.compare()
    }
}