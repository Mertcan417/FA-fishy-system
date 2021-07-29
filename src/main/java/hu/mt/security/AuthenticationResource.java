package hu.mt.security;

import hu.mt.model.Eigenaar;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;

//maak in de klasse: Eigenaar een rolnaam
//maak ook in klasse Eigenaar een methode validateEigenaar,
// kloppen deze ingevulde gegevens wel met de static AquariumManager eigenaar lijst

@Path("login")
public class AuthenticationResource {
    public static final Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("voornaam") String vn, @FormParam("achternaam") String an, @FormParam("wachtwoord") String pass) {
        try {
            String role = Eigenaar.roleValidationEigenaar(vn, an, pass);
            System.out.println(role);
            if (role == null) {
                throw new IllegalArgumentException("No user found");
            }
            String token = createToken(vn+" "+an, role);
            System.out.println(token);
            return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String createToken(String nm, String role) throws JwtException {
        //aanmaken van token
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(nm)
                .setExpiration(expiration.getTime())
                .claim(nm, role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}

