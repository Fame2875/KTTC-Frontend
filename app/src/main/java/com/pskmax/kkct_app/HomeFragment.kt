package com.pskmax.kkct_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HomeFragment : Fragment() {

    private var profileButton : ImageView? = null
    private var transactionButton : Button? = null
    private var unpaidButton : Button? = null
    private lateinit var tranInfoText : TextView
    private lateinit var tranDateInfoText : TextView
    var tranInfoTemp :String? = null
    var tranDateTemp :String? = null

    private fun changeFragments(fragment : Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout,fragment)
        fragmentTransaction?.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileButton = view.findViewById(R.id.profileUser)
        transactionButton = view.findViewById(R.id.seeTrans)
        unpaidButton = view.findViewById(R.id.seeUnpaid)
        tranInfoText = view.findViewById(R.id.tranInfo)
        tranDateInfoText = view.findViewById(R.id.tranDateInfo)

        // recieve data //
        val data = arguments
        if (data != null) {
            tranInfoTemp = data.get("usEmail").toString()
            tranDateTemp = data.get("usToken").toString()
            println("{$tranInfoTemp} Fragment")
            println("{$tranDateTemp} Fragment")
            println("Done Loading")
        }
        else {
            println("load failed")
        }

        tranInfoText.text = tranInfoTemp
        tranDateInfoText.text = tranDateTemp
        ////////////////////////
        profileButton!!.setOnClickListener {
            changeFragments(ProfileFragment())
        }

        transactionButton!!.setOnClickListener {
            changeFragments(TransactionsFragment())
        }

        unpaidButton!!.setOnClickListener {
            changeFragments(UnpaidFragment())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(isMyBoolean: Boolean) = HomeFragment().apply {
            arguments = Bundle().apply {
                putBoolean("REPLACE WITH A STRING CONSTANT", isMyBoolean)
            }
        }
    }

}