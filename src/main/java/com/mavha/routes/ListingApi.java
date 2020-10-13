package com.mavha.routes;

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

import com.mavha.model.IntervaloDTO;
import com.mavha.model.ListingDTO;
import com.mavha.model.SpecialPriceDTO;

@Path("/listings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ListingApi {
	
	 @GET
	 public Response getAllListing();
	
	 @GET
	 @Path("/{uuid}")
	 public Response getListing(@PathParam("uuid") String uuid);
	 
	 @POST
	 @Path("/{uuid}/checkout")
	 public Response getCheckout(IntervaloDTO intervalo, @PathParam("uuid") String uuid);

	 @PUT
	 @Path("/{uuid}")
	 public Response editListing(ListingDTO listing, @PathParam("uuid") String uuid);

	 @POST
	 public Response addListing(ListingDTO listing);
	 
	 @POST
	 @Path("/{uuid}/special-prices")
	 public Response addSpecialPrices(SpecialPriceDTO specialPrice, @PathParam("uuid") String uuid);

	 @DELETE
	 @Path("/{uuid}")
	 public Response deleteListing(@PathParam("uuid") String uuid);
	 
	 @DELETE
	 @Path("/{uuid}/special-prices/{id}")
	 public Response deleteSpecialPrice(@PathParam("uuid") String uuid,
			 @PathParam("id") String id);

}
