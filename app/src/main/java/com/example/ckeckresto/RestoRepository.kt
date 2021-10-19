package com.example.ckeckresto

import android.app.appsearch.BatchResultCallback
import com.example.ckeckresto.RestoRepository.Singleton.databaseRef
import com.example.ckeckresto.RestoRepository.Singleton.restoList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RestoRepository {

    object Singleton{
        // se connecter à la ref "resto"
        val databaseRef = FirebaseDatabase.getInstance().getReference("Resto")

        // créer une liste contenant nos resto
        val restoList = arrayListOf<RestoModel>()
    }

    fun updateData(callback: () -> Unit){
        // Absorber les donnes depuis la db -> liste de resto
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // Retirer les anciennes
                restoList.clear()
                // récolter la liste
                for (ds in snapshot.children) {
                    // construire objet resto
                    val Resto = ds.getValue(RestoModel::class.java)

                    // vérifier que le resto est pas null
                    if(Resto != null){
                        // Ajouter le resto à notre list
                        restoList.add(Resto)
                    }
                }
                // actionner le callback
                callback()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    // mettre à jour un objet resto en db
    fun updateResto(resto: RestoModel){
        databaseRef.child(resto.id).setValue(resto)
    }

    // Supprimer un resto de la db
    fun deleteResto(resto: RestoModel) = databaseRef.child(resto.id).removeValue()

}