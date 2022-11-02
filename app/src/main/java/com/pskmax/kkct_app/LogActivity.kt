package com.pskmax.kkct_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LogActivity : AppCompatActivity() {

    var log = arrayOf(
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

    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        supportActionBar?.hide()

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val logAdapter = LogRecycleView(log,this)
        recyclerView!!.adapter = logAdapter
    }
}