package loanbroker.receiver.boundary;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import loanbroker.receiver.controller.QouteRequestController;

@Path("quote")
@Produces(MediaType.APPLICATION_JSON)
public class QuoteRequestResource {

	private QouteRequestController controller = new QouteRequestController();

	@POST
	@Path("send")
	public Response getQuoteRequest(@QueryParam("ssn") long ssn, @QueryParam("amount") int amount,
			@QueryParam("duration") int duration) {

		JsonObject input = Json.createObjectBuilder().add("ssn", ssn).add("amount", amount).add("duration", duration)
				.build();
		return controller.sendMessageBasic(input).build();
	}

}
