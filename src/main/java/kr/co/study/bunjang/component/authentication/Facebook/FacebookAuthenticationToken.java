package kr.co.study.bunjang.component.authentication.Facebook;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class FacebookAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    public FacebookAuthenticationToken(String credentials) {
		super(null);
        this.credentials = credentials;
	}

	public FacebookAuthenticationToken(UserDetails userDetails, String credentials) {
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
