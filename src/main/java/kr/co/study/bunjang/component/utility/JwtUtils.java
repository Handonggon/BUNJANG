package kr.co.study.bunjang.component.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JwtUtils {

    public final String SECRET_KEY = "BUNJANG";

	public PrivateKey getPrivateKey() throws IOException {
		ClassPathResource resource = new ClassPathResource("private_key.der");
		byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));

		try {
			KeyFactory kf = KeyFactory.getInstance(SignatureAlgorithm.RS512.getFamilyName());
			return kf.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
		} catch (NoSuchAlgorithmException e) {
			log.debug("JwtUtils - getPrivateKey: " + e.getMessage());
			throw new IOException(e);
		} catch (InvalidKeySpecException e) {
			log.debug("JwtUtils - getPrivateKey: " + e.getMessage());
			throw new IOException(e);
		}
	}

	public PublicKey getPublicKey() throws IOException {
		ClassPathResource resource = new ClassPathResource("public_key.der");
		byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));
		try {
			KeyFactory kf = KeyFactory.getInstance(SignatureAlgorithm.RS512.getFamilyName());
			return kf.generatePublic(new X509EncodedKeySpec(keyBytes));
		} catch (NoSuchAlgorithmException e) {
			log.debug("JwtUtils - getPublicKey: " + e.getMessage());
			throw new IOException(e);
		} catch (InvalidKeySpecException e) {
			log.debug("JwtUtils - getPublicKey: " + e.getMessage());
			throw new IOException(e);
		}
	}

	public String createToken(Map<String, Object> params, String key, Long tokenValidMillisecond)
			throws IOException {
		Claims claims = Jwts.claims();
		claims.putAll(params);

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + tokenValidMillisecond))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(key.getBytes()))
				.compact();
	}

	public String createToken(Map<String, Object> params, SignatureAlgorithm algorithm, PrivateKey key,
			Long tokenValidMillisecond) throws IOException {
		Claims claims = Jwts.claims();
		claims.putAll(params);

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + tokenValidMillisecond))
				.signWith(algorithm, key)
				.compact();
	}

	public boolean validationToken(String token, String key) {
		try {
			Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(key.getBytes())).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.debug("JwtUtils - getPublicKey: " + e.getMessage());
		} catch (Exception e) {
			log.debug("JwtUtils - getPublicKey: " + e.getMessage());
		}
		return false;
	}

	public boolean validationToken(String token, PublicKey key) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.debug("JwtUtils - getPublicKey: " + e.getMessage());
		} catch (Exception e) {
			log.debug("JwtUtils - getPublicKey: " + e.getMessage());
		}
		return false;
	}

	public Map<String, Object> parseToken(String token, String key) throws IOException {
		try {
			return new HashMap<String, Object>(Jwts.parser()
					.setSigningKey(Base64.getEncoder().encodeToString(key.getBytes())).parseClaimsJws(token).getBody());
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException e) {
			log.debug("JwtUtils - parseToken: " + e.getMessage());
			throw new IOException(e);
		}
	}

	public Map<String, Object> parseToken(String token, PublicKey key) throws IOException {
		try {
			return new HashMap<String, Object>(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody());
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException e) {
			log.debug("JwtUtils - parseToken: " + e.getMessage());
			throw new IOException(e);
		}
	}
}