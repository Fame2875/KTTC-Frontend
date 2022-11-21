package com.pskmax.kkct_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.pskmax.kkct_app.databinding.FragmentProfileBinding
import org.json.JSONObject

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var email : String? = null
    private var cid : String? = null
    var tranToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString("usEmail")
            tranToken = it.getString("usToken")
        }
        loadCredit(email.toString(),tranToken.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        Handler().postDelayed({
            binding.emailValue.text = email
            binding.citizenIdValue.text = cid
        }, 500)
        println("{$email} Profile")
        println("{$cid} Profile")
        binding.emailValue.text = email
        binding.citizenIdValue.text = cid
        binding.logoutBtn.setOnClickListener {

        }
        return binding.root
    }

    private fun loadCredit(email: String, token: String){
        val url = "http://10.0.2.2:8093/getCustomer?email=${email}"
        println("url: " + url)
        println("email: " + email)
        println("token: " + token)
        val jsonRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener{
                    response ->
                Log.d("Respond",response.toString())
                this.cid = JSONObject(response).getString("citizenID")
//                this.recommend = JSONObject(response).getString("recommend")
                println("data = $cid")
            },
            Response.ErrorListener{ error ->
                Log.d("Response",error.toString())
                return@ErrorListener
            }
        )

        val queue = Volley.newRequestQueue(requireContext())
        queue.add(jsonRequest)
    }

}