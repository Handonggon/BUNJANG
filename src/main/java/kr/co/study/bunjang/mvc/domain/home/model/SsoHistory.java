package kr.co.study.bunjang.mvc.domain.home.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.co.study.bunjang.mvc.enums.Yn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "SSO_HISTORY")
public class SsoHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("히스토리 키값")
    private Long historyNo;

    @Column(length = 100, nullable = false)
    @Comment("출발지")
    private String origin;

    @Column(length = 100, nullable = false)
    @Comment("도착지")
    private String destin;

    @Column(length = 100, nullable = false)
    @Comment("이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1) default 'N'")
    @Comment("성공 여부")
    private Yn successYn;

    @CreatedDate
    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    @Comment("히스토리 시간")
    @ColumnDefault("now()")
    private LocalDateTime datetime;
    
    @Column(length = 100, nullable = true)
    @Comment("성공/실패 메시지")
    private String message;
}