package br.com.spt.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Sinistro;
import br.com.spt.banco.service.SinistroService;
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

@Path("/sinistro") // http://localhost:8080/SPT_4_JAVA/api/sinistro
public class SinistroResource {

	private SinistroService service;

	public SinistroResource() throws ClassNotFoundException, SQLException {
		service = new SinistroService();
	}

	// POST http://localhost:8080/SPT_4_JAVA/api/sinistro/ (Cadastrar um
	// sinistro)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Sinistro sinistro, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(sinistro);
			// Recupera o path (URL atual(http://localhost:8080/SPT_4_JAVA/api/sinistro/))
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			// Adiciona o nome do sinistro que foi criado na URL
			// uriBuilder.path(String.valueOf(sinistro.getNome()));
			uriBuilder.path((sinistro.getDataOcorrencia()));
			// Retornar o status 201 com a URL para acessar o sinistro criado
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			// Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	// GET http://localhost:8080/SPT_4_JAVA/api/sinistro (Listar todos os
	// sinistros)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sinistro> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/SPT_4_JAVA/api/sinistro/Rafael (Apagar um produto)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int idSinistro) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idSinistro);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/SPT_4_JAVA/api/produto/1 (Atualizar um sinistro)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Sinistro sinistro, @PathParam("id") int idSinistro)
			throws ClassNotFoundException, SQLException {
		try {
			sinistro.setIdSinistro(idSinistro);
			service.atualizar(sinistro);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
