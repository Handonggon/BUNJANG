package kr.co.study.bunjang.mvc.domain.home.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import kr.co.study.bunjang.mvc.domain.home.model.embedded.Modif;
import kr.co.study.bunjang.mvc.domain.home.model.listeners.ModifAccessible;
import kr.co.study.bunjang.mvc.domain.home.model.listeners.ModifListener;
import kr.co.study.bunjang.mvc.vo.Yn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SHOP")
@DynamicUpdate
@EntityListeners(ModifListener.class)
@Entity public class Shop implements Serializable, ModifAccessible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상점 키값")
    private Long shopNo;

    @Column(length = 100, nullable = true)
    @Comment("상점 이름")
    private String shopNm;




    @Column(length = 20, nullable = false, unique = true)
    @Comment("사용자 이름")
    private String userNm;

    @Column(length = 20, nullable = false, unique = true)
    @Comment("주민등록번호")
    private String identiNumber;

    @Column(length = 20, nullable = false, unique = true)
    @Comment("휴대폰 번호")
    private String phoneNumber;

    @Column(length = 10, nullable = false, unique = true)
    @Comment("통신사")
    private String telecom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("본인 인증 여부")
    private Yn authenticationYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("번개장터 이용약관 (필수)")
    private Yn termsYn;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("개인정보 수집 이용 동의 (필수)")
    private Yn collectionPrivacyPolicyYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("휴대폰 본인확인서비스 (필수)")
    private Yn phoneIdentificationYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("휴면 개인정보 분리보관 동의 (필수)")
    private Yn privacyArchivingYn;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("위치정보 이용약관 동의 (필수)")
    private Yn locationInfoYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("개인정보 수집 이용 동의 (선택)")
    private Yn privacyYn;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("마케팅 수신 동의 (선택)")
    private Yn eventYn;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("개인정보 광고활용 동의 (선택)")
    private Yn adUtilizationYn;
    
    @Embedded
    private Modif modif;
}