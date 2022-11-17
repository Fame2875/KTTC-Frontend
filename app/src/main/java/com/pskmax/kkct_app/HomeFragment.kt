package com.pskmax.kkct_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

class HomeFragment : Fragment() {

    private var profileButton : ImageView? = null
    private var transactionButton : Button? = null
    private var unpaidButton : Button? = null

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
}