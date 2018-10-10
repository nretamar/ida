package dao;

public class OrdenDeCompraDAO {
	
	private static OrdenDeCompraDAO instancia;

	private OrdenDeCompraDAO() {
	}

	public static OrdenDeCompraDAO getInstance() {
		if (instancia == null)
			instancia = new OrdenDeCompraDAO();
		return instancia;
	}
	
	//TODO
	
	
	
}
