package com.example.ckeckresto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ckeckresto.MainActivity
import com.example.ckeckresto.R
import com.example.ckeckresto.RestoModel
import com.example.ckeckresto.RestoRepository.Singleton.restoList
import com.example.ckeckresto.adapter.RestoAdapter
import com.example.ckeckresto.adapter.RestoItemDecoration

class homeFragment(
    private val context:MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_home, container, false)
        // Récuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = RestoAdapter(context, restoList, R.layout.item_horizontal_resto)
        // Récuperer le second recyclerView
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = RestoAdapter(context, restoList, R.layout.item_vertical_resto)
        verticalRecyclerView.addItemDecoration(RestoItemDecoration())
        return view

    }
}