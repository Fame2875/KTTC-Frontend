package com.pskmax.kkct_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var logRecyclerView: RecyclerView? = null

    // Log Data from DB
    private var log = arrayOf(
        "Log 1 : ไม้โมก",
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
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        logRecyclerView = findViewById<RecyclerView>(R.id.logRecyclerView)
        logRecyclerView!!.layoutManager = LinearLayoutManager(this)
        val logAdapter = LogRecycleView(log,this)
        logRecyclerView!!.adapter = logAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}