package kr.co.study.bunjang.mvc.domain.home.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "COMPANY")
public class Company extends Modif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("법인 키값")
    private Long comNo;

    @Column(length = 20, nullable = false)
    @Comment("법인 코드")
    private String cmpCd;

    @Column(length = 30, nullable = false)
    @Comment("한글회사명")
    private String cmpNm;

    @Column(length = 30, nullable = true)
    @Comment("영문회사명")
    private String cmpEnNm;

    @Column(length = 30, nullable = true)
    @Comment("중문회사명")
    private String cmpZhNm;

    @Column(length = 30, nullable = true)
    @Comment("일문회사명")
    private String cmpJaNm;

    @Column(length = 30, nullable = true)
    @Comment("상위법인 키값")
    private int oriCmpNo;

    @Column(length = 30, nullable = true)
    @Comment("인사시스템의 법인 코드")
    private String ehrCmpCd;
}