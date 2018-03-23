package br.com.fiap.ws.view;

import java.util.Scanner;

import br.com.fiap.ws.service.ProdutoService;

public class RemoveView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("C�digo: ");
		int codigo = sc.nextInt();
		
		ProdutoService service = new ProdutoService();
		try {
			service.remover(codigo);
			System.out.println("Removido!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		sc.close();
	}
	
}
