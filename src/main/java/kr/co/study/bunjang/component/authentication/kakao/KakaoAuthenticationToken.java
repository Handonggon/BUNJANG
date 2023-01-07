package kr.co.study.bunjang.component.authentication.kakao;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class KakaoAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    public KakaoAuthenticationToken(Long shopNo, String credentials) {
		super(null);
		this.setDetails(shopNo);
        this.credentials = credentials;
	}

	public KakaoAuthenticationToken(UserDetails userDetails, String credentials) {
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
