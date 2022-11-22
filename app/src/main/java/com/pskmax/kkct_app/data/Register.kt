package com.pskmax.kkct_app.data
import androidx.appcompat.app.AppCompatActivity
import com.pskmax.kkct_app.myVolley.IVolley
import com.pskmax.kkct_app.myVolley.MyVolleyRequest
import org.json.JSONObject

class Register (private var email:String? = null,
                private var password:String? = null,
                private var citizen_id:String? = null){

    private var url = "http://10.0.2.2:8093/api/signup"

    fun getUrl():String{
        return url
    }

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

    fun setReg(name:String, pwd:String, citizen_id: String){
        setUserEmail(name)
        setUserPwd(pwd)
        setUserCitizenId(citizen_id)
    }

    fun register(context:AppCompatActivity,volley:IVolley){

        val regJson = JSONObject()
        regJson.put("email",getUserEmail())
        regJson.put("password",getUserPwd())
        regJson.put("citizenID", getUserCitizenId())

        //println(regJson.keys())
        println("Register regJSON POST  ${getUserEmail()} ${getUserPwd()} ${getUserCitizenId()}")
        MyVolleyRequest.getInstance(context,volley)
            .postRequestWithBody(getUrl(),regJson)
        println("Register Done")
    }
}