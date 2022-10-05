package cl.bci.useful;

import io.jsonwebtoken.Claims;

public interface JWTService {

	boolean validate(String token);
	Claims getClaims(String token);
	String getUsername(String token);
	String resolve(String token);

}
