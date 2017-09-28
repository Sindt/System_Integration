package loanbroker.receiver.boundary;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import loanbroker.receiver.controller.QouteRequestController;

@Path("quote")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuoteRequestResource {

	QouteRequestController controller = new QouteRequestController();

	@GET
	@Path("send")
	public Response getQuoteRequest(@FormParam("message") JsonObject input) {
		return controller.sendMessageBasic(input).build();
	}

}
