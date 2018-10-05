package zPaquetesBasura;

import entity.PedidoItemEntity;
import model.PedidoItem;

public class PedidoItemDAO {
	
	public static PedidoItemDAO instancia;
	
	public static PedidoItemDAO getInstancia() {
		if (instancia == null)
			instancia = new PedidoItemDAO();
		return instancia;
	}
	
	public PedidoItem toNegocio(PedidoItemEntity pedido) {

		PedidoItem pedi = new PedidoItem();
		pedi.setIdPedidoItem(pedido.getIdPedidoItem());
		//pedi.setProd
		
		return pedi;

	}
}
