package com.usp.testeapi.testeapi.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.usp.testeapi.entidade.Cartao;
import com.usp.testeapi.respositorio.RepositorioCartao;

@Path("/rest")
public class ApplicationResource {
	@GET
	@Path("/sayhi")
	public Response sayHi() {
		return Response.status(Response.Status.OK)
				.entity("hello humanos").build();
	}
	@GET
	@Path("/cartoes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cartao> cartoesAll() {
		RepositorioCartao rc = new RepositorioCartao();
		return rc.listarTodos();
	}
	@GET
	@Path("/cartoes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cartao cartoesId(@PathParam("id") int id) {
		RepositorioCartao rc = new RepositorioCartao();
		return rc.obterPorId(id);
	}
	@DELETE
	@Path("/cartoes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCartoes(@PathParam("id") int id) {
		RepositorioCartao rc = new RepositorioCartao();
		Cartao c = rc.obterPorId(id);
		if(c == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		try {
			rc.remover(c);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
	@POST
	@Path("/cartoes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) //colocar conteudo JSON no body e Content-Type : application/json
	public Response insertCartao(Cartao cartao) {
		try {
			RepositorioCartao rc = new RepositorioCartao();
			rc.salvar(cartao);
			return Response.status(Response.Status.CREATED).entity(cartao).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
	@PUT
	@Path("/cartoes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) //colocar conteudo JSON no body e Content-Type : application/json
	public Response editCartao(@PathParam("id") int id, Cartao cartao) {
		RepositorioCartao rc = new RepositorioCartao();
		Cartao c = rc.obterPorId(id);
		if(c == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		try {
			cartao.setId(id);
			rc.salvar(cartao);
			System.out.println("EDITADO");
			System.out.println(cartao.toString());
			return Response.status(Response.Status.OK).entity(cartao).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
}
