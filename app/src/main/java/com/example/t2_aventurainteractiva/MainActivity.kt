package com.example.t2_aventurainteractiva

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // usa el layout definido

        // Cargar el fragmento solo si no está cargado ya
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.motherboard_container, MotherboardFragment())
                .commit()
        }
    }
}
