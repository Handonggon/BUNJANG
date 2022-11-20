package kr.co.study.bunjang.mvc.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.co.study.bunjang.mvc.domain.home.model.entity.Shop;
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
public class ShopDto extends AbstractDto {

	@ApiModelProperty(position = 1, value = "상점 번호", notes = "순번 데이터", example = "1")
	private Long shopNo;

	@ApiModelProperty(position = 2, value = "상점 이름", example = "상점0호")
	private String shopNm;

	@ApiModelProperty(position = 3, value = "사용자 이름", example = "홍길동", required = true)
	private String userNm;

	@ApiModelProperty(position = 4, value = "주민등록번호", example = "123456-1*****", required = true)
	private String identiNumber;

	@ApiModelProperty(position = 5, value = "휴대폰 번호", example = "010-1234-5678", required = true)
	private String phoneNumber;

	@ApiModelProperty(position = 6, value = "통신사", required = true)
	private String telecom;

	@ApiModelProperty(position = 7, value = "본인 인증 여부", required = true)
	private Yn authenticationYn;

	@ApiModelProperty(position = 8, value = "번개장터 이용약관 (필수)", required = true)
	private Yn termsYn;

	@ApiModelProperty(position = 9, value = "개인정보 수집 이용 동의 (필수)", required = true)
	private Yn collectionPrivacyPolicyYn;

	@ApiModelProperty(position = 10, value = "휴대폰 본인확인서비스 (필수)", required = true)
	private Yn phoneIdentificationYn;

	@ApiModelProperty(position = 11, value = "휴면 개인정보 분리보관 동의 (필수)", required = true)
	private Yn privacyArchivingYn;

	@ApiModelProperty(position = 12, value = "위치정보 이용약관 동의 (필수)", required = true)
	private Yn locationInfoYn;

	@ApiModelProperty(position = 13, value = "개인정보 수집 이용 동의 (선택)")
	private Yn privacyYn;

	@ApiModelProperty(position = 14, value = "마케팅 수신 동의 (선택)")
	private Yn eventYn;

	@ApiModelProperty(position = 15, value = "개인정보 광고활용 동의 (선택)")
	private Yn adUtilizationYn;

	public ShopDto(Shop shop) {
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