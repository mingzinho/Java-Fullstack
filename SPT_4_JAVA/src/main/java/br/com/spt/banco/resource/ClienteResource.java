package br.com.spt.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Cliente;
import br.com.spt.banco.service.ClienteService;
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
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/cliente") // http://localhost:8080/SPT_4_JAVA/api/cliente
public class ClienteResource {

	private ClienteService service;

	public ClienteResource() throws ClassNotFoundException, SQLException {
		service = new ClienteService();
	}

	// POST http://localhost:8080/SPT_4_JAVA/api/cliente/ (Cadastrar um
	// cliente)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Cliente cliente, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(cliente);
			// Recupera o path (URL atual(http://localhost:8080/SPT_4_JAVA/api/cliente/))
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			// Adiciona o nome do cliente que foi criado na URL
			// uriBuilder.path(String.valueOf(cliente.getNome()));
			uriBuilder.path((cliente.getNomeCliente()));
			// Retornar o status 201 com a URL para acessar o cliente criado
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			// Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	// GET http://localhost:8080/SPT_4_JAVA/api/cliente (Listar todos os
	// clientes)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/SPT_4_JAVA/api/cliente/Rafael (Apagar um produto)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int idCliente) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idCliente);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/SPT_4_JAVA/api/produto/1 (Atualizar um cliente)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Cliente cliente, @PathParam("id") int idCliente)
			throws ClassNotFoundException, SQLException {
		try {
			cliente.setIdCliente(idCliente);
			service.atualizar(cliente);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	

}
