package br.com.fiap.ws.resource;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


import br.com.fiap.ws.dao.ProdutoDAO;
import br.com.fiap.ws.dao.impl.ProdutoDAOImpl;
import br.com.fiap.ws.entity.Produto;
import br.com.fiap.ws.singleton.EntityManagerFactorySingleton;

@Path("/produto")
public class ProdutoResource {

	//MÉTODOS			STATUS CODE HTTP 
	//GET			READ 		200	
	//POST			CREATE		201
	//PUT			UPDATE		200
	//DELETE		DELETE		204 NO CONTENT
	//	
	
	private ProdutoDAO dao;
	
	public ProdutoResource(){
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		dao = new ProdutoDAOImpl(em);
	}
	
	//buscar um produto pelo id (GET)
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscar(@PathParam("id") int codigo) {
		return dao.read(codigo);
	}
	//listar os produtos (GET)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listar(){
		return dao.list();
	}
	//criar um novo produto (POST)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Produto produto, @Context UriInfo url) {
		try {
			dao.create(produto);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		UriBuilder b = url.getAbsolutePathBuilder();
		b.path(String.valueOf(produto.getCodigo()));
		return Response.created(b.build()).build();
	}
	
	//atualizar um produto existente (PUT)
	@PUT
	@Path("id")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Produto produto, @PathParam("id") int codigo) {
		try {
			produto.setCodigo(codigo);
			dao.update(produto);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	//deletar um produto existente (DELETE)
	@DELETE
	@Path("id")
	public void remover(@PathParam("id")int codigo) {
		try {
			dao.delete(codigo);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	
}
