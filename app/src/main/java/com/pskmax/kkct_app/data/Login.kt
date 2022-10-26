package com.pskmax.kkct_app.data

class Login(
    email_ui: String,
    email_db: String,
    pwd_ui: String,
    pwd_db: String){

    private var email_ui: String? = null
    private var email_db: String? = null
    private var pwd_ui: String? = null
    private var pwd_db: String? = null

    init {
        this.set_Email_DB(email_db)
        this.set_Email_UI(email_ui)
        this.set_Pwd_DB(pwd_db)
        this.set_Pwd_UI(pwd_ui)

    }

    private fun set_Email_DB(a : String){
        this.email_db = a
    }

    private fun set_Pwd_DB(a : String){
        this.pwd_db = a
    }

    private fun set_Email_UI(a : String){
        this.email_ui = a
    }

    private fun set_Pwd_UI(a : String){
        this.pwd_ui = a
    }

    fun compare () : Boolean{
        if (this.email_ui == this.email_db && this.pwd_ui == this.pwd_db) {
            return true
        }
        return false
    }
}