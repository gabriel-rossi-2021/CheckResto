package com.example.ckeckresto

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.ckeckresto.adapter.RestoAdapter

class restoPopup(
    private val adapter: RestoAdapter,
    private val currentResto: RestoModel
    ): Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_resto_details)
        setupComponents()
        setupClosButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView){
        if(currentResto.liked){
            button.setImageResource(R.drawable.ic_star)
        }else{
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setupStarButton() {
        // recuperer
        val starButton = findViewById<ImageView>(R.id.star_button)

        updateStar(starButton)

        // Interaction
        starButton.setOnClickListener {
            currentResto.liked = !currentResto.liked
            val repo = RestoRepository()
            repo.updateResto(currentResto)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            // Supprimer le resto de la db
            val repo = RestoRepository()
            repo.deleteResto(currentResto)
            dismiss()
        }
    }

    private fun setupClosButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            // Fermer la fenetre popup
            dismiss()
        }
    }

    private fun setupComponents() {
        // actualiser l'image du resto
        val restoImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentResto.imageUrl)).into(restoImage)

        // Actualiser le name resto
        findViewById<TextView>(R.id.popup_resto_name).text = currentResto.name_cat

        // Actualiser la description
        findViewById<TextView>(R.id.popup_resto_description_subtitle).text = currentResto.description

        // ACtualiser la fourchette
        findViewById<TextView>(R.id.popup_resto_grow_subtitle).text = currentResto.grow
    }

}