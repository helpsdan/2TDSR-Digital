package br.com.fiap.ws.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.ws.to.Veiculo;

public class VeiculoService {

	private static final String URL = "http://localhost:8080/PS-Server/rest/veiculo";	
	
	public Client client = Client.create();
	
	/* MÉTODOS */
	/* REMOVER */
	public void remover(int codigo) throws Exception {
		WebResource resource = client.resource(URL).path(String.valueOf(codigo));
		ClientResponse resp = resource.delete(ClientResponse.class);
		if (resp.getStatus() != 204) {
			throw new Exception("Erro: " + resp.getStatus());
		}
	}
	
	/* PESQUISAR */
	public Veiculo pesquisar(int codigo) throws Exception {
		WebResource resource = client.resource(URL).path(String.valueOf(codigo));
		ClientResponse resp = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			throw new Exception("Erro: " + resp.getStatus());
		}
		return resp.getEntity(Veiculo.class);
		
	}
	
	/* CADASTRAR */
	public void cadastar(Veiculo veiculo) throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, veiculo);
		if (resp.getStatus() != 201) {
			throw new Exception("Erro: " + resp.getStatus());
		}		
	}
	
	/* ATUALIZAR */
	public void atualizar(Veiculo veiculo) throws Exception {
		WebResource resource = client.resource(URL).path(String.valueOf(veiculo.getCodigo()));
		ClientResponse resp = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, veiculo);
		if(resp.getStatus() != 200) {
			throw new Exception("Erro: " + resp.getStatus());
		}
	}
	
	/* LISTAR */
	public List<Veiculo> listar() throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse resp = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			throw new Exception("Erro: " + resp.getStatus());
		}
		return Arrays.asList(resp.getEntity(Veiculo[].class));
	}
}
