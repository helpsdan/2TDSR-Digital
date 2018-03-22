package br.com.fiap.ws.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import br.com.fiap.ws.dao.ProdutoDAO;
import br.com.fiap.ws.dao.impl.ProdutoDAOImpl;
import br.com.fiap.ws.entity.Produto;
import br.com.fiap.ws.exception.CommitException;
import br.com.fiap.ws.exception.KeyNotFoundException;
import br.com.fiap.ws.singleton.EntityManagerFactorySingleton;

@Path("/produto")
public class ProdutoResource {

	//MÉTODOS			STATUS CODE HTTP 
	//GET			READ 		200	
	//POST			CREATE		201
	//PUT			UPDATE		200
	//DELETE		DELETE		204 NO CONTENT
	//							500 (NÃO CONSEGUIU CONECTAR AO SERVIDOR)
	
	private ProdutoDAO dao;

	public ProdutoResource() {
		// Inicializar o DAO
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		dao = new ProdutoDAOImpl(em);
	}

	//Buscar um produto pelo "id"	
	@GET
	@Path("{id}")
	// ELE É UM MÉTODO PRODUCES QUANDO ELE RETORNAR A CLASSE
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscar(@PathParam("id") int codigo) {
		//ENTREGA UM STATUS DE CODE 200 "OK"
		return dao.read(codigo);
	}

	//Listar os produtos
	@GET
	// ELE É UM MÉTODO PRODUCES QUANDO ELE RETORNAR A CLASSE
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listar() {
		//ENTREGA UM STATUS DE CODE 200 "OK"
		return dao.list();
	}
	
	
	// CÓDIGO PARA CRIAR UM NOVO PRODUTO
	@POST
	// ELE É UM MÉTOCO CONSUMES QUANDO ELE RECEBE UM OBJETO DA CLASSE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Produto produto, @Context UriInfo url) {
		try {
			dao.create(produto);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// EXCEÇÃO COM STATUS DE CODE 201 "CREATED"
			return Response.serverError().build();
		}
		// CRIA A URL PARA ACESSAR O RECURSO CRIADO
		UriBuilder b = url.getAbsolutePathBuilder();
		b.path(String.valueOf(produto.getCodigo()));
		return Response.created(b.build()).build();
	}
	
	// CÓDIGO PARA ATUALIZAR UM PRODUTO EXISTENTE PELO PathParam "id"
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	// ELE É UM MÉTOCO CONSUMES QUANDO ELE RECEBE UM OBJETO DA CLASSE
	public Response atualizar(Produto produto, @PathParam("id") int codigo) {
		try {
			produto.setCodigo(codigo);
			dao.update(produto);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			// BOAS PRÁTICAS É SEMPRE TER UM RETURN NO TRY CATCH, NÃO PODE DEIXAR O TRY_CATCH SEM NADA.
			// EXCEÇÃO COM STATUS DE CODE 200 (OK)
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	
	// DELETAR UM PRODUTO EXISTENTE PELO PathParam "id"	
	@DELETE
	@Path("{id}")
	public void remover(@PathParam("id")int codigo) {
		try {
			dao.delete(codigo);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//EXCEÇÃO COM STATUS DE CODE 500
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		// ENTREGA AO USUÁRIO UM STATUS DE CODE 204, "NO CONTENT"
	}
	
}
