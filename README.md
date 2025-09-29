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
   git clone <url-del-repo>
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

## Capturas de pantalla

*(Inserta aquí las imágenes tomadas desde el emulador o dispositivo, recomendadas en formato `.png` o `.jpg`)*

1. **Pantalla Principal – Lista de componentes**
2. **Detalle del Componente – Vista resumida**
3. **Explicación Detallada – Información completa**

---

## Créditos

Desarrollado por: **Carlos David González Sánchez**
Materia: **Desarrollo de Aplicaciones Moviles Nativas**
Lenguaje: **Kotlin**
Entorno: **Android Studio**
