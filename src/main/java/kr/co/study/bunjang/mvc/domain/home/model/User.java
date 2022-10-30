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
@Entity(name = "USER")
public class User extends Modif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자 마스터 키값")
    private Long userNo;

    @Column(length = 20, nullable = false, unique = true)
    @Comment("사용자 아이디")
    private String userId;

    @Column(length = 100, nullable = true)
    @Comment("사용자 암호")
    private String userPw;

    @Column(length = 30, nullable = true)
    @Comment("한글성명")
    private String userNm;

    @Column(length = 30, nullable = true)
    @Comment("영문성명")
    private String userEnNm;

    @Column(length = 30, nullable = true)
    @Comment("중문성명")
    private String userZhNm;

    @Column(length = 30, nullable = true)
    @Comment("일문성명")
    private String userJaNm;
}