package edu.northeastern.cs5200.services;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.daos.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

@Path("/restaurant")
public class services {
	
		@GET
		@Path("users")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Customer> getAllUsers() {
			Collection<Customer> customers = new ArrayList<Customer>();
			customers= CustomerDao.findAllCustomer(ConnectionDao.getConnection());
			return customers;
		}
		
		@GET
		@Path("address/{userId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Address getUserAddress(@PathParam("userId") int userId) {
			return AddressDao.findAddressById(ConnectionDao.getConnection(), userId);
		}
		
		@GET
		@Path("phone/{userId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Phone getUserPhone(@PathParam("userId") int userId) {
			return PhoneDao.findPhoneById(ConnectionDao.getConnection(), userId);
		}
		
		
		@GET
		@Path("allUsers")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<User> getAllUsersOnly() {
			return AdminDao.findAllUsers(ConnectionDao.getConnection());
		}
		
		@GET
		@Path("getAllRestOfOwner/{resOId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Restaurant> getAllRestOfOwner(@PathParam("resOId") int resOId){
			RestaurantDao rDao= RestaurantDao.getInstance();
			return rDao.findRestaurantByOwnerId(ConnectionDao.getConnection(), resOId);
		}
		
		@GET
		@Path("getAllRestFollowers/{resOId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Customer> getAllRestFollowers(@PathParam("resOId") int resOId){
			RestaurantOwnerDao roDao= RestaurantOwnerDao.getInstance();
			return roDao.getAllRestFollowers(ConnectionDao.getConnection(), resOId);
		}
		
