package kr.co.study.bunjang.mvc.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModelProperty;
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
public class ShopDetailsDto extends AbstractDto implements UserDetails {

	@ApiModelProperty(value = "상점 번호", notes = "순번 데이터", example = "1")
	private Long shopNo;

	@ApiModelProperty(value = "상점 이름", example = "상점0호", required = true)
	private String shopNm;

	private String userNm;

	private String identiNumber;

	private String phoneNumber;

	private String telecom;

	private Yn authenticationYn;

	private Yn termsYn;

	private Yn collectionPrivacyPolicyYn;

	private Yn phoneIdentificationYn;

	private Yn privacyArchivingYn;

	private Yn locationInfoYn;

	@ApiModelProperty(hidden=true)
	private Yn privacyYn;

	@ApiModelProperty(hidden=true)
	private Yn eventYn;

	@ApiModelProperty(hidden=true)
	private Yn adUtilizationYn;
	
	@ApiModelProperty(hidden=true)
	public final Collection<Role> authorities = new ArrayList<Role>();

	public ShopDetailsDto(Shop shop) {
		this.shopNo = shop.getShopNo();
		this.shopNm = shop.getShopNm();
		this.userNm = shop.getUserNm();
		this.identiNumber = shop.getIdentiNumber();
		this.phoneNumber = shop.getPhoneNumber();
		this.telecom = shop.getTelecom();
		this.authenticationYn = shop.getAdUtilizationYn();
		this.termsYn = shop.getTermsYn();
		this.collectionPrivacyPolicyYn = shop.getCollectionPrivacyPolicyYn();
		this.phoneIdentificationYn = shop.getPhoneIdentificationYn();
		this.privacyArchivingYn = shop.getPrivacyArchivingYn();
		this.locationInfoYn = shop.getLocationInfoYn();
		this.privacyYn = shop.getPrivacyYn();
		this.eventYn = shop.getEventYn();
		this.adUtilizationYn = shop.getAdUtilizationYn();
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
		return this.shopNm + " " + this.userNm;
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
	
	public Shop toEntity() {
		return Shop.builder()
					.shopNo(this.shopNo)
					.userNm(this.userNm)
					.identiNumber(this.identiNumber)
					.phoneNumber(this.phoneNumber)
					.telecom(this.telecom)
					.termsYn(this.termsYn)
					.collectionPrivacyPolicyYn(this.collectionPrivacyPolicyYn)
					.phoneIdentificationYn(this.phoneIdentificationYn)
					.privacyArchivingYn(this.privacyArchivingYn)
					.locationInfoYn(this.locationInfoYn)
					.privacyYn(this.privacyYn)
					.eventYn(this.eventYn)
					.adUtilizationYn(this.adUtilizationYn)
					.build();
	}
}