package com.fiap.inventario.view;

import java.util.Arrays;
import java.util.List;

import com.fiap.inventario.EstoqueBOStub;
import com.fiap.inventario.EstoqueBOStub.Listar;
import com.fiap.inventario.EstoqueBOStub.ListarResponse;
import com.fiap.inventario.EstoqueBOStub.ProdutoTO;


public class ListarView {

	public static void main(String[] args) {
		
		try {
			EstoqueBOStub stub = new EstoqueBOStub();
			Listar params = new Listar();
			ListarResponse resp = stub.listar(params);
			
			ProdutoTO[] array = resp.get_return();
			
			//Transformar o Array em Lista
			List<ProdutoTO> lista = Arrays.asList(array);
			
			for (ProdutoTO p : lista) {
				System.out.println("Produto: "+p.getNome());
				System.out.println("Preço: "+ p.getPreco());
				System.out.println("Quantidade: "+p.getQuantidade());
				System.out.println("***********************");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
}
