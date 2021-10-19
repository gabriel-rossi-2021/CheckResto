package com.example.ckeckresto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ckeckresto.fragments.CollectionFragment
import com.example.ckeckresto.fragments.homeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charger restoRepository
        val repo = RestoRepository()

        // mettre à jour liste resto
            repo.updateData{
                // Injecter le fragent dans notre boite (fragment container)
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, homeFragment(this))
                transaction.addToBackStack(null)
                transaction.commit()
            }
    }
}