package com.pskmax.kkct_app

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val bindTextValues: TextView? = itemView.findViewById(R.id.txtTitle)
}