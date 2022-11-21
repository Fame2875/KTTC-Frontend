package com.pskmax.kkct_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.pskmax.kkct_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var tranInfoTemp :String? = null
    private var tranDateTemp :String? = null

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
                tranInfoTemp = it.getString("usEmail")
                tranDateTemp = it.getString("usPwd")
                println("{$tranInfoTemp} Fragment")
                println("{$tranDateTemp} Fragment")
                println("Done Loading")
            }
            else {
                println("load failed")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.credit.text = tranInfoTemp
        binding.recommend.text = "Recommend : $tranDateTemp"
        binding.profileUser.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString("usEmail",tranInfoTemp)
            bundle.putString("usCID",tranDateTemp)
            fragment.arguments = bundle
            fragmentTransaction?.replace(R.id.frame_layout,fragment)
            fragmentTransaction?.commit()
        }

        binding.seeUnpaid.setOnClickListener {
            changeFragments(UnpaidFragment())
        }
        return binding.root
    }

}