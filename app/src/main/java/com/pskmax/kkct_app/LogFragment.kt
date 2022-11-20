package com.pskmax.kkct_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pskmax.kkct_app.myVolley.IVolley
import com.pskmax.kkct_app.myVolley.MyVolleyRequest

class LogFragment : Fragment(), IVolley {

    var logRecyclerView: RecyclerView? = null
    var tranEmail: String? = null
    var tranToken: String? = null

    // Log Data from DB
    private var log = arrayOf(
        "Log 1 : ไม้โมก\n12345",
        "Log 2 : เป็น",
        "Log 3 : ประธาน",
        "Log 4 : ชมรม",
        "Log 5 : คน",
        "Log 6 : ชอบ",
        "Log 7 : หี",
        "Log 8 : ที่",
        "Log 9 : มี",
        "Log 10 : สมาชิก",
        "Log 11 : ใน",
        "Log 12 : ชมรม",
        "Log 13 : คือ",
        "Log 14 : พวก",
        "Log 15 : มึง",
        "Log 16 : ที่",
        "Log 17 : อ่าน",
        "Log 18 : นี่",
        "Log 19 : แหละ",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        // recieve data //
        val data = arguments
        if (data != null) {
            tranEmail = data.getString("usEmail")
            tranToken = data.getString("usToken")
            println("{$tranEmail} Fragment")
            println("Done Loading")
        }
        else {
            println("log load failed")
        }
        logData(tranEmail.toString(),tranToken.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logRecyclerView = view.findViewById<RecyclerView>(R.id.logRecyclerView)
        logRecyclerView!!.layoutManager = LinearLayoutManager(requireContext())
        val logAdapter = LogRecycleView(log,requireContext())
        logRecyclerView!!.adapter = logAdapter

    }

    companion object {

        @JvmStatic
        fun newInstance(isMyBoolean: Boolean) = LogFragment().apply {
            arguments = Bundle().apply {
                putBoolean("REPLACE WITH A STRING CONSTANT", isMyBoolean)
            }
        }
    }

    private fun logData(email: String, token: String){
        val url = "http://10.0.2.2:8093/api/getTransaction?email=${email}"
        //test url
        //val url = "https://jsonplaceholder.typicode.com/todos/1"
        println("url: " + url)
        println("token: " + token)
        MyVolleyRequest.getInstance(requireContext(),this@LogFragment)
            .getRequestWithHeader(url,token)
    }

    override fun onResponse(response: String) {
        TODO("Not yet implemented")
        println(response)
    }
}