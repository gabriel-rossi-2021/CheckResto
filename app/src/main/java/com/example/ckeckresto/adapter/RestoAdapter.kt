package com.example.ckeckresto.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ckeckresto.*
import kotlin.coroutines.coroutineContext

class RestoAdapter(
    val context: MainActivity,
    private val restoList: List<RestoModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<RestoAdapter.ViewHolder>() {
    // Boîte pour ranger tout les composant à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // image resto
        val restoImage = view.findViewById<ImageView>(R.id.image_item)
        val restoName:TextView? = view.findViewById(R.id.name_item)
        val restoDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Mettre à jour chaque modèle avec chaque categorie resto
        val currentResto = restoList[position]

        // Recup le repository
        val repo = RestoRepository()

        // récuperer image avec son lien grâce à Glide
        Glide.with(context).load(Uri.parse(currentResto.imageUrl)).into(holder.restoImage)

        // MEttre à jour le nom de la cat resto
        holder.restoName?.text = currentResto.name_cat

        // Mettre à jour la description de la cat
        holder.restoDescription?.text = currentResto.description

        // Vérifier si cat resto a été liked
        if(currentResto.liked){
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        // interaction sur l'étoile
        holder.starIcon.setOnClickListener {
            // inverser si le bouton est like ou non
            currentResto.liked = !currentResto.liked
            // Mettre à jour l'objet plant
            repo.updateResto(currentResto)
        }

        // interaction lors du click sur un resto
        holder.itemView.setOnClickListener {
            // Afficher la pop-up
            restoPopup(this, currentResto).show()
        }
    }

    // CMb d'item on va renvoyer dynamiquement
    override fun getItemCount(): Int = restoList.size

}