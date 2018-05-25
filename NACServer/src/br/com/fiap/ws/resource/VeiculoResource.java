package br.com.fiap.ws.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
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

import br.com.fiap.ws.dao.VeiculoDAO;
import br.com.fiap.ws.dao.impl.VeiculoDAOImpl;
import br.com.fiap.ws.entity.Veiculo;
import br.com.fiap.ws.exception.CommitException;
import br.com.fiap.ws.singleton.EntityManagerFactorySingleton;

@Path("/veiculo")
public class VeiculoResource {

	private VeiculoDAO dao;

	public VeiculoResource() {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		dao = new VeiculoDAOImpl(em);
	}

	@PUT
	@Path("id")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Veiculo veiculo, @PathParam("id") int codigo) {
		try {
			veiculo.setCodigo(codigo);
			dao.atualizar(veiculo);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Veiculo veiculo, @Context UriInfo uri) {

		try {
			dao.cadastrar(veiculo);
			dao.commit();
		} catch (CommitException e) {
			return Response.serverError().build();
		}

		UriBuilder b = uri.getAbsolutePathBuilder();
		b.path(String.valueOf(veiculo.getCodigo())).build();
		return Response.created(b.build()).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> listar() {
		return dao.listar();
	}

}
