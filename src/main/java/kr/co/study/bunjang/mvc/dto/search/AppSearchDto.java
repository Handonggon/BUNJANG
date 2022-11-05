package kr.co.study.bunjang.mvc.dto.search;

import kr.co.study.bunjang.mvc.dto.AbstractDto;
import kr.co.study.bunjang.mvc.vo.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AppSearchDto extends AbstractDto {

	private Long appNo;

	private Long cmpNo;

	private String tenantId;

	private String clientId;

	private String sysName;

	private String entityId;

	private String sysUrl;

	private String loginUrl;

	private String logoutUrl;

	private Yn ssoYn;
}