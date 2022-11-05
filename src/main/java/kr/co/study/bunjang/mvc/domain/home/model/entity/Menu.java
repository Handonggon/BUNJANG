package kr.co.study.bunjang.mvc.domain.home.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "MENU")
@DynamicUpdate
@EntityListeners(ModifListener.class)
@Entity public class Menu implements Serializable, ModifAccessible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("메뉴 키값")
    private Long menuNo;

    // @Column(nullable = true)
    // @Comment("상위 메뉴 키값")
    // private Long parentMenuNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "parentMenuNo", referencedColumnName = "menuNo", nullable = true, columnDefinition = "bigint comment '상위 메뉴 키값'")}
               , foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Menu parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Menu> child;

    @Column(length = 100, nullable = true)
    @Comment("메뉴 Path")
    private String menuPath;

    @Column(length = 100, nullable = true)
    @Comment("메뉴 Icon")
    private String menuIcon;

    @Column(length = 50, nullable = true)
    @Comment("메뉴 이름")
    private String menuNm;

    @Column(length = 100, nullable = true)
    @Comment("메뉴 설명")
    private String menuDesc;

    @Column(length = 100, nullable = false)
    @Comment("메뉴 Level")
    private int menuLevel;

    @Column(length = 100, nullable = false)
    @Comment("메뉴 순서")
    private int menuOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("메뉴 사용 여부")
    private Yn useYn;

    @Embedded
    private Modif modif;
}