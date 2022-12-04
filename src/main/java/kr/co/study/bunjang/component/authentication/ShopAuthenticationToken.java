package kr.co.study.bunjang.component.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class ShopAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    public ShopAuthenticationToken(Long shopNo, String credentials) {
		super(null);
		this.setDetails(shopNo);
        this.credentials = credentials;
	}

	public ShopAuthenticationToken(UserDetails userDetails, String credentials) {
		super(userDetails.getAuthorities());
		this.setAuthenticated(true);
		this.setDetails(userDetails);
        this.credentials = credentials;
	}

    @Override
    public Object getPrincipal() {
		return this.getDetails();
	}

    @Override
    public String getCredentials() {
        return this.credentials;
    }
}
