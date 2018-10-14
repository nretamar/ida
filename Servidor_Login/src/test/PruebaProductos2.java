package test;

import java.util.List;

import controlador.ProductoControlador;
import dto.ProductoDTO;

public class PruebaProductos2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<ProductoDTO> productos = ProductoControlador.getInstancia().findAllProductos();
		
		for(ProductoDTO item : productos)
		{
			PruebaProductos.imprimirProducto(item);
		}
	}

}
