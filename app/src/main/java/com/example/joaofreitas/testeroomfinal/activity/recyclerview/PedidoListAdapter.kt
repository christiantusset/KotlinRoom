package com.example.joaofreitas.testeroomfinal.activity.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.pedido_item.view.*

class PedidoListAdapter(private val pedidos: MutableList<Pedido> = mutableListOf(),
                        private val context: Context,
                        var onItemClickListener: (Pedido) -> Unit = {}) : RecyclerView.Adapter<PedidoListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View = LayoutInflater.from(context).inflate(R.layout.pedido_item, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return pedidos.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val pedido: Pedido = pedidos[position]
		holder.bind(pedido)
	}

	fun add(products: List<Pedido>) {
		this.pedidos.addAll(products)
		notifyItemRangeInserted(0, products.size)
	}

	fun alteraTodosPedidos(pedidos: List<Pedido>) {
		this.pedidos.clear()
		this.pedidos.addAll(pedidos)
		notifyDataSetChanged()
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val title = itemView.pedido_item_title
		private val total = itemView.pedido_item_total
		private lateinit var pedido: Pedido

		init {
			itemView.setOnClickListener {
				if (::pedido.isInitialized) {
					onItemClickListener(pedido)
				}
			}
		}

		fun bind(pedido: Pedido) {
			this.pedido = pedido
			this.title.text = pedido.nome
			this.total.text = pedido.total.toString()
		}
	}
}
