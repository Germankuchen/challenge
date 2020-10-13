package com.mavha.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mavha.logic.ListingLogic;
import com.mavha.model.CheckoutDTO;
import com.mavha.model.IntervaloDTO;
import com.mavha.model.ListingDTO;
import com.mavha.model.SpecialPriceDTO;
import com.mavha.routes.ListingApi;


public class ListingImpl implements ListingApi {
	
	@Inject
	ListingLogic listingLogic;
	
	@Override
	public Response getAllListing() {
		try {
			List<ListingDTO> listings = listingLogic.obtenerListing(null);
			return Response.ok(listings, MediaType.APPLICATION_JSON).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response getListing(String uuid) {
		try {
			List<ListingDTO> listings = listingLogic.obtenerListing(uuid);
			return Response.ok(listings, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response getCheckout(IntervaloDTO intervalo, String uuid) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date checkIn = formato.parse(intervalo.getCheckin());
			Date checkOut = formato.parse(intervalo.getCheckout());
			if (checkIn.before(new Date())) {
				return Response.status(400).entity("{\"mensaje\": \"La fecha de checkin no puede ser anterior a la fecha de hoy\"}").build();
			}
			if (checkOut.before(new Date())) {
				return Response.status(400).entity("{\"mensaje\": \"La fecha de checkout no puede ser anterior a la fecha de hoy\"}").build();
			}
			if (checkOut.before(checkIn)) {
				return Response.status(400).entity("{\"mensaje\": \"La fecha de checkout no puede ser anterior a la fecha de checkin\"}").build();
			}
			long diffInMillies = Math.abs(checkOut.getTime() - checkIn.getTime());
		    Long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff > 28L) {
				return Response.status(400).entity("{\"mensaje\": \"No puede ser superior a los 28 días\"}").build();
			}
			CheckoutDTO checkoutDTO = new CheckoutDTO();
			Integer cantidadDeDias = Integer.parseInt(diff.toString());
			checkoutDTO.setNights_count(cantidadDeDias);
			List<ListingDTO> listings = listingLogic.obtenerListing(uuid);
			if (listings.size() == 0) {
				return Response.status(400).entity("{\"mensaje\": \"Error no existe esa reserva\"}").build();
			}
			ListingDTO listing = listings.get(0);
			Double cost = listing.getBase_price() * cantidadDeDias;
			Double discount = new Double(0);
			
			if (cantidadDeDias >= 7) {
				discount = listing.getWeekly_discount();
				cost = cost * discount;
			}
			Double cleaningPrice = listing.getCleaning_fee() * cantidadDeDias;
			checkoutDTO.setCleaning_fee(cleaningPrice);
			checkoutDTO.setDiscount(discount * cantidadDeDias);
			checkoutDTO.setNights_cost(cost);
			checkoutDTO.setTotal(cost + cleaningPrice);
			return Response.ok(checkoutDTO, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("{\"mensaje\": \"" + e.getMessage() + "\"}").build();
		}
		
	}

	@Override
	public Response editListing(ListingDTO listingDTO, String uuid) {
		try {
			listingDTO = listingLogic.actualizarListing(listingDTO, uuid);
			return Response.ok(listingDTO, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response addListing(ListingDTO listingDTO) {
		try {
			listingDTO = listingLogic.agregarListing(listingDTO);
			return Response.ok(listingDTO, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response addSpecialPrices(SpecialPriceDTO specialPrice, String id) {
		try {
			specialPrice = listingLogic.addSpecialPrice(specialPrice, id);
			return Response.ok(specialPrice, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response deleteListing(String id) {
		try {
			listingLogic.eliminarListing(id);
			return Response.ok("{\"id\": \"" + id + "\"}", MediaType.APPLICATION_JSON).build();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response deleteSpecialPrice(String uuid, String id) {
		try {
			listingLogic.eliminarSpecialPrice(id);
			return Response.ok("{\"id\": \"" + id + "\"}", MediaType.APPLICATION_JSON).build();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	

}