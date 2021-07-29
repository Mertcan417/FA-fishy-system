package hu.mt.webservices;

import hu.mt.model.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;

@Path("eigenaar")
public class EigenaarResource {

    @GET
    @RolesAllowed("beheerder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEigenaren() {
        try {
            return Response.ok(AquariumManager.getEigenaren()).build();
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @GET
    @Path("{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEigenaar(@PathParam("voornaam") String vn, @PathParam("achternaam") String an) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                return Response.ok(eigenaar).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addEigenaar(@FormParam("voornaam") String vn, @FormParam("achternaam") String an) {
        try {
            Eigenaar eigenaar = new Eigenaar(vn, an);
            AquariumManager.voegEigenaarToe(eigenaar);
            return Response.ok(eigenaar).build();
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @DELETE
    @Path("verwijderen/{voornaam}/{achternaam}")
    @RolesAllowed("beheerder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response removeEigenaar(@PathParam("voornaam") String vn, @PathParam("achternaam") String an) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            AquariumManager.getEigenaren().remove(eigenaar);
            return Response.ok("Eigenaar succesvol verwijderd!").build();
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @GET
    @Path("aquarium/{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAquarium(@PathParam("voornaam") String vn, @PathParam("achternaam") String an) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                return Response.ok(eigenaar.getAquariua()).build();
            }
            return null;
        } catch (Exception e) {
            return Response.status(404).entity("Geen aquaria bestaat niet!").build();
        }
    }

    @PUT
    @Path("aquariumtoevoegen/{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addAquarium(@PathParam("voornaam") String vn, @PathParam("achternaam") String an,
                                @FormParam("naam") String nm, @FormParam("bodemSoort") String bs, @FormParam("waterSoort") String ws, @FormParam("lengte") int lt,
                                @FormParam("breedte") int bt, @FormParam("hoogte") int ht) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            Aquarium aq = new Aquarium(nm, lt, bt, ht, bs, ws);
            Eigenaar.validateEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                eigenaar.voegAquariumToe(aq);
                return Response.ok(eigenaar).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @DELETE
    @Path("aquariumverwijderen/{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response removeAquarium(@PathParam("voornaam") String vn, @PathParam("achternaam") String an,
                                   @FormParam("naam") String nm) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                Aquarium aq = eigenaar.getAquariumByName(nm);
                eigenaar.verwijderAquarium(aq);
                return Response.ok(eigenaar).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @GET
    @Path("bewoners/{voornaam}/{achternaam}/{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBewoners(@PathParam("voornaam") String vn, @PathParam("achternaam") String an,
                                @PathParam("naam") String nm) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                Aquarium aq = eigenaar.getAquariumByName(nm);
                return Response.ok(aq.getBewoners()).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @PUT
    @Path("bewonertoevoegen/{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addBewoner(@PathParam("voornaam") String vn, @PathParam("achternaam") String an,
                               @FormParam("naam") String nm, @FormParam("soortnaam") String sn, @FormParam("kleurnaam") String kn,
                               @FormParam("groepsdier") boolean gd, @FormParam("type") String tp,
                               @FormParam("aantal") int at) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                Bewoner bn = new Bewoner(sn, kn, at, gd, tp);
                Aquarium aquarium = eigenaar.getAquariumByName(nm);
                aquarium.voegBewonerToe(bn);
                return Response.ok(eigenaar).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @GET
    @Path("toebehoren/{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToebehoren(@PathParam("voornaam") String vn, @PathParam("achternaam") String an) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                return Response.ok(eigenaar.getToebehoren()).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @PUT
    @Path("toebehorentoevoegen/{voornaam}/{achternaam}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addToebehoren(@PathParam("voornaam") String vn, @PathParam("achternaam") String an,
                                  @FormParam("model") String mdl, @FormParam("serienummer") int snmr,
                                  @FormParam("omschrijving") String os) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                Toebehoren tbh = new Toebehoren(mdl, snmr, os);
                eigenaar.voegToebehorenToe(tbh);
                return Response.ok(eigenaar).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }

    @GET
    @Path("ornamenten/{voornaam}/{achternaam}/{naam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrnamenten(@PathParam("voornaam") String vn, @PathParam("achternaam") String an, @PathParam("naam") String nm) {
        try {
            Eigenaar eigenaar = Eigenaar.getEigenaar(vn, an);
            if (Eigenaar.validateEigenaar(vn, an) == true) {
                Aquarium aq = eigenaar.getAquariumByName(nm);
                return Response.ok(aq.getOrnamenten()).build();
            }
            return null;
        } catch (Exception e) {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", e.getMessage().toString());
            return Response.status(404).entity(messages).build();
        }
    }
}
