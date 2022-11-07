package kr.co.study.bunjang.mvc.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Shop;
import kr.co.study.bunjang.mvc.vo.Role;
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
public class ShopDetailsDto extends AbstractDto implements UserDetails {
	//TODO shopDetailsDto Refactoring

	private Long shopNo;

	private String empNo;

	private String shopNm;


	private String cmpEmail;

	private String cellPhone;

	private String cmpCd;

	private String cmpNm;

	private String bsCd;

	private String bsNm;

	private String deptCd;

	private String deptNm;

	private String ofcCd;

	private String ofcNm;

	private String posCd;

	private String posNm;

	private String rankCd;

	private String rankNm;

	private String roleCd;

	public final List<String> accDeptCd = new ArrayList<String>();

	public final Collection<Role> authorities = new ArrayList<Role>();

	public ShopDetailsDto(Shop shop) {
		this.shopNo = shop.getShopNo();
		this.shopNm = shop.getShopNm();
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
		return StringUtils.EMPTY;
	}

	@Override
	public String getUsername() {
		return this.shopNm + " " + this.posNm;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}