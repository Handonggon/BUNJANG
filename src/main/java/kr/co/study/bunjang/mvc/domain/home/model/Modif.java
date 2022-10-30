package kr.co.study.bunjang.mvc.domain.home.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Modif implements Serializable {

        @CreatedDate
        @Column(name = "CDT", updatable = false, nullable = false)
        @Convert(converter = LocalDateTimeConverter.class)
        @Comment("생성일")
        @ColumnDefault("now()")
        private LocalDateTime CDT;

        @LastModifiedDate
        @Column(name = "MDT", nullable = false)
        @Convert(converter = LocalDateTimeConverter.class)
        @Comment("수정일")
        private LocalDateTime MDT;

        @CreatedBy
        @Embedded
        @AttributeOverrides({
                        @AttributeOverride(name = "id", column = @Column(name = "CID")),
                        @AttributeOverride(name = "nm", column = @Column(name = "CNM"))
        })
        private Modifier constructor;

        @LastModifiedBy
        @Embedded
        @AttributeOverrides({
                        @AttributeOverride(name = "id", column = @Column(name = "MID")),
                        @AttributeOverride(name = "nm", column = @Column(name = "MNM"))
        })
        private Modifier modifier;
}