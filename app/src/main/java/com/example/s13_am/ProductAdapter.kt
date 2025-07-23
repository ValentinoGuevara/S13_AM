package com.example.s13_am

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val products: List<String>,
    private val onSelectionChanged: (Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val selectedProducts = mutableSetOf<String>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkboxProduct)
        val textView: TextView = itemView.findViewById(R.id.textProductName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.textView.text = product.capitalize()
        holder.checkBox.isChecked = selectedProducts.contains(product)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (selectedProducts.size < 4) {
                    selectedProducts.add(product)
                } else {
                    holder.checkBox.isChecked = false
                    return@setOnCheckedChangeListener
                }
            } else {
                selectedProducts.remove(product)
            }
            onSelectionChanged(selectedProducts.size)
        }

        holder.itemView.setOnClickListener {
            holder.checkBox.isChecked = !holder.checkBox.isChecked
        }
    }

    override fun getItemCount() = products.size

    fun getSelectedProducts(): List<String> = selectedProducts.toList()
}