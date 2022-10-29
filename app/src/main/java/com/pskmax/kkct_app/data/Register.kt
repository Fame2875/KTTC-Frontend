package com.pskmax.kkct_app.data

class Register (private var email:String? = null,
                private var password:String? = null,
                private var citizen_id:String? = null){

    fun setUserEmail(ui_email:String?){
        this.email = ui_email
    }

    fun getUserEmail():String?{
        return this.email
    }

    fun setUserPwd(ui_pwd:String?){
        this.password = ui_pwd
    }

    fun getUserPwd():String?{
        return this.password
    }

    fun setUserCitizenId(citizen_id:String?){
        this.citizen_id = citizen_id
    }

    fun getUserCitizenId():String?{
        return this.citizen_id
    }

    fun updateUserInfo(email: String?,pwd: String?,citizen_id: String?){
        setUserEmail(email)
        setUserPwd(pwd)
        setUserCitizenId(citizen_id)
        ////// create transection //////


    }

}