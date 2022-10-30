package kr.co.study.bunjang.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.component.utils.MessageUtils;
import kr.co.study.bunjang.component.utils.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.repository.UserRepository;
import kr.co.study.bunjang.mvc.dto.UserDetailsDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsDto userDetailsDto = new UserDetailsDto(userRepository.findByUserId(username));
        if (ObjUtils.isEmpty(userDetailsDto)) {
            throw new UsernameNotFoundException(MessageUtils.getMessage("exception.UsernameNotFound"));
        }
        return userDetailsDto;
    }
}
