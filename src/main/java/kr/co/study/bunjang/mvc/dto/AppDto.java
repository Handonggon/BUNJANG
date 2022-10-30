package kr.co.study.bunjang.mvc.dto;

import kr.co.study.bunjang.mvc.domain.home.model.App;
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
public class AppDto extends AbstractDto {

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

	public AppDto(App app) {
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
	}

	public App toEntity() {
		return App.builder().build();
	}
}
