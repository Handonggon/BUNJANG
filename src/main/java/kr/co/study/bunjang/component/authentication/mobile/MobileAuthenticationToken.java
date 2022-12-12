package kr.co.study.bunjang.component.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class MobileAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    public MobileAuthenticationToken(Long shopNo, String credentials) {
		super(null);
		this.setDetails(shopNo);
        this.credentials = credentials;
	}

	public MobileAuthenticationToken(UserDetails userDetails, String credentials) {
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
