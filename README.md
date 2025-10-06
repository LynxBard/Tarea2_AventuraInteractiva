# Tarea 2 y Practica 2 Diseño de una Aventura Interactiva

## Descripción de la aplicación

**T2_AventuraInteractiva** es una aplicación Android desarrollada en Kotlin para explicar, de forma interactiva, los diferentes componentes internos de una computadora.
La aplicación está organizada en una **jerarquía de pantallas** que permiten al usuario explorar cada parte del sistema:

1. **Pantalla Principal (MainActivity):** muestra la lista de componentes principales.
2. **Detalle del Componente (DetailActivity):** ofrece información breve del componente seleccionado y acceso a explicaciones más profundas.
3. **Explicación Detallada (ExplanationActivity):** presenta una descripción completa del componente, acompañada de animaciones de transición para una experiencia inmersiva.

El objetivo es facilitar el aprendizaje de la arquitectura de hardware mediante una navegación fluida y amigable.

---

## Instrucciones para ejecutar el proyecto

1. **Clonar o descargar el repositorio** en tu computadora.

   ```bash
   git clone https://github.com/LynxBard/Tarea2_AventuraInteractiva.git
   ```

2. **Abrir en Android Studio** (versión recomendada: **Android Studio Ladybug+ o superior**).

3. **Configurar el SDK**:

   * Ir a *File > Project Structure > SDK Location*.
   * Asegurarse de tener instalado **Android SDK 34 o superior**.

4. **Ejecutar la aplicación**:

   * Conectar un dispositivo Android físico o usar un emulador.
   * Presionar (*Run*) en Android Studio.

La aplicación debería iniciar mostrando la pantalla principal con la lista de componentes.

---

## Decisiones de diseño y transiciones

* **Arquitectura simple:** se utilizó una jerarquía clara de actividades (`MainActivity → DetailActivity → ExplanationActivity`) para mantener la navegación comprensible.
* **Uso de `Intent` con extras:** la comunicación entre pantallas se hace mediante el paso de un `component_id` que permite cargar dinámicamente la información de cada componente desde `ComponentRepository`.
* **Transiciones animadas:**

  * Entrada y salida con `overridePendingTransition` para simular un efecto de deslizamiento (*slide in/out*).
  * Esto crea una sensación de flujo narrativo al pasar de un nivel de detalle a otro.

---

## Retos y Soluciones

### 1. **Error: `Unresolved reference 'findById'`**

* **Problema:** se intentó acceder a `findById` desde `ComponentData` en lugar de `ComponentRepository`.
* **Solución:** corregir la llamada y cambiar `componentId` a `String` en lugar de `Int`.

### 2. **Manejo de recursos de texto**

* **Problema:** algunos campos almacenaban IDs de recursos (`R.string...`) en lugar de texto plano.
* **Solución:** usar `TextView.setText(resourceId)` para que Android cargue el recurso automáticamente.

### 3. **Consistencia en el paso de datos entre Activities**

* **Problema:** en un inicio se intentaba pasar un `Int` como identificador, pero los componentes usaban `String` (`"cpu_socket"`, `"ram_slots"`).
* **Solución:** unificar todo el sistema para trabajar con IDs tipo `String`.

---

## Video: Uso de SharedPreferences 

https://drive.google.com/file/d/104y0wEj2nMBmnC-Awuwjkto7aFegyNpS/view?usp=sharing

## Capturas de pantalla

1. **Pantalla Principal – Lista de componentes**

![](https://github.com/user-attachments/assets/2f19e9e9-f64a-435a-92a5-52a7c3a1b458)

3. **Detalle del Componente – Vista resumida**

| ![](https://github.com/user-attachments/assets/c29afc44-4b26-414b-ba98-9ec752631e21) | ![](https://github.com/user-attachments/assets/f1206a8d-fdd6-47bc-8364-a4ce93881255) |

| ![](https://github.com/user-attachments/assets/610774f4-24a2-4f34-8094-5ade3dced244) |

5. **Explicación Detallada – Información completa**

| ![](https://github.com/user-attachments/assets/6bbd465d-8a79-4ea6-bf63-b5ba71acb6fb) | ![](https://github.com/user-attachments/assets/9b2ca8ce-29d6-47f2-ab89-c8a1286c84bd) |

| ![](https://github.com/user-attachments/assets/f7cd5cf3-56fe-450e-80ea-e5cc011e3a5a) |

---

## Créditos

Desarrollado por: **Carlos David González Sánchez**
Materia: **Desarrollo de Aplicaciones Moviles Nativas**
Lenguaje: **Kotlin**
Entorno: **Android Studio**
