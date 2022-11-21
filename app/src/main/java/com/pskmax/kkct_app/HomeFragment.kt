package com.pskmax.kkct_app

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.pskmax.kkct_app.databinding.FragmentHomeBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.Objects

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var tranEmail :String? = null
    var tranCID :String? = null
    var tranToken: String? = null

//    var historyTransaction : JSONArray? = null
    var creditScore : String? = null
    var recommend : String? = null
    var unpaid : String? = null
    var dueDate : String? = null
//    var creditCalculation : JSONObject? = null

    private fun changeFragments(fragment : Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout,fragment)
        fragmentTransaction?.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it != null) {
                tranEmail = it.getString("usEmail")
                tranCID = it.getString("usPwd")
                tranToken = it.getString("usToken")
                println("{$tranEmail} Fragment")
                println("{$tranCID} Fragment")
                println("Done Loading")
            }
            else {
                println("load failed")
            }
        }
        loadCredit(tranEmail.toString(),tranToken.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        Handler().postDelayed({
            binding.credit.text = creditScore
            binding.recommend.text = "Recommend : $recommend"
            binding.latestUnpaid.text = unpaid
            binding.dueDate.text = dueDate
        }, 500)

        binding.profileUser.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString("usEmail",tranEmail)
            fragment.arguments = bundle
            fragmentTransaction?.replace(R.id.frame_layout,fragment)
            fragmentTransaction?.commit()
        }

        binding.seeUnpaid.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = LogFragment()
            val bundle = Bundle()
            bundle.putString("usEmail",tranEmail)
            bundle.putString("usToken",tranToken)
            fragment.arguments = bundle
            fragmentTransaction?.replace(R.id.frame_layout,fragment)
            fragmentTransaction?.commit()
        }
        return binding.root
    }
    private fun loadCredit(email: String, token: String){
        val url = "http://10.0.2.2:8093/RequestCredit_fromCustomer?email=${email}"
        println("url: " + url)
        println("email: " + email)
        println("token: " + token)

        val jsonRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener{
                    response ->
                Log.d("Respond",response.toString())
                val historyTransaction : JSONArray = JSONObject(response).getJSONArray("historyTransaction")
                val creditCalculation : JSONObject = JSONObject(response).getJSONObject("creditCalculation")
                val length : Int = historyTransaction.length()
                this.creditScore = creditCalculation.getString("creditScore")
                this.recommend = creditCalculation.getString("recommend")
                var test = getUnpaid(historyTransaction[length-1].toString())
                this.unpaid = test[5]
                this.dueDate = test[3]
                println("credit score = $creditScore")
                println("recommend = $recommend")
                println("unpaid = $unpaid")
                println("recommend = $dueDate")
            },
            Response.ErrorListener{ error ->
                Log.d("Response",error.toString())
                return@ErrorListener
            }
        )
        val queue = Volley.newRequestQueue(requireContext())
        queue.add(jsonRequest)
    }

    private fun getUnpaid(string : String) : ArrayList<String>{
        var temp : String = ""
        var list = arrayListOf<String>()
//        var data = string.replace("{","").replace("}","").replace("\""," ").replace(",","\n")
        for(item in string){
            if (item.toString() == "{"){
                temp = ""
            }
            else if (item.toString() != ","){
                temp += item
            }
            else if (item.toString() == "}"){
                list += temp
            }
            else if (item.toString() == ","){
                list += temp
                temp = ""
            }
        }
        return list
    }

}