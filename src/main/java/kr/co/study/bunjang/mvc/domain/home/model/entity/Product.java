package kr.co.study.bunjang.mvc.domain.home.model.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
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
@Table(name = "PRODUCT")
@DynamicUpdate
@EntityListeners(ModifListener.class)
public class Product implements Serializable, ModifAccessible {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상품 키값")
    private Long productId;

    @Embedded
    private Modif modif;
}
