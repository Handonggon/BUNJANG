package kr.co.study.bunjang.mvc.dto;

import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.model.entity.Menu;
import kr.co.study.bunjang.mvc.vo.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MenuDto extends AbstractDto {

	// TODO @ApiModelProperty(value="최상위 여부(1건만 존재)", example = "Y")
	private Long menuNo;

	private Long upMenuNo;

	private String menuPath;

	private String menuIcon;

	private String menuNm;

	private String menuDesc;

	private int menuLevel;

	private int menuOrder;

	private Yn useYn;

	public MenuDto(Menu menu) {
		this.menuNo = menu.getMenuNo();
		if (!ObjUtils.isEmpty(menu.getParent())) {
			this.upMenuNo = menu.getParent().getMenuNo();
		}
		this.menuPath = menu.getMenuPath();
		this.menuIcon = menu.getMenuIcon();
		this.menuNm = menu.getMenuNm();
		this.menuDesc = menu.getMenuDesc();
		this.menuLevel = menu.getMenuLevel();
		this.menuOrder = menu.getMenuOrder();
		this.useYn = menu.getUseYn();
	}

	public Menu toEntity(Menu parentMenu) {
		return Menu.builder()
				   .menuNo(this.menuNo)
				   .parent(parentMenu)
				   .menuPath(this.menuPath)
				   .menuIcon(this.menuIcon)
				   .menuNm(this.menuNm)
				   .menuDesc(this.menuDesc)
				   .menuLevel(this.menuLevel)
				   .menuOrder(this.menuOrder)
				   .useYn(this.useYn)
				   .build();
	}

	public Menu toEntity() {
		return Menu.builder()
				   .menuNo(this.menuNo)
				   .menuPath(this.menuPath)
				   .menuIcon(this.menuIcon)
				   .menuNm(this.menuNm)
				   .menuDesc(this.menuDesc)
				   .menuLevel(this.menuLevel)
				   .menuOrder(this.menuOrder)
				   .useYn(this.useYn)
				   .build();
	}
}
