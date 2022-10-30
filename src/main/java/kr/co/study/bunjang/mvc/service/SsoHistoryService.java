package kr.co.study.bunjang.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.mvc.domain.home.repository.SsoHistoryRepository;
import kr.co.study.bunjang.mvc.dto.SsoHistoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SsoHistoryService {

    @Autowired
    SsoHistoryRepository ssoHistoryRepository;

    public SsoHistoryDto save(SsoHistoryDto ssoHistoryDto) {
        return new SsoHistoryDto(ssoHistoryRepository.save(ssoHistoryDto.toEntity()));
    }
}