package br.com.fiap.ws.service;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.ws.to.Produto;

public class ProdutoService {

	private Client client = Client.create();
	private static final String URL = "http://localhost:8080/07-WS-Restful-Server/rest/produto/";
	
	public Produto pesquisar (int codigo) throws Exception { 
		//CRIA A URL COM O CÓDIGO CONCATENADO NO FINAL. PODE UTILIZAR O + NO LUGAR DO PATH
		WebResource w = client.resource(URL).path(String.valueOf(codigo));
		
		// Chama o web service
		ClientResponse response = w
			// Recebe o JSON
			.accept(MediaType.APPLICATION_JSON)
			.get(ClientResponse.class);
		
		if(response.getStatus()!=200) {
			throw new Exception ("Erro " 
						+ response.getStatus());
		}
		// Retorna o prdoduto encontrado
		return response.getEntity(Produto.class);
	}
	
}
