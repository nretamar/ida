package testConexiones;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import businessDelegate.ComprasDelegate;
import businessDelegate.ProductoDelegate;
import dto.ProductoDTO;
import dto.RemitoDTO;
import dto.RemitoItemDTO;
import excepciones.GenericRemoteException;

public class PruebaComprasDelegateSwing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ProductoDTO p = ProductoDelegate.getInstancia().buscarProductoByCodigoDeBarras("123");
			
			List<RemitoItemDTO> items = new ArrayList<RemitoItemDTO>();
			RemitoItemDTO item = new RemitoItemDTO();
			item.setIdRemitoItem(null);
			item.setProducto(p);
			item.setCantidad(5);
			items.add(item);
			RemitoDTO remito = ComprasDelegate.getInstancia().recepcionarCompra(items);
			
			if(remito != null)
			{
				for(RemitoItemDTO lista : remito.getProductosRecibidos()) {
					System.out.println("c:" + lista.getCantidad() + " p:" + p.getDescripcion());
				}
			} else {
				JOptionPane.showMessageDialog(null, "No recibir el producto");
			}
			
			
		} catch (GenericRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
