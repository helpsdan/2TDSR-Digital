package br.com.fiap.service;

import org.apache.axis2.AxisFault;

import br.com.fiap.inventario.EstoqueBOStub;
import br.com.fiap.inventario.EstoqueBOStub.ProdutoTO;

public class EstoqueService {

	private EstoqueBOStub stub;
	
	public EstoqueService() throws AxisFault {
		stub = new EstoqueBOStub();
	}
	

	
}
