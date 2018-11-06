package pl.rabowski.jaxrs.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reservation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationsController {

    @GET
    public Response reservations(){
        return Response.ok("Wszystkie rezerwacje: ").build();
    }

    @GET
    @Path("/{id}")
    public Response getReservation(@PathParam("id") Integer id){
        return Response.ok("Oto rezerwacja o numerze " + id + ". Enjoy.").build();
    }

    @PUT
    @Path("/{id}")
    public Response modifyReservation(@PathParam("id") Integer id){
        return Response.ok("Zmodyfikowałeś rezerwację numer " + id + ".").build();
    }


}
