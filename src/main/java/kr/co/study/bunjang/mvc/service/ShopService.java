package kr.co.study.bunjang.mvc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.component.utility.MessageUtils;
import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.repository.ShopRepository;
import kr.co.study.bunjang.mvc.dto.ShopDetails;
import kr.co.study.bunjang.mvc.dto.ShopDto;

@Service
public class ShopService implements UserDetailsService {

    @Autowired
    ShopRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopDetails userDetailsDto = new ShopDetails(userRepository.findAll().get(0));
        if (ObjUtils.isEmpty(userDetailsDto)) {
            throw new UsernameNotFoundException(MessageUtils.getMessage("exception.UsernameNotFound"));
        }
        return userDetailsDto;
    }

    @Transactional
    public ShopDto save(ShopDto shopDto) {
        return new ShopDto(userRepository.save(shopDto.toEntity()));
    }
}