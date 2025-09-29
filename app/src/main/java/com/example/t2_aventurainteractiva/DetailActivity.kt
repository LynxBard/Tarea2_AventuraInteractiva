// app/src/main/java/com/example/t2_aventurainteractiva/DetailActivity.kt
package com.example.t2_aventurainteractiva

import android.content.Intent
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COMPONENT_ID = "extra_component_id" // Asegúrate que esta constante sea la misma que usa ExplanationActivity o define una nueva.
        const val EXTRA_TRANSITION_NAME = "extra_transition_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Postpone the enter transition until we have the image ready
        // para evitar un "flash" o que la animación ocurra antes de mostrar la imagen.
        supportPostponeEnterTransition()

        val imageView = findViewById<ImageView>(R.id.component_image_detail)
        val fab = findViewById<FloatingActionButton>(R.id.info_fab)

        // 1) Recuperar extras
        val componentId = intent.getStringExtra(EXTRA_COMPONENT_ID)
        val transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)

        // 2) Asignar el transitionName al ImageView destino. Debe coincidir con el transitionName del hotspot.
        //    Si no coincide, la animación shared element no ocurrirá.
        if (transitionName != null) {
            ViewCompat.setTransitionName(imageView, transitionName)
        }

        // 3) Buscar los datos del componente usando el id
        val component = ComponentRepository.findById(componentId)
        if (component == null) {
            Toast.makeText(this, "Componente no encontrado", Toast.LENGTH_SHORT).show()
            // Empezamos la transición y cerramos
            supportStartPostponedEnterTransition()
            finish()
            return
        }

        // 4) Cargar la imagen del componente en el ImageView.
        //    Si tu imagen se carga de forma asíncrona (Glide/Picasso), debes llamar a supportStartPostponedEnterTransition()
        //    cuando la imagen termine de cargarse. Aquí usamos un recurso local, así que arrancamos la transición
        //    cuando el sistema haya medido/dibujado la vista (onPreDraw).
        imageView.setImageResource(component.imageRes)

        // Asegurarse de iniciar la transición después de que la vista esté lista para dibujarse
        val preDrawListener = object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                imageView.viewTreeObserver.removeOnPreDrawListener(this)
                supportStartPostponedEnterTransition()
                return true
            }
        }
        imageView.viewTreeObserver.addOnPreDrawListener(preDrawListener)

        // 5) Configurar botón de información para abrir ExplanationActivity (pasando el mismo id)
        fab.setOnClickListener {
            val intent = Intent(this, ExplanationActivity::class.java).apply {
                putExtra("COMPONENT_ID", componentId) // Usar la misma clave que espera ExplanationActivity
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out) // Animación para entrar a ExplanationActivity
        }
    }

    // Si quieres una animación al volver de ExplanationActivity a DetailActivity con el botón de atrás del sistema
    // override fun onActivityReenter(resultCode: Int, data: Intent?) {
    //     super.onActivityReenter(resultCode, data)
    //     overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down)
    // }

    override fun finish() {
        super.finish()
        // Esto aplicará la animación de salida cuando DetailActivity se cierre (ej. por el botón atrás del sistema)
        // Si vienes de MotherboardFragment con una shared element transition, esta podría interferir.
        // Considera si esta es la animación deseada en todos los casos de cierre de DetailActivity.
        // overridePendingTransition(R.anim.fade_in, R.anim.zoom_out) // Ejemplo, ajusta según necesidad
    }
}
