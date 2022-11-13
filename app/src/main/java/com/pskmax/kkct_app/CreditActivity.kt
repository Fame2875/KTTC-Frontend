package com.pskmax.kkct_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class CreditActivity : AppCompatActivity() {

    var transactionView: CardView? = null
    var creditLevelView: CardView? = null
    var creditScoreView: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)
        supportActionBar?.hide()
    }
}