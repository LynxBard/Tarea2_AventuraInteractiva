package com.example.t2_aventurainteractiva

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExplanationActivity : AppCompatActivity() {

    // Simulación de datos de componentes. En una app real, esto vendría de una base de datos, API, etc.
    private val componentData = mapOf(
        "cpu_socket" to Pair("CPU (Unidad Central de Procesamiento)", "También conocido como ranura de CPU, el zócalo de CPU se utiliza para conectar un microprocesador a una placa de circuito impreso (PCB) sin soldadura, lo que facilita la instalación o sustitución de la CPU en la placa base. Comúnmente presentes en PC y servidores, los zócalos de CPU son la antítesis de las CPU de montaje superficial utilizadas en portátiles, que ahorran espacio a costa de una instalación o intercambio más cómodos.\n" +
                "\n" +
                "Los tipos más comunes de zócalos de CPU incluyen el Pin Grid Array (PGA) y el Land Grid Array (LGA). La diferencia entre ambos radica en que el PGA coloca los pines en el procesador y los orificios en el zócalo, mientras que el LGA tiene un zócalo con pines donde se coloca el procesador. El número y la disposición de los pines varían según el tipo de CPU utilizado, pero, en general, una mayor cantidad y densidad de pines permitirá transferir más señales de datos con mayor rapidez."),
        "ram_slots" to Pair("RAM (Memoria de Acceso Aleatorio)", "Una ranura de memoria es un conector físico en la placa base de una computadora, diseñado para insertar módulos de memoria. Esta ranura permite expandir la memoria de tu sistema, mejorando su capacidad para manejar datos y ejecutar aplicaciones con mayor fluidez. También se le conoce como ranura para memoria RAM o ranura DIMM.\n" +
                "\n" +
                "Cuando insertas un módulo de memoria en una ranura, se establece una conexión entre el módulo y el controlador de memoria de la placa base. Esto permite que el procesador acceda de manera rápida a los datos almacenados en la memoria, proporcionándole un espacio temporal para realizar tareas de manera más eficiente.\n" +
                "\n" +
                "Las ranuras de memoria están adaptadas para tipos específicos de módulos, como DIMM para computadoras de escritorio o SODIMM para laptops. Estos módulos pueden ser DDR3, DDR4 o DDR5, que representan diferentes generaciones de memoria RAM."),
        "pcie_slots" to Pair("PCIe (Interconexión de Componentes Periféricos)", "Las ranuras PCI se utilizan para instalar tarjetas de sonido, Ethernet y tarjetas remotas y, actualmente, unidades de estado sólido que utilizan tecnología NVMe para proporcionar velocidades de unidad SSD que son varias veces más rápidas que las velocidades de SSD SATA. Las ranuras PCI también permiten agregar tarjetas de diseño discreto a una computadora.\n" +
                "\n" +
                "Las ranuras PCI (y sus variantes) permiten agregar tarjetas de expansión a una placa base. Las tarjetas de expansión aumentan las capacidades de la máquina más allá de lo que la placa base puede ofrecer por sí sola, como: gráficos mejorados, sonido extendido, controlador de disco duro y USB expandido y opciones de interfaz de configuración adicionales, por nombrar algunas.\n" +
                "\n" +
                "En el mercado existen 3 ranuras PCI principales (PCI de 64 bits, PCI de 32 bits y PCI-X y PCI Express (PCI-E)). Tienen un aspecto diferente y también aceptan dispositivos diferentes. Poner una tarjeta PCI en la ranura equivocada puede dañar la tarjeta y posiblemente hacer funcionar todo el ordenador."),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation)

        // Aplicar animación de entrada
        overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out)


        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val backButton: Button = findViewById(R.id.backButton)

        // Recuperar el ID del componente del Intent
        val componentId = intent.getStringExtra("COMPONENT_ID")

        if (componentId != null) {
            val componentInfo = componentData[componentId] // Get the Pair?
            if (componentInfo != null) { // Check if it's not null
                val (name, description) = componentInfo // Now it's safe to destructure
                titleTextView.text = name
                descriptionTextView.text = description
            } else {
                titleTextView.text = "Error"
                descriptionTextView.text = "Información del componente no encontrada."
            }
        } else {
            titleTextView.text = "Error"
            descriptionTextView.text = "No se pudo cargar la información del componente."
        }

        backButton.setOnClickListener {
            finish() // Cierra la actividad actual
        }
    }

    override fun finish() {
        super.finish()
        // Aplicar animación de salida
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down)
    }
}
