package br.com.spt.banco.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.spt.banco.exception.BadInfoException;
import br.com.spt.banco.exception.IdNotFoundException;
import br.com.spt.banco.model.Pagamento;
import br.com.spt.banco.service.PagamentoService;
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

@Path("/pagamento") // http://localhost:8080/SPT_4_JAVA/api/pagamento
public class PagamentoResource {

	private PagamentoService service;

	public PagamentoResource() throws ClassNotFoundException, SQLException {
		service = new PagamentoService();
	}

	// POST http://localhost:8080/SPT_4_JAVA/api/pagamento/ (Cadastrar um
	// pagamento)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Pagamento pagamento, @Context UriInfo uri)
			throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(pagamento);
			// Recupera o path (URL atual(http://localhost:8080/SPT_4_JAVA/api/pagamento/))
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			// Adiciona o nome do pagamento que foi criado na URL
			// uriBuilder.path(String.valueOf(pagamento.getNome()));
			uriBuilder.path((pagamento.getFormaPagamento()));
			// Retornar o status 201 com a URL para acessar o pagamento criado
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			// Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	// GET http://localhost:8080/SPT_4_JAVA/api/pagamento (Listar todos os
	// pagamentos)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pagamento> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}

	// DELETE http://localhost:8080/SPT_4_JAVA/api/pagamento/Rafael (Apagar um produto)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int idPagamento) throws ClassNotFoundException, SQLException {
		try {
			service.remover(idPagamento);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	// PUT http://localhost:8080/SPT_4_JAVA/api/produto/1 (Atualizar um pagamento)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Pagamento pagamento, @PathParam("id") int idPagamento)
			throws ClassNotFoundException, SQLException {
		try {
			pagamento.setIdPagamento(idPagamento);
			service.atualizar(pagamento);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
