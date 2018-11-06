package pl.rabowski.jaxrs.controller;

import pl.rabowski.jaxrs.dao.FriendDao;
import pl.rabowski.jaxrs.model.Address;
import pl.rabowski.jaxrs.model.Friend;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/friend")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class FriendController {

    @Inject
    private FriendDao friendDao;

    @GET
    public Response allFriends(){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        List<Friend> friendList = friendDao.getFriends();
        for(Friend friend: friendList){
            for(Address address : friend.getAddressList()){
                arrayBuilder.add(address.toString());
            }
            objectBuilder
                    .add("id" , friend.getId())
                    .add("firstName" , friend.getFirstName())
                    .add("lastName", friend.getLastName())
                    .add("age" , friend.getAge())
                    .add("addressList", arrayBuilder);
        }
        return Response.ok(objectBuilder).entity("Get all friends.").build();
    }

    @GET
    @Path("/{id}")
    public Response getFriend(@PathParam("id") long id){
        Friend friend = friendDao.getFriend(id);
        if(friend != null){
            return Response.ok(friend).entity("Get friend with id " + id).build();
        } else return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
