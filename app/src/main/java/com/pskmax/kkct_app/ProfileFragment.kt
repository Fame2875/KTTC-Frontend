package com.pskmax.kkct_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class ProfileFragment : Fragment() {

    private lateinit var emailValue: TextView
    private lateinit var citizenIdValue: TextView
    private var btnLogout: Button? = null

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailValue = view.findViewById<TextView>(R.id.emailValue)
        citizenIdValue = view.findViewById<TextView>(R.id.citizenIdValue)
        btnLogout = view.findViewById<AppCompatButton>(R.id.logoutBtn)

        btnLogout!!.setOnClickListener{

        }
    }

}