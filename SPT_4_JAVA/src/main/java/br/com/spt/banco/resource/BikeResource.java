package br.com.spt.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Bike;
import br.com.spt.banco.service.BikeService;
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

@Path("/bike") // http://localhost:8080/SPT_4_JAVA/api/bike
public class BikeResource {

	private BikeService service;

	public BikeResource() throws ClassNotFoundException, SQLException {
		service = new BikeService();
	}

	// POST http://localhost:8080/SPT_4_JAVA/api/bike/ (Cadastrar um
	// bike)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Bike bike, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(bike);
			// Recupera o path (URL atual(http://localhost:8080/SPT_4_JAVA/api/bike/))
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			// Adiciona o nome do bike que foi criado na URL
			// uriBuilder.path(String.valueOf(bike.getNome()));
			uriBuilder.path((bike.getMarcaBike()));
			// Retornar o status 201 com a URL para acessar o bike criado
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			// Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	// GET http://localhost:8080/SPT_4_JAVA/api/bike (Listar todos os
	// bikes)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bike> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/SPT_4_JAVA/api/bike/Rafael (Apagar um produto)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int idBike) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idBike);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/SPT_4_JAVA/api/produto/1 (Atualizar um bike)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Bike bike, @PathParam("id") int idBike)
			throws ClassNotFoundException, SQLException {
		try {
			bike.setIdBike(idBike);
			service.atualizar(bike);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
