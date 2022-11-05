package kr.co.study.bunjang.mvc.domain.home.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import kr.co.study.bunjang.mvc.domain.home.model.embedded.Modif;
import kr.co.study.bunjang.mvc.domain.home.model.listeners.ModifAccessible;
import kr.co.study.bunjang.mvc.domain.home.model.listeners.ModifListener;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Shop")
@DynamicUpdate
@EntityListeners(ModifListener.class)
@Entity public class Shop implements Serializable, ModifAccessible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상점 키값")
    private Long shopNo;

    @Column(length = 20, nullable = false, unique = true)
    @Comment("상점 아이디")
    private String shopId;

    @Column(length = 100, nullable = true)
    @Comment("상점 암호")
    private String shopPw;

    @Column(length = 100, nullable = true)
    @Comment("상점 이름")
    private String shopNm;

    @Embedded
    private Modif modif;
}