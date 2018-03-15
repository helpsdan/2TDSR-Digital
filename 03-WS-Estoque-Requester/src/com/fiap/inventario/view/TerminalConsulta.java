package com.fiap.inventario.view;

import java.util.Scanner;

import org.apache.axis2.AxisFault;

import com.fiap.inventario.EstoqueBOStub;
import com.fiap.inventario.EstoqueBOStub.ConsultarProduto;
import com.fiap.inventario.EstoqueBOStub.ConsultarProdutoResponse;
import com.fiap.inventario.EstoqueBOStub.ProdutoTO;

public class TerminalConsulta {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		try {
			EstoqueBOStub stub = new EstoqueBOStub();
			ConsultarProduto params = new ConsultarProduto();
			System.out.println("Digite o código: ");
			params.setCodigo(sc.nextInt());
			
			ConsultarProdutoResponse resp = stub.consultarProduto(params);
			
			ProdutoTO produto = resp.get_return();
			System.out.println(produto.getNome());
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		sc.close();
		
		
	}

}