		@POST
		@Path("deleteUserById/{userId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
		public int deleteUserById(@PathParam("userId") int userId) {
			return AdminDao.deleteUser(ConnectionDao.getConnection(), userId);
	    }
		
		
		
		@POST
		@Path("createFavRestaurant/{custId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
		public int createFavoriteRestaurant(@PathParam("custId") int custId, Restaurant rest) {
			System.out.println("createFavorite restuarant ");
			System.out.println(rest);
	    	return CustomerDao.createFavoriteRestaurant(ConnectionDao.getConnection(), custId, rest);
	    }
		@GET
		@Path("getAllOrders/{custId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<CustomerOrder> getAllOrders(@PathParam("custId") int custId) {
			return CustomerDao.findOrdersOfCustomer(ConnectionDao.getConnection(), custId);
		}
		
		@GET
		@Path("getOrders")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<CustomerOrder>  getOrders() {
			return OrderDao.findAllOrder(ConnectionDao.getConnection());
		}
		
		@GET
		@Path("getAllReviewsAsAdmin")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<RateAndReview> getAllReviewsAsAdmin() {
			return RateAndReviewDao.findAllReviews(ConnectionDao.getConnection());
		}
		
		
		@GET
		@Path("getAllFav/{custId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Restaurant> getAllFav(@PathParam("custId") int custId) {
			return CustomerDao.findFavRestaurant(ConnectionDao.getConnection(), custId);
		}
		
		@GET
		@Path("getAllFavChef/{custId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Chef> getAllFavChef(@PathParam("custId") int custId){
			return CustomerDao.findFavChef(ConnectionDao.getConnection(), custId);
		}
		
		@GET
		@Path("getAllReviews/{custId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<RateAndReview> getAllReviews(@PathParam("custId") int custId) {
			return CustomerDao.findReviewsOfCustomer(ConnectionDao.getConnection(), custId);
		}
		
		
	    @POST
	    @Path("customercreation")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createCustomer(Customer customer) {
	    	System.out.println("createCustomer method");
            return CustomerDao.createCustomer(ConnectionDao.getConnection(), customer);
	    }
	    
	    @POST
	    @Path("customerlogin")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Customer customerLogin(String customerId) {
	    	System.out.println("customerLogin method");
	    	int cId=Integer.parseInt(customerId);
            return CustomerDao.findCustomerById(ConnectionDao.getConnection(), cId);
        
	    }
	    
	    @POST
	    @Path("customer")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Customer customerByCredentials(Customer data) {
	    	System.out.println("customerLogin ");
	    	System.out.println(data.getUsername());
            return CustomerDao.findCustomerByCredentials(ConnectionDao.getConnection(), data.getUsername(), data.getPassword());
        
	    }
	    
	    @POST
	    @Path("admin")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public User userByCredentials(User data) {
	    	return CustomerDao.findUserByCredentials(ConnectionDao.getConnection(), data.getUsername(), data.getPassword());
	    }
	    
	    @POST
	    @Path("chef")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Chef chefByCredentials(Chef data) {
	    	System.out.println("customerLogin ");
	    	System.out.println(data.getUsername());
            return ChefDao.findChefByCredentials(ConnectionDao.getConnection(), data.getUsername(), data.getPassword());
        
	    }
	    
	    @POST
	    @Path("owner")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public ResturantOwner ownerByCredentials(ResturantOwner data) {
	    	System.out.println("customerLogin ");
	    	System.out.println(data.getUsername());
            return RestaurantOwnerDao.findOwnerByCredentials(ConnectionDao.getConnection(), data.getUsername(), data.getPassword());
        
	    }
	    
	    @POST
	    @Path("updatecustomer")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response updateCustomer(int custId, Customer customer) {
	    	System.out.println("updateCustomer method");
	    	CustomerDao.updateCustomer(ConnectionDao.getConnection(), custId, customer);
            return makeResponse("Customer account updated", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("deletecustomer")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response deleteCustomer(int custId) {
	    	System.out.println("In custDelete method");
	    	CustomerDao.deleteCustomer(ConnectionDao.getConnection(), custId);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("chefcreation")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createChef(Chef chef) {
	    	System.out.println("createChef method");
            System.out.println(chef.getEmail()+" -- "+chef.getPassword());
            return ChefDao.createChef(ConnectionDao.getConnection(), chef);
	    }
	    
	    @POST
	    @Path("cheflogin")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Chef chefLogin(int chefId) {
	    	System.out.println("chefLogin method");
            return ChefDao.findChefById(ConnectionDao.getConnection(), chefId);
        
	    }
	    
	    @POST
	    @Path("updatechef")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response updateChef(int chefId, Chef chef) {
	    	System.out.println("updateChef method");
	    	ChefDao.updateChef(ConnectionDao.getConnection(), chefId, chef);
            return makeResponse("Customer account updated", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("deletechef")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response deleteChef(int chefId) {
	    	System.out.println("In chefDelete method");
	    	ChefDao.deleteChef(ConnectionDao.getConnection(), chefId);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("ownercreation")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createOwner(ResturantOwner owner) {
	    	System.out.println("createOwner method");
            System.out.println(owner.getEmail()+" -- "+owner.getPassword());
            return RestaurantOwnerDao.createOwner(ConnectionDao.getConnection(), owner);
	    }
	    
	    @POST
	    @Path("ownerlogin")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public ResturantOwner ownerLogin(int ownerId) {
	    	System.out.println("chefLogin method");
            return RestaurantOwnerDao.findOwnerById(ConnectionDao.getConnection(), ownerId);
        
	    }
	    
	    @POST
	    @Path("update-user/{userId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int updateOwner(@PathParam("userId")int userId, User user) {
	    	return AdminDao.updateUser(ConnectionDao.getConnection(), userId, user);
	    }
	    
	    @POST
	    @Path("update-review/{reviewId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int updateOwner(@PathParam("reviewId")int reviewId, RateAndReview review) {
	    	return RateAndReviewDao.updateRateReview(ConnectionDao.getConnection(), reviewId, review);
	    }
	    
	    @POST
	    @Path("updateowner")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response updateOwner(int ownerId, ResturantOwner owner) {
	    	System.out.println("updateChef method");
	    	RestaurantOwnerDao.updateOwner(ConnectionDao.getConnection(), ownerId, owner);
            return makeResponse("Customer account updated", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("deleteowner")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response deleteOwner(int ownerId) {
	    	System.out.println("In ownerDelete method");
	    	RestaurantOwnerDao.deleteOwner(ConnectionDao.getConnection(), ownerId);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("restaurantcreation/{ownerId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createRestaurant(@PathParam("ownerId")int ownerId,Restaurant restaurant) {
	    	return RestaurantDao.createRestauraunt(ConnectionDao.getConnection(), ownerId,restaurant);
	    }
	    
	    @POST
	    @Path("restaurantlogin")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Restaurant restaurantLogin(int restId) {
	    	System.out.println("restaurantLogin method");
            return RestaurantDao.findRestaurantById(ConnectionDao.getConnection(), restId);
        
	    }
	    
	    @POST
	    @Path("restaurantupdate")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response restaurantUpdate(int restId, Restaurant restaurant) {
	    	System.out.println("restaurantUpdate method");
	    	RestaurantDao.updateRestaurant(ConnectionDao.getConnection(), restId, restaurant);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("deleterestaurant/{resId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int deleteRestaurant(@PathParam("resId") int restId) {
	    	System.out.println("In restDelete method");
	    	return RestaurantDao.deleteRestaurant(ConnectionDao.getConnection(), restId);
	    }
	    
	    @POST
	    @Path("addresscreation")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createAddress(int usrId, Address address) {
	    	return AddressDao.createAddress(ConnectionDao.getConnection(), usrId, address);
	    }
	    
	    @POST
	    @Path("update-address/{addId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int addressUpdate(@PathParam("addId") int addId, Address address) {
	    	return AddressDao.updateAddress(ConnectionDao.getConnection(), addId, address);
	    }
	    
	    @POST
	    @Path("deleteaddress")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response deleteAddress(int addId) {
	    	System.out.println("In deleteAddress method");
	    	AddressDao.deleteAddress(ConnectionDao.getConnection(), addId);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("phonecreation")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createPhone(int usrId, Phone phone) {
	    	return PhoneDao.createPhone(ConnectionDao.getConnection(), usrId, phone);
	    }
	      
	    @POST
	    @Path("update-phone/{phId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int phoneUpdate(@PathParam("phId") int phId, Phone phone) {
	    	System.out.println("phoneUpdate method");
	    	return PhoneDao.updatePhone(ConnectionDao.getConnection(), phId, phone);
	    }
	    
	    @POST
	    @Path("deletephone")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response deletePhone(int phId) {
	    	System.out.println("In deletePhone method");
	    	PhoneDao.deletePhone(ConnectionDao.getConnection(), phId);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("foodRecpcreation/{foodRecpId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createFoodRecp(@PathParam("foodRecpId")int foodRecpId, FoodRecipe foodRecipe) {
	    	return FoodRecipeDao.createFoodRecipe(ConnectionDao.getConnection(), foodRecpId, foodRecipe);
	    }
	    
	    @GET
	    @Path("getAllOrdersOwner/{oId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Collection<Order> createFoodRecp(@PathParam("oId")int oId) {
	    	return RestaurantOwnerDao.getAllOrders(ConnectionDao.getConnection(), oId);
	    }
	    
	    @POST
	    @Path("foodRecpupdate")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response foodRecpUpdate(int frId, FoodRecipe foodRecp) {
	    	System.out.println("foodRecpUpdate method");
	    	FoodRecipeDao.updateFoodRecipe(ConnectionDao.getConnection(), frId, foodRecp);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("deletefoodRecp/{frId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int deleteFoodRecp(@PathParam("frId")int frId) {
	    	System.out.println("In deleteFoodRecp method");
	    	return FoodRecipeDao.deleteFoodRecipe(ConnectionDao.getConnection(), frId);
	    }
	    
	    @POST
	    @Path("createorder")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createOrder(@QueryParam("userId") int userId,@QueryParam("restId") int restId,@QueryParam("fmId") int fmId, Order order) {
	    	System.out.println("createOrder method");
            return OrderDao.createOrder(ConnectionDao.getConnection(), userId, restId, fmId, order);
            
	    }
	    
	    @GET
	    @Path("getAllfollowers/{chefId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Customer> getAllFollwers(@PathParam("chefId") int chefId){
	    	return ChefDao.findAllFollowers(ConnectionDao.getConnection(),chefId);
	    }
	
	    
	    @POST
	    @Path("orderupdate/{ordId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int orderUpdate(@PathParam("ordId")int ordId, Order order) {
	    	System.out.println("orderUpdate method");
	    	return OrderDao.updateOrder(ConnectionDao.getConnection(), ordId, order);
	    }
	    
	    @POST
	    @Path("deleteOrder/{ordId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public int deleteOrder(@PathParam("ordId") int ordId) {
	    	System.out.println("In deleteOrder method");
	    	return OrderDao.deleteOrder(ConnectionDao.getConnection(), ordId);
	    }
	    
	    @POST
	    @Path("createpromotion")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response createPromotion(int custId, int restId, Promotion pr) {
	    	System.out.println("createPromotion method");
            PromotionDao.createPromo(ConnectionDao.getConnection(), custId, restId, pr);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("promotionupdate")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response promotionUpdate(int prId, Promotion promotion) {
	    	System.out.println("promotionUpdate method");
	    	PromotionDao.updatePromo(ConnectionDao.getConnection(), prId, promotion);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("deletepromotion")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response deletePromotion(int prId) {
	    	System.out.println("In deletePromotion method");
	    	PromotionDao.deletePromo(ConnectionDao.getConnection(), prId);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
	    @POST
	    @Path("createrateReview/{orderId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int createRateReview(@PathParam("orderId") int orderId, RateAndReview rateReview) {
	    	System.out.println("createRateReview method");
	    	return RateAndReviewDao.createRateReview(ConnectionDao.getConnection(), orderId, rateReview);
	    }
	    
	    @POST
	    @Path("rateReviewupdate")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public Response rateReviewUpdate(int rrId, RateAndReview rateReview) {
	    	System.out.println("rateReviewUpdate method");
	    	RateAndReviewDao.updateRateReview(ConnectionDao.getConnection(), rrId, rateReview);
            return makeResponse("success", MediaType.TEXT_PLAIN);
	    }
	    
		
		@GET
		@Path("getAllFoodRecipes")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<FoodRecipe> getAllRecipes() {
			FoodRecipeDao fDao = FoodRecipeDao.getInstance();
			return fDao.findAllFoodRecipe(ConnectionDao.getConnection());
		}
	    
	    @POST
	    @Path("deleterateReview/{rid}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int deleteRateReview(@PathParam("rid")int rid) {
	    	System.out.println("In deleteRateReview method");
	    	return RateAndReviewDao.deleteRateReview(ConnectionDao.getConnection(), rid );
	    }
	    
	    @POST
	    @Path("deleteFavChef/{favCid}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int deleteFavChef(@PathParam("favCid")int favCid) {
	    	System.out.println("In deletefavchef method");
	    	return CustomerDao.deleteFavChef(ConnectionDao.getConnection(), favCid);
	    }
	    
	    @POST
	    @Path("deleteFavRestaurant/{favRid}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public int deleteFavRestaurant(@PathParam("favRid")int favRid) {
	    	System.out.println("In deletefavrest method");
	    	return CustomerDao.deleteFavRestaurant(ConnectionDao.getConnection(), favRid );
	    }
	    
	    @GET
	    @Path("listAllChefs")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Chef> findAllchef(){
	    	return ChefDao.findAllChef(ConnectionDao.getConnection());
	    }
	    
	    @GET
	    @Path("listAllChefRecipes/{chefId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<FoodRecipe> getAllChefRecipes(@PathParam("chefId") int chefId){
	    	return ChefDao.findAllRecipes(ConnectionDao.getConnection(), chefId);
	    }
	    
	    @GET
	    @Path("makeChefFav")
	    @Produces(MediaType.APPLICATION_JSON)
	    public int makeChefFav(@QueryParam("custId") int custId, @QueryParam("chefId") int chefId) {
	    	return CustomerDao.makeChefFav(ConnectionDao.getConnection(), custId,chefId);
	    }
	    
	    
	    
	    
	    public Response makeResponse(Object o, String type) {
	        return Response.ok(o, type).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	    }
}
