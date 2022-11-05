package kr.co.study.bunjang.mvc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Menu;
import kr.co.study.bunjang.mvc.domain.home.repository.MenuRepository;
import kr.co.study.bunjang.mvc.dto.MenuDto;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
    
    public List<MenuDto> findAll() {
        return menuRepository.findAll().stream().map(menu->new MenuDto(menu)).collect(Collectors.toList());
    }

    @Transactional
    public MenuDto update(Long menuid, String menuDesc) {
        Menu menu = menuRepository.findById(menuid).get();
        menu.setMenuDesc(menuDesc);

        return new MenuDto(menu);
    }

    @Transactional
    public MenuDto save(MenuDto menuDto) {
        return new MenuDto(menuRepository.save(menuDto.toEntity()));
    }
}
