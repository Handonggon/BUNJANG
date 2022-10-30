package kr.co.study.bunjang.mvc.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.study.bunjang.component.utils.HttpUtils;
import kr.co.study.bunjang.mvc.domain.home.model.App;
import kr.co.study.bunjang.mvc.enums.Role;
import kr.co.study.bunjang.mvc.enums.Yn;
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
public class AppDetailsDto extends AbstractDto implements UserDetails {

	private Long appNo;

	private Long cmpNo;

	private String tenantId;

	private String clientId;

	private String sysNm;

	private String entityId;

	private String sysUrl;

	private String loginUrl;

	private String logoutUrl;

	private Yn ssoYn;

	private final Collection<Role> authorities = new ArrayList<Role>();

	private String ip;

	public AppDetailsDto(App app) {
		this.appNo = app.getAppNo();
		this.cmpNo = app.getCmpNo();
		this.tenantId = app.getTenantId();
		this.clientId = app.getClientId();
		this.sysNm = app.getSysNm();
		this.entityId = app.getEntityId();
		this.sysUrl = app.getSysUrl();
		this.loginUrl = app.getLoginUrl();
		this.logoutUrl = app.getLogoutUrl();
		this.ssoYn = app.getSsoYn();
		this.authorities.add(Role.APP);
		this.ip = HttpUtils.getUserIp();
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
		return null;
	}

	@Override
	public String getUsername() {
		return this.sysNm;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ssoYn.equals(Yn.Y);
	}
}
