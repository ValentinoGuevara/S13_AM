package com.example.s13_am

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnBuscar: Button
    private lateinit var productAdapter: ProductAdapter

    private val productos = listOf(
        "auriculares", "pc", "mouse", "monitor", "teclado",
        "webcam", "altavoces", "tarjeta gráfica", "cpu", "silla gamer"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewProducts)
        btnBuscar = findViewById(R.id.btnBuscar)
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(productos) { selectedCount ->
            btnBuscar.isEnabled = selectedCount > 0
            btnBuscar.text = if (selectedCount > 0) {
                "Buscar en el mapa ($selectedCount/4)"
            } else {
                "Selecciona productos"
            }
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = productAdapter
        }
    }

    private fun setupClickListeners() {
        btnBuscar.setOnClickListener {
            val selectedProducts = productAdapter.getSelectedProducts()

            if (selectedProducts.isEmpty()) {
                Toast.makeText(this, "Selecciona al menos un producto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedProducts.size > 4) {
                Toast.makeText(this, "Máximo 4 productos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MapActivity::class.java)
            intent.putStringArrayListExtra("selected_products", ArrayList(selectedProducts))
            startActivity(intent)
        }
    }
}