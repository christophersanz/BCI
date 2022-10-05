package cl.bci.useful.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import cl.bci.useful.JWTService;

@Component
public class JWTServiceImpl implements JWTService {

	public static final String SECRET = Base64Utils.encodeToString("Alguna.Clave.Secreta.123456".getBytes());
	
	public static final String TOKEN_PREFIX = "Bearer ";

	@Override
	public boolean validate(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(resolve(token))
				.getBody();
		return claims;
	}

	@Override
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			return token.replace(TOKEN_PREFIX, "");
		}
		return null;
	}

}
