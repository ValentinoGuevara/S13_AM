package com.example.s13_am
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlin.math.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var selectedProducts: List<String> = emptyList()
    private var userLocation: LatLng? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val MAX_DISTANCE_KM = 5.0 // Radio máximo de búsqueda
    }

    data class Tienda(
        val nombre: String,
        val direccion: String,
        val latitud: Double,
        val longitud: Double,
        val productos: List<String>
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        selectedProducts = intent.getStringArrayListExtra("selected_products") ?: emptyList()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        supportActionBar?.title = "Tiendas cercanas"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true

        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableUserLocation()
                } else {
                    Toast.makeText(this, "Permiso de ubicación necesario", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    private fun enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                userLocation = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation!!, 14f))
                loadNearbyStores()
            } else {
                Toast.makeText(this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadNearbyStores() {
        try {
            val jsonString = assets.open("locales_computo.json").bufferedReader().use { it.readText() }
            val gson = Gson()
            val listType = object : TypeToken<List<Tienda>>() {}.type
            val tiendas: List<Tienda> = gson.fromJson(jsonString, listType)

            val nearbyStores = tiendas.filter { tienda ->
                val hasSelectedProducts = tienda.productos.any { producto ->
                    selectedProducts.contains(producto)
                }

                val distance = userLocation?.let {
                    calculateDistance(it, LatLng(tienda.latitud, tienda.longitud))
                } ?: Double.MAX_VALUE

                hasSelectedProducts && distance <= MAX_DISTANCE_KM
            }

            displayStores(nearbyStores)

        } catch (e: IOException) {
            Toast.makeText(this, "Error al cargar las tiendas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayStores(stores: List<Tienda>) {
        if (stores.isEmpty()) {
            Toast.makeText(this, "No hay tiendas cercanas con los productos seleccionados", Toast.LENGTH_LONG).show()
            return
        }

        stores.forEach { tienda ->
            val position = LatLng(tienda.latitud, tienda.longitud)
            val productsMatch = tienda.productos.filter { selectedProducts.contains(it) }

            val marker = map.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(tienda.nombre)
                    .snippet("${tienda.direccion}\nProductos: ${productsMatch.joinToString(", ")}")
            )

            marker?.tag = tienda
        }

        Toast.makeText(this, "Encontradas ${stores.size} tienda(s) cercana(s)", Toast.LENGTH_SHORT).show()
    }

    private fun calculateDistance(point1: LatLng, point2: LatLng): Double {
        val R = 6371 // Radio de la Tierra en km
        val dLat = Math.toRadians(point2.latitude - point1.latitude)
        val dLon = Math.toRadians(point2.longitude - point1.longitude)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(point1.latitude)) * cos(Math.toRadians(point2.latitude)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}