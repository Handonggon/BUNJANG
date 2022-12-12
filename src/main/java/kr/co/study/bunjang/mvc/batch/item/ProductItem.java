package kr.co.study.bunjang.mvc.batch.item;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import kr.co.study.bunjang.mvc.domain.home.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    
    @ApiModelProperty(position = 1, value = "상품 ID", example = "208182068", required = true)
    private Long pid;

    @ApiModelProperty(position = 2, value = "상품 이름", example = "무료배송 곰돌이 장갑 벙어리장갑 새상품", required = true)
    private String productName;

    @ApiModelProperty(position = 3, value = "상품 이미지", example = "https://media.bunjang.co.kr/product/208182068_1_1670839897_w{res}.jpg")
    private String productImage;

    @ApiModelProperty(position = 4, value = "상품 가격", example = "12000", required = true)
    private Long price;

    @ApiModelProperty(position = 5, value = "", example = "V7:BP008")
    private String refCode;

    @ApiModelProperty(position = 6, value = "상점 이름", example = "93atelier")
    private String shopName;

    @ApiModelProperty(position = 7, value = "유저 이름", example = "93atelier")
    private String userName;

    @ApiModelProperty(position = 7, value = "상점 이미지", example = "")
    private String shopImage;

    @ApiModelProperty(position = 7, value = "프로필 이미지", example = "")
    private String profileImage;

    @ApiModelProperty(position = 7, value = "생성 일자", example = "2022-12-10T23:18:48Z")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 7, value = "수정 일자", example = "2022-01-09T23:47:45Z")
    private LocalDateTime updatedAt;

    @ApiModelProperty(position = 7, value = "이미지 ID", example = "")
    private Long impId;

    @ApiModelProperty(position = 7, value = "상품 상태", example = "")
    private int status;

    @ApiModelProperty(position = 7, value = "상품 찜 횟수", example = "")
    private int favoriteCount;

    @ApiModelProperty(position = 7, value = "거래 장소", example = "")
    private int location;

    @ApiModelProperty(position = 7, value = "거래 장소", example = "")
    private Long uid;

    @ApiModelProperty(position = 7, value = "번개톡 횟수", example = "")
    private int buntalkCount;

    @ApiModelProperty(position = 7, value = "", example = "")
    private int inspection;

    @ApiModelProperty(position = 7, value = "", example = "")
    private int refSource;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean isAdult;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean isAd;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean isBizseller;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean contactHope;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean banned;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean blocked;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean bunPayFilterEnabled;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean isFavorite;

    @ApiModelProperty(position = 7, value = "", example = "")
    private Boolean care;

    public Product toEntity() {
        // TODO 항목추가
        return Product.builder()
                      .productId(this.pid)
                      .build();
    }
}