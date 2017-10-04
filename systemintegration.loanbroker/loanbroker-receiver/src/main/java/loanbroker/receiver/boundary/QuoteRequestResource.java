package loanbroker.receiver.boundary;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import loanbroker.receiver.controller.QouteRequestController;

@Path("quote")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteRequestResource {

	private QouteRequestController controller = new QouteRequestController();

	@GET
	public Response getRunning() {
		JsonObject input = Json.createObjectBuilder().add("Status", "Running...").build();
		return Response.status(Status.OK).entity(input).build();
	}

	@POST
	@Path("send")
	public Response getQuoteRequest(@QueryParam("ssn") String ssn, @QueryParam("amount") int amount,
			@QueryParam("duration") int duration) {

		JsonObject input = Json.createObjectBuilder().add("ssn", ssn).add("amount", amount).add("duration", duration)
				.build();
		return controller.sendMessageBasic(input).build();
	}

}
