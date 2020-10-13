package com.mavha.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mavha.entities.Listing;
import com.mavha.entities.SpecialPrices;
import com.mavha.entities.Users;
import com.mavha.model.ListingDTO;
import com.mavha.model.SpecialPriceDTO;
import com.mavha.model.SpecialPriceSearchDTO;

@Stateless
@Named
public class ListingLogic {

	 @PersistenceContext(unitName = "airbnb")
	 private EntityManager em;
	 
	 public List<ListingDTO> obtenerListing(String uuid) {
		 List<ListingDTO> listings = new ArrayList<>();
		 try {
			 String consulta = "SELECT id, owner_id, name, slug, description, adults, children, is_pets_allowed, "
			 				 + "	base_price, cleaning_fee, image_url, weekly_discount, monthly_discount "
			 				 + "FROM listings "
			 				 + "WHERE (:PId is null or id = :PId)";
			 Query qry = em.createNativeQuery(consulta);
			 qry.setParameter("PId", uuid);
			 List<Object[]> info = qry.getResultList();
			 
			 String consultaSpecialPrices = "SELECT listing_id, date, price " +
					 						"FROM special_prices ";
			 Query qrySP = em.createNativeQuery(consultaSpecialPrices);
			 List<Object[]> infoSpecialPrices = qrySP.getResultList();
			 
			 List<SpecialPriceSearchDTO> preciosEspeciales = new ArrayList<>();
			 for (Object[] unPrecio :  infoSpecialPrices) {
				 SpecialPriceSearchDTO unPrecioEspecial = new SpecialPriceSearchDTO();
				 unPrecioEspecial.setListing_id(unPrecio[0].toString());
				 unPrecioEspecial.setDate(unPrecio[1].toString());
				 unPrecioEspecial.setPrice(Double.parseDouble(unPrecio[2].toString()));
				 preciosEspeciales.add(unPrecioEspecial);
			 }
			 
			 for (Object[] unListing :  info) {
				 ListingDTO unListingDTO = new ListingDTO();
				 unListingDTO.setId(unListing[0].toString());
				 unListingDTO.setOwner_id(unListing[1].toString());
				 unListingDTO.setName(unListing[2].toString());
				 unListingDTO.setSlug(unListing[3].toString());
				 unListingDTO.setDescription(unListing[4].toString());
				 unListingDTO.setAdults(Integer.parseInt(unListing[5].toString()));
				 unListingDTO.setChildren(Integer.parseInt(unListing[6].toString()));
				 unListingDTO.setIs_pets_allowed(Boolean.parseBoolean(unListing[7].toString()));
				 unListingDTO.setBase_price(Double.parseDouble(unListing[8].toString()));
				 unListingDTO.setCleaning_fee(Double.parseDouble(unListing[9].toString()));
				 unListingDTO.setImage_url(unListing[10].toString());
				 unListingDTO.setWeekly_discount(Double.parseDouble(unListing[11].toString()));
				 unListingDTO.setMonthly_discount(Double.parseDouble(unListing[12].toString()));
				 
				 for (SpecialPriceSearchDTO unPrecio : preciosEspeciales) {
					 if (unPrecio.getListing_id().equals(unListingDTO.getId())) {
						 unListingDTO.getSpecial_prices().add(unPrecio);
					 }
				 }
				 listings.add(unListingDTO);
			 }
			 return listings;
			 
		 } catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e);
		}
		 
	 }
	 
	 public ListingDTO actualizarListing(ListingDTO listingDTO, String id) {
		 try {
			Listing listing = em.find(Listing.class, id);
			listing.setAdults(listingDTO.getAdults());
			listing.setBasePrice(listingDTO.getBase_price());
			listing.setChildren(listingDTO.getChildren());
			listing.setCleaningFee(listingDTO.getCleaning_fee());
			listing.setDescription(listingDTO.getDescription());
			listing.setImageUrl(listingDTO.getImage_url());
			listing.setIsPetsAllowed(listingDTO.getIs_pets_allowed());
			listing.setMonthlyDiscount(listingDTO.getMonthly_discount());
			listing.setName(listingDTO.getName());
			if (listingDTO.getOwner_id() != null) {
				listing.setUser(this.em.find(Users.class, listingDTO.getOwner_id()));
			}
			listing.setWeeklyDiscount(listingDTO.getWeekly_discount());
			listingDTO.setId(id);
			this.em.merge(listing);
			this.em.flush();
			return listingDTO;
		 } catch (Exception e) {
			 e.printStackTrace();
			 throw new EJBException(e);
		}
		 
	 }
	 
	 public ListingDTO agregarListing(ListingDTO listingDTO) {
		 try {
			Listing listing = new Listing();
			listing.setAdults(listingDTO.getAdults());
			listing.setBasePrice(listingDTO.getBase_price());
			listing.setChildren(listingDTO.getChildren());
			listing.setCleaningFee(listingDTO.getCleaning_fee());
			listing.setDescription(listingDTO.getDescription());
			listing.setId("d290f1ee-6c54-4b01-90e6-d701748f0851"); // Cambiar esto para que se autogenere
			listing.setImageUrl(listingDTO.getImage_url());
			listing.setIsPetsAllowed(listingDTO.getIs_pets_allowed());
			listing.setMonthlyDiscount(listingDTO.getMonthly_discount());
			listing.setName(listingDTO.getName());
			listing.setSlug("1");
			listing.setUser(this.em.find(Users.class, "d290f1ee-6c54-4b01-90e6-d701748f0851")); // Cambiar esto para que el usuario sea obtenido por JWT
			listing.setWeeklyDiscount(listingDTO.getWeekly_discount());
			this.em.persist(listing);
			this.em.flush();
			String id = listing.getId();
			listingDTO.setOwner_id(listing.getUser().getId());
			listingDTO.setId(id);
			return listingDTO;
		 } catch (Exception e) {
			 e.printStackTrace();
			 throw new EJBException(e);
		}
		 
	 }
	 
	 public void eliminarListing(String id) {
		 try {
			em.remove(em.find(Listing.class, id));
		 } catch (Exception e) {
			 e.printStackTrace();
			 throw new EJBException(e);
		}
		 
	 }
	 
	 public void eliminarSpecialPrice(String idPrecio) {
		 try {
			 em.remove(em.find(SpecialPrices.class, idPrecio));
		 } catch (Exception e) {
			 e.printStackTrace();
			 throw new EJBException(e);
		}
		 
	 }
	 
	 public SpecialPriceDTO addSpecialPrice(SpecialPriceDTO specialPrice, String id) {
		 try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
			SpecialPrices precio = new SpecialPrices();
			precio.setDate(formato.parse(specialPrice.getDate()));
			precio.setId("d290f1ee-6c52-4b02-90e6-d701748f9854"); // Cambiar para que se autogenere
			precio.setListing(em.find(Listing.class, id));
			precio.setPrice(specialPrice.getPrice());
			em.persist(precio);
			em.flush();
			specialPrice.setId(precio.getId());
			return specialPrice;
		 } catch (Exception e) {
			 e.printStackTrace();
			 throw new EJBException(e);
		}
		 
	 }
	
}
