package br.com.spt.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Seguro;
import br.com.spt.banco.service.SeguroService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/seguro") // http://localhost:8080/SPT_4_JAVA/api/seguro
public class SeguroResource {

	private SeguroService service;

	public SeguroResource() throws ClassNotFoundException, SQLException {
		service = new SeguroService();
	}

	// POST http://localhost:8080/SPT_4_JAVA/api/seguro/ (Cadastrar um
	// seguro)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Seguro seguro, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(seguro);
			// Recupera o path (URL atual(http://localhost:8080/SPT_4_JAVA/api/seguro/))
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			// Adiciona o nome do seguro que foi criado na URL
			// uriBuilder.path(String.valueOf(seguro.getNome()));
			uriBuilder.path((seguro.getFormaPagamento()));
			// Retornar o status 201 com a URL para acessar o seguro criado
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			// Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	// GET http://localhost:8080/SPT_4_JAVA/api/seguro (Listar todos os
	// seguros)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Seguro> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/SPT_4_JAVA/api/seguro/Rafael (Apagar um produto)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int idSeguro) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idSeguro);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/SPT_4_JAVA/api/produto/1 (Atualizar um seguro)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Seguro seguro, @PathParam("id") int idSeguro)
			throws ClassNotFoundException, SQLException {
		try {
			seguro.setIdSeguro(idSeguro);
			service.atualizar(seguro);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}


}
