package kr.co.study.bunjang.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.component.utility.MessageUtils;
import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.repository.ShopRepository;
import kr.co.study.bunjang.mvc.dto.ShopDetailsDto;

@Service
public class ShopService implements UserDetailsService {

    @Autowired
    ShopRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopDetailsDto userDetailsDto = new ShopDetailsDto(userRepository.findByShopId(username));
        if (ObjUtils.isEmpty(userDetailsDto)) {
            throw new UsernameNotFoundException(MessageUtils.getMessage("exception.UsernameNotFound"));
        }
        return userDetailsDto;
    }
}
