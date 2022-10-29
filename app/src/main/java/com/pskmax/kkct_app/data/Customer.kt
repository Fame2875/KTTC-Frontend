package com.pskmax.kkct_app.data

class Customer (private var email : String?=null,
                private var pwd : String?=null,
                private var citizen_id:String?=null){

    private var id : String? = null
    private var user_token : String? = null

    fun setUserToken(token :String){
        this.user_token = token
    }

    fun getUserToken():String?{
        return this.user_token
    }

    fun setUserId(DB :String){
        this.id = DB
    }

    fun getUserId():String?{
        return this.id
    }

    fun setUserEmail(db_email:String){
        this.email = db_email
    }

    fun getUserEmail():String?{
        return this.email
    }

    fun setUserPwd(db_pwd:String?){
        this.pwd = db_pwd
    }

    fun getUserPwd():String?{
        return this.pwd
    }

    fun setUserCitizenId(db_citizen_id:String?){
        this.citizen_id
    }

    fun getUserCitizenId():String?{
        return this.citizen_id
    }

    fun fetchUserInfo(){
        ///////////search in Database//////////
        setUserId("From DB")
        setUserEmail("From DB")
        setUserPwd("From DB")
        setUserCitizenId("From DB")
        setUserToken("From DB")
    }


}