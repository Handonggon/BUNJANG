package kr.co.study.bunjang.mvc.domain.home.model.embedded;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import kr.co.study.bunjang.component.utility.ObjUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable public class Modif implements Serializable {

        @CreatedDate
        @Column(name = "CDT", updatable = false, nullable = true)
        @Convert(converter = LocalDateTimeConverter.class)
        @Comment("생성일")
        private LocalDateTime CDT;

        @LastModifiedDate
        @Column(name = "MDT",nullable = true)
        @Convert(converter = LocalDateTimeConverter.class)
        @Comment("수정일")
        private LocalDateTime MDT;

        @CreatedBy
        @Embedded
        @AttributeOverrides({
                        @AttributeOverride(name = "id", column = @Column(name = "CID", columnDefinition = "bigint comment '생성자'")),
                        @AttributeOverride(name = "nm", column = @Column(name = "CNM", columnDefinition = "varchar(50) comment '생성자 이름'"))
        })
        private Modifier constructor;

        @LastModifiedBy
        @Embedded
        @AttributeOverrides({
                        @AttributeOverride(name = "id", column = @Column(name = "MID", columnDefinition = "bigint comment '수정자'")),
                        @AttributeOverride(name = "nm", column = @Column(name = "MNM", columnDefinition = "varchar(50) comment '수정자 이름'"))
        })
        private Modifier modifier;

        public void changeCreated() {
                Modifier modifier = this.getConstructor();
                if (ObjUtils.isEmpty(modifier)) {
                        modifier = new Modifier();
                        this.constructor = modifier;
                }
                constructor.change();
                this.CDT = LocalDateTime.now();
        }

        public void changeModified() {
                Modifier modifier = this.getModifier();
                if (ObjUtils.isEmpty(modifier)) {
                        modifier = new Modifier();
                        this.modifier = modifier;
                }
                modifier.change();
                this.MDT = LocalDateTime.now();
        }
}