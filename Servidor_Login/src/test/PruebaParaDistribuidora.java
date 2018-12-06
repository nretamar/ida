package test;



public class PruebaParaDistribuidora {

	public static void main(String[] args) {
		
		//Correr cada comando de a uno a la vez
		
		// ----------- FASE 1 -----------
		PruebaProductos.crearProveedor1();
		PruebaProductos.crearProveedor2();
		PruebaProductos.crearProveedor3();
		
		PruebaProductos.crearProducto1(100);//minimo 50
		PruebaProductos.crearProducto2(100);//minimo 75
		
		
		// ----------- FASE 2 -----------
		//System.out.println("\n\n\nCREO PEDIDO SIN DISTRIBUIDORA\n");
		PruebaPedidos.crearPedido();
		//System.out.println("\nFIN 1\n\n");
		
		//System.out.println("\n\n\nCREO PEDIDO COOONNNN DISTRIBUIDORA\n");
		PruebaPedidos.crearPedido2();
		
		
		
		
		
	}

}
