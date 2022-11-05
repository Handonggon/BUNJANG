package kr.co.study.bunjang.mvc.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {
	USER(ROLES.USER, "유저");

	private String authority;

	private String desc;

	private static class ROLES {
		public static final String USER = "ROLE_USER";
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return authority.replace("ROLE_", "");
	}

	public static Role getEnum(String authority) {
		for (Role userRole : Role.values()) {
			if (userRole.toString().equals(authority)) {
				return userRole;
			}
		}
		return null;
	}
}