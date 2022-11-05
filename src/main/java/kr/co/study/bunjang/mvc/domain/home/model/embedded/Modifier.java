package kr.co.study.bunjang.mvc.domain.home.model.embedded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.co.study.bunjang.mvc.dto.ShopDetailsDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable public class Modifier implements Serializable {

    @Column
    private Long id;

    @Column(length = 30)
    private String nm;

    public void change() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof ShopDetailsDto) {
            ShopDetailsDto shopDetailsDto = (ShopDetailsDto) authentication.getPrincipal();
            this.id = shopDetailsDto.getShopNo();
            this.nm = shopDetailsDto.getShopNm();
        } else {
            this.id = 0L;
            this.nm = "anonymous";   
        }
	}
}