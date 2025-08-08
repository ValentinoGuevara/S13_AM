
# 🗺️ **TechPoint - Mapa de Establecimientos de Equipos de Cómputo en Chimbote**

**Desarrollado por:** Valentino Guevara  
**Correo:** [202114033@uns.edu.pe](mailto:202114033@uns.edu.pe)  
**GitHub:** [ValentinoGuevara](https://github.com/ValentinoGuevara)  
**Curso:** Aplicaciones Móviles - Semana 13  

<div align="center">
  <img src="screenshots5/imagen1.jpg" width="200" alt="Vista Principal de TechPoint">
</div>

## 🛠️ **Tecnologías Utilizadas**

<div align="center">
  <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">
    <img src="https://img.shields.io/badge/ViewModel-3DDC84?style=for-the-badge&logo=android" alt="ViewModel">
  </a>
  <a href="https://developer.android.com/topic/libraries/architecture/livedata">
    <img src="https://img.shields.io/badge/LiveData-00B0FF?style=for-the-badge&logo=android" alt="LiveData">
  </a>
  <a href="https://kotlinlang.org/">
    <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin" alt="Kotlin">
  </a>
  <a href="https://material.io/components">
    <img src="https://img.shields.io/badge/Material_Design-2196F3?style=for-the-badge&logo=material-design" alt="Material Design">
  </a>
  <a href="https://developers.google.com/maps/documentation/android-sdk">
    <img src="https://img.shields.io/badge/Google_Maps_API-FF5722?style=for-the-badge&logo=googlemaps" alt="Google Maps API">
  </a>
</div>

## 🔍 **¿Cómo funciona la aplicación?**

**TechPoint** es una aplicación que permite a los usuarios localizar establecimientos en **Chimbote** que venden equipos de cómputo. Utiliza el **Google Maps API** para mostrar el mapa, detectar la ubicación del usuario y recomendar los establecimientos más cercanos donde se pueden comprar productos como PC, teclados, monitores, entre otros.

---

### 🏗️ **Arquitectura y Flujo**

1. **Detección de Ubicación del Usuario**:
   - La aplicación usa la ubicación actual del usuario para recomendar los establecimientos más cercanos.
   - Implementa **Google Maps API** para mostrar los puntos de interés.

2. **Recomendación de Establecimientos**:
   - Los usuarios pueden ver los establecimientos cercanos a ellos en el mapa, marcados con un ícono.
   - La aplicación muestra el tiempo estimado para llegar a cada establecimiento.

3. **Interfaz Moderna**:
   - Diseño visual inspirado en **Material Design**.
   - Botones grandes, claros y responsivos.
   - Compatible con modo oscuro.

---

### 🌟 **Características Destacadas**

| Característica | Descripción |
|----------------|-------------|
| 🗺️ **Mapa interactivo** | Visualización de establecimientos de cómputo en el mapa de Chimbote |
| 📍 **Detección de ubicación** | Ubicación del usuario detectada automáticamente |
| ⏱ **Tiempo de llegada** | Recomendación del tiempo estimado para llegar a cada establecimiento |
| 🖥️ **Equipos de cómputo disponibles** | Muestra información de los productos disponibles en cada tienda |
| 🌙 **Modo oscuro** | Compatible con el modo oscuro para una visualización cómoda |

---

### 📱 **Capturas de Pantalla**

<div align="center">
  <table>
    <tr>
      <td><img src="screenshots5/imagen1.jpg" width="200" alt="Pantalla de inicio"></td>
      <td><img src="screenshots5/imagen2.jpg" width="200" alt="Mapa de establecimientos"></td>
      <td><img src="screenshots5/imagen3.jpg" width="200" alt="Mapa interactivo"></td>
    </tr>
    <tr>
      <td><img src="screenshots5/imagen4.jpg" width="200" alt="Seleccionando producto"></td>
      <td><img src="screenshots5/imagen5.jpg" width="200" alt="Recomendaciones de tiendas"></td>
      <td><img src="screenshots5/imagen6.jpg" width="200" alt="Detalles de establecimiento"></td>
    </tr>
    <tr>
      <td><img src="screenshots5/imagen7.jpg" width="200" alt="Vista de ubicación"></td>
      <td><img src="screenshots5/imagen8.jpg" width="200" alt="Vista de distancia"></td>
      <td><img src="screenshots5/imagen9.jpg" width="200" alt="Navegación de mapa"></td>
    </tr>
    <tr>
      <td><img src="screenshots5/imagen10.jpg" width="200" alt="Interfaz de usuario moderna"></td>
    </tr>
  </table>
  <p>Explora el mapa, selecciona productos y encuentra los mejores establecimientos cercanos.</p>
</div>

---

## 🛠️ **Implementación Técnica**

### 🧩 **Uso de Google Maps API**

```kotlin
val gmap: GoogleMap = googleMap
val userLocation = LatLng(currentLocation.latitude, currentLocation.longitude)
gmap.addMarker(MarkerOptions().position(userLocation).title("Tu ubicación"))
```

### 🧱 **Dependencias y Herramientas**

| Herramienta | Uso |
|-------------|-----|
| **Google Maps API** | Muestra el mapa interactivo y los puntos de interés |
| **Kotlin** | Lógica de la aplicación |
| **Material Design Components** | UI moderna |
| **ViewModel** | Persistencia de datos |
| **LiveData** | Observación reactiva |

---

## 🚀 **¿Cómo Ejecutarla?**

1. Clona el repositorio:  
   ```bash
   git clone https://github.com/ValentinoGuevara/TechPoint.git
   ```

2. Abre en **Android Studio** y espera que sincronice Gradle.

3. Ejecuta la aplicación en tu emulador o dispositivo físico.

---

## 🧩 **Posibles Mejoras Futuras**

- 🗺 **Ampliar el área de cobertura** para otras ciudades.
- 🖥️ **Integrar catálogo de productos** por cada tienda.
- 🔄 **Actualizar ubicación en tiempo real** al moverse el usuario.
- 🎯 **Filtros de búsqueda** (por tipo de producto o tienda).

---

## 📚 **Contexto Académico**

Este proyecto fue desarrollado como parte del curso de **Aplicaciones Móviles**, con el objetivo de aplicar el uso de **Google Maps API** y arquitectura **MVVM** en una aplicación Android.

---

## 📜 **Licencia**

**© 2025 - Valentino Guevara**  
Prohibido el uso comercial sin autorización expresa.

<div align="center">
  <img src="screenshots5/app_demo.gif" width="300" alt="Demo TechPoint">
  <p>¡Explora el código y contribuye!</p>
  <a href="[https://github.com/ValentinoGuevara/TechPoint](https://github.com/ValentinoGuevara/S13_AM.git)">
    <img src="https://img.shields.io/badge/VER_EN_GITHUB-181717?style=for-the-badge&logo=github">
  </a>
</div>

---
