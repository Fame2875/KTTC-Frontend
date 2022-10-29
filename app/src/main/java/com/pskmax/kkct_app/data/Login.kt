package com.pskmax.kkct_app.data


class Login(
    private var email_ui: String? =null,
    private var email_db: String? = null,
    private var pwd_ui: String? = null,
    private var pwd_db: String? = null){

    private var token:String? = null

    fun getToken():String?{
        return this.token
    }

    fun set_Email_UI(ui_email: String?){
        this.email_ui = ui_email
    }

    fun get_Email_UI():String?{
        return email_ui
    }

    fun setDBEmail(DB :String){
        ///////////connect backend -> search in Database//////////
        this.email_db = DB
    }

    fun getDBEmail():String?{
        return email_db
    }

    fun setUiPwd(ui_pwd:String?){
        this.pwd_ui = ui_pwd
    }

    fun getUiPwd():String?{
        return pwd_ui
    }

    fun setDBPwd(DB :String){
        ///////////connect backend -> search in Database//////////
        this.pwd_db = DB
    }

    fun getDBPwd():String?{
        return pwd_db
    }

    fun isRegister(ui_email :String):Boolean{
        ///////////connect backend -> search in Database//////////
        if(ui_email == getDBEmail()) {
            return true
        }
        return false
    }

    fun generateToken(){
        ////////////// token section ///////////////
        this.token = "from generate"
    }

    fun checkForLogin () : Boolean{
        if (get_Email_UI() == getDBEmail() && getUiPwd() == getDBPwd()) {
            return true
        }
        return false
    }
}