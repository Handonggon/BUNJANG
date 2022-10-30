package kr.co.study.bunjang.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.component.utils.MessageUtils;
import kr.co.study.bunjang.component.utils.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.repository.AppRepository;
import kr.co.study.bunjang.mvc.dto.AppDetailsDto;
import kr.co.study.bunjang.mvc.dto.AppDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppService implements UserDetailsService {

    @Autowired
    AppRepository appRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppDetailsDto appDetailsDto = new AppDetailsDto(appRepository.findByClientId(username));
        if (ObjUtils.isEmpty(appDetailsDto)) {
            throw new UsernameNotFoundException(MessageUtils.getMessage("exception.UsernameNotFound"));
        }
        return appDetailsDto;
    }

    public AppDto findByEntityId(String entityId) {
        return new AppDto(appRepository.findByEntityId(entityId));
    }
}
