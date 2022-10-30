package kr.co.study.bunjang.mvc.domain.home.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Comment;

import kr.co.study.bunjang.mvc.enums.Yn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "APP")
public class App extends Modif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("어플리케이션 키값")
    private Long appNo;

    @Column(nullable = false)
    @Comment("법인 키값")
    private Long cmpNo;

    @Column(length = 100, nullable = true)
    @Comment("Azure tenant id")
    private String tenantId;

    @Column(length = 100, nullable = true)
    @Comment("Azrue client id")
    private String clientId;

    @Column(length = 100, nullable = true)
    @Comment("시스템명")
    private String sysNm;

    @Column(length = 100, nullable = true)
    @Comment("Azure entity id")
    private String entityId;

    @Column(length = 200, nullable = true)
    @Comment("시스템 URL")
    private String sysUrl;

    @Column(length = 200, nullable = true)
    @Comment("시스템 로그인 URL")
    private String loginUrl;

    @Column(length = 200, nullable = true)
    @Comment("시스템 로그아웃 URL")
    private String logoutUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, columnDefinition = "CHAR(1) default 'N'")
    @Comment("SSO 사용 여부")
    private Yn ssoYn;
}