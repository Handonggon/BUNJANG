package kr.co.study.bunjang.mvc.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Shop;
import kr.co.study.bunjang.mvc.vo.Role;
import kr.co.study.bunjang.mvc.vo.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ShopDetails extends AbstractDto implements UserDetails {

	private Long shopNo;

	private String shopNm;

	private Yn authenticationYn;

	private final String credentials = "111111";
	
	public final Collection<Role> authorities = new ArrayList<Role>();

	public ShopDetails(Shop shop) {
		this.shopNo = shop.getShopNo();
		this.shopNm = shop.getShopNm();
		this.authenticationYn = shop.getAuthenticationYn();
		this.authorities.add(Role.USER);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public Boolean hasRole(Role role) {
		return this.authorities.stream().filter(o -> o.equals(role)).findAny().isPresent();
	}

	@Override
	public String getPassword() {
		return this.credentials;
	}

	@Override
	public String getUsername() {
		return this.shopNm;
	}

	@Override
	public boolean isAccountNonExpired() {
		return authenticationYn.equals(Yn.Y);
	}

	@Override
	public boolean isAccountNonLocked() {
		return authenticationYn.equals(Yn.Y);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return authenticationYn.equals(Yn.Y);
	}

	@Override
	public boolean isEnabled() {
		return authenticationYn.equals(Yn.Y);
	}
}