package com.example.joaofreitas.testeroomfinal.ui.pedido.list


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import br.com.maximasistemas.arch.mvp.view.MvpListaView
import br.com.maximasistemas.arch.mvp.view.activities.MvpListaActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.PedidoRepository
import com.example.joaofreitas.testeroomfinal.ui.pedido.detalhes.PedidoDetalhesActivity
import com.example.joaofreitas.testeroomfinal.views.activity.ui.FormularioPedidoActivity
import kotlinx.android.synthetic.main.activity_list_pedido.*
import org.koin.android.ext.android.inject

class ListPedidoActivity : MvpListaActivity<ListPedidoView, ListPedidoPresenter>(), ListPedidoView {

	private val pedidoRepository: PedidoRepository by inject()

	companion object {
		const val TITLE_APPBAR = "Pedido"
	}

	override fun obterClassePresenter(): Class<ListPedidoPresenter> = ListPedidoPresenter::class.java

	override fun obterRepository(): PedidoRepository = pedidoRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list_pedido)
		title = TITLE_APPBAR

		adapter = PedidoListAdapter { pedido -> PedidoDetalhesActivity.startActivity(pedido, this) }
		configuraBotaoFazerPedido()
		//TODO chamar os metodos no configure LiveData, detalhe o pedidoRepository parece não ter sido iniciado
		configureLiveData(pedidoRepository)
	}

	//TODO achar uma forma de fazer o liveData funcionar
	private fun configureLiveData(pedidoRepository: PedidoRepository) {
		val liveData = pedidoRepository.obtemPedidosLiveData()
		liveData.observe(this, Observer { pedidos ->
			pedidos?.let {
//				pedidoRepository.consultarPedidos()
			}
		})
	}

	private fun configuraBotaoFazerPedido() {
		list_pedido_add_pedido.setOnClickListener {
			val abreFormularioPedido = Intent(this, FormularioPedidoActivity::class.java)
			startActivity(abreFormularioPedido)
		}
	}
}

interface ListPedidoView : MvpListaView {
	fun obterRepository(): PedidoRepository
}
