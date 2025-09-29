// app/src/main/java/com/example/t2_aventurainteractiva/MotherboardFragment.kt
package com.example.t2_aventurainteractiva

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment

class MotherboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el mismo layout de activity_main.xml (contiene los hotspots)
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a hotspots (asegúrate que los ids coinciden con tu XML)
        val hotspotCpu: View = view.findViewById(R.id.hotspot_cpu)
        val hotspotRam: View = view.findViewById(R.id.hotspot_ram)
        val hotspotPci: View = view.findViewById(R.id.hotspot_pci)

        // 1) Asignar un transitionName único a cada hotspot.
        // Esto es necesario para que el sistema empareje la vista de origen con la vista destino.
        ViewCompat.setTransitionName(hotspotCpu, "transition_cpu")
        ViewCompat.setTransitionName(hotspotRam, "transition_ram")
        ViewCompat.setTransitionName(hotspotPci, "transition_pci")

        // 2) Click listeners: abre DetailActivity indicando el id del componente y el transitionName.
        hotspotCpu.setOnClickListener {
            openDetailWithSharedElement("cpu_socket", hotspotCpu)
        }

        hotspotRam.setOnClickListener {
            openDetailWithSharedElement("ram_slots", hotspotRam)
        }

        hotspotPci.setOnClickListener {
            openDetailWithSharedElement("pcie_slots", hotspotPci)
        }
    }

    /**
     * Crea el Intent y lanza DetailActivity usando una transición shared-element
     * que parte desde sharedView (el hotspot) y termina en el ImageView de DetailActivity.
     */
    private fun openDetailWithSharedElement(componentId: String, sharedView: View) {
        val transitionName = ViewCompat.getTransitionName(sharedView) ?: "transition_$componentId"

        // Preparamos el intent y pasamos los extras necesarios
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_COMPONENT_ID, componentId)
            putExtra(DetailActivity.EXTRA_TRANSITION_NAME, transitionName)
        }

        // ActivityOptionsCompat crea la animación de elemento compartido.
        // El sistema animará la vista `sharedView` en esta activity a la vista del mismo transitionName en la Activity destino.
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), sharedView, transitionName)
        startActivity(intent, options.toBundle())
    }
}
