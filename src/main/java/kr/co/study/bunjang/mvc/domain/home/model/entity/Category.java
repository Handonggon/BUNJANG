package kr.co.study.bunjang.mvc.domain.home.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "CATEGORY")
@DynamicUpdate
@EntityListeners(ModifListener.class)
@Entity public class Category implements Serializable, ModifAccessible {
    
    @Id
    @Column(length = 20)
    @Comment("카테고리 코드")
    private String cateCd;

    @Column(length = 100, nullable = true)
    @Comment("카테고리명")
    private String cateNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "parentCateCd", referencedColumnName = "cateCd", nullable = true, columnDefinition = "varchar(20) comment '상위 카테고리코드'")}
               , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> children;

    @Column(nullable = false)
    @Comment("Level")
    private int cateLevel;

    @Column(nullable = false)
    @Comment("순서")
    private int cateOrder;

    @Embedded
    private Modif modif;
}