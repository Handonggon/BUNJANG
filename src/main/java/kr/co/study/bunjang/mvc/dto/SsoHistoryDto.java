package kr.co.study.bunjang.mvc.dto;

import java.time.LocalDateTime;

import kr.co.study.bunjang.mvc.domain.home.model.SsoHistory;
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
public class SsoHistoryDto extends AbstractDto {

    private Long historyNo;

    private String name;

	private String origin;

	private String destin;

    private Yn successYn;

    private LocalDateTime datetime;

    private String message;

	public SsoHistoryDto(SsoHistory ssoHistory) {
		this.historyNo = ssoHistory.getHistoryNo();
		this.name = ssoHistory.getName();
		this.origin = ssoHistory.getOrigin();
		this.destin = ssoHistory.getDestin();
		this.successYn = ssoHistory.getSuccessYn();
		this.datetime = ssoHistory.getDatetime();
		this.message = ssoHistory.getMessage();
	}

	public SsoHistory toEntity() {
		return SsoHistory.builder()
		.historyNo(this.historyNo)
		.name(this.name)
		.origin(this.origin)
		.destin(this.destin)
		.successYn(this.successYn)
		.datetime(this.datetime)
		.message(this.message)
		.build();
	}
}