package com.example.ckeckresto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ckeckresto.MainActivity
import com.example.ckeckresto.R
import com.example.ckeckresto.RestoRepository.Singleton.restoList
import com.example.ckeckresto.adapter.RestoAdapter
import com.example.ckeckresto.adapter.RestoItemDecoration

class CollectionFragment(
    private val context: MainActivity
) :Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        // recuperer ma recycler view
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = RestoAdapter(context, restoList.filter { it.liked }, R.layout.item_vertical_resto)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(RestoItemDecoration())

        return view
    }
}