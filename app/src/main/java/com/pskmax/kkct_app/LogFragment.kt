package com.pskmax.kkct_app


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.pskmax.kkct_app.myVolley.IVolley
import com.android.volley.Request
import com.pskmax.kkct_app.databinding.FragmentHomeBinding
import com.pskmax.kkct_app.databinding.FragmentLogBinding


class LogFragment : Fragment(), IVolley {

    private var _binding : FragmentLogBinding? = null
    private val binding get() = _binding!!

    var logRecyclerView: RecyclerView? = null
    var tranEmail: String? = null
    var tranToken: String? = null
    var getRes : String = ""

    // Log Data from DB
    private var log = arrayOf(
        getRes
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it != null) {
                tranEmail = it.getString("usEmail")
                tranToken = it.getString("usToken")
                println("{$tranEmail} Fragment")
                println("Token = {$tranToken}")
                println("Done Loading")
            }
            else {
                println("log load failed")
            }
            logData(tranEmail.toString(),tranToken.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogBinding.inflate(inflater,container,false)
        logRecyclerView = binding.logRecyclerView
        logRecyclerView!!.layoutManager = LinearLayoutManager(requireContext())

        Handler().postDelayed({
            // Log Data from DB
            var log = arrayOf(
                getRes
            )
            val logAdapter = LogRecycleView(log,requireContext())
            logRecyclerView!!.adapter = logAdapter
        }, 1000)

        return binding.root
    }

    private fun logData(email: String, token: String){
//        val url = "http://10.0.2.2:8093/api/getTransaction?email=${email}"
        val url = "http://10.0.2.2:8093/getTransaction?email=${email}"
//        val url = "http://10.0.2.2:8093/api/login?email=poohkung@gmail.com&password=Pooh12345678"
        //test url
        //val url = "https://jsonplaceholder.typicode.com/todos/1"
        println("url: " + url)
        println("token: " + token)

        val jsonRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener{
                response ->
//                Log.d("Respond",response.toString())
                getRes = response
                println("getRes = $getRes")
            },
            Response.ErrorListener{ error ->
                Log.d("Response",error.toString())
                return@ErrorListener
            }
        )

        val queue = Volley.newRequestQueue(requireContext())
        queue.add(jsonRequest)
    }

    override fun onResponse(response: String) {
        TODO("Not yet implemented")
        println(response)
    }

}