package hu.mt.security;


import hu.mt.model.Eigenaar;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestCtx) {

        boolean isSecure = requestCtx.getSecurityContext().isSecure();
        String scheme = requestCtx.getUriInfo().getRequestUri().getScheme();
        // Users are treated as guests, unless a valid JWT is provided
        MySecurityContext msc = new MySecurityContext(null, scheme);
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring("Bearer".length()).trim();

            try {
                // Validate the token
                JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key);
                Claims claims = parser.parseClaimsJws(token).getBody();

                String user = claims.getSubject();
                System.out.println(user);
                String vn = user.substring(0,user.indexOf(" "));
                System.out.println(vn);
                String an = user.substring(user.indexOf(" ") + 1, user.length() - 1 + 1);
                System.out.println(an);

                msc = new MySecurityContext(Eigenaar.getEigenaar(vn, an), scheme);
//                msc = new MySecurityContext(Eigenaar.getEigenaar("rik", "hik"), scheme);
                //voornaam achternaam check user ^^
            } catch (JwtException | IllegalArgumentException e) {
//                System.out.println("Invalid JWT, processing as guest!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        requestCtx.setSecurityContext(msc);
    }
}















