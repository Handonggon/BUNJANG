package kr.co.study.bunjang.component.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class ShopAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    public ShopAuthenticationToken(String OAuth) {
		super(null);
		this.setDetails(OAuth);
        this.credentials = null;
	}

	public ShopAuthenticationToken(UserDetails userDetails, String OAuth) {
		super(userDetails.getAuthorities());
		this.setAuthenticated(true);
		this.setDetails(userDetails);
        this.credentials = OAuth;
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
