package kr.co.study.bunjang.mvc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.study.bunjang.component.utility.MessageUtils;
import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.model.entity.Shop;
import kr.co.study.bunjang.mvc.domain.home.repository.ShopRepository;
import kr.co.study.bunjang.mvc.dto.ShopDetails;
import kr.co.study.bunjang.mvc.dto.ShopDto;

@Service
public class ShopService implements UserDetailsService {

    @Autowired
    ShopRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopDetails userDetailsDto = new ShopDetails(userRepository.findById(ObjUtils.objToLong(username)).get());
        if (ObjUtils.isEmpty(userDetailsDto)) {
            throw new UsernameNotFoundException(MessageUtils.getMessage("exception.UsernameNotFound"));
        }
        return userDetailsDto;
    }

    @Transactional
    public ShopDto save(ShopDto shopDto) {
        return new ShopDto(userRepository.save(shopDto.toEntity()));
    }

    // @Transactional
    // public ShopDto update(Long shopNo, String shopNm, String profile_image, String context) {
    //     Shop persistence = shopRepository.findById(shopNo).get();

    //     persistence.setShopNm(shopNm);
    //     persistence.setProfile_image(profile_image);
    //     persistence.setContext(context);

    //     return new ShopDto(persistence);
    // }

    @Transactional
    public ShopDto update(ShopDto shopDto) {
        Shop persistence = userRepository.findById(shopDto.getShopNo())
            .orElseThrow(() -> {
                return new IllegalArgumentException("회원 찾기 실패 : 아이디를 찾을 수 없습니다.");
            });

        persistence.setShopNm(shopDto.getShopNm());
        persistence.setProfileImage(shopDto.getProfileImage());
        persistence.setContext(shopDto.getContext());

        return new ShopDto(persistence);
    }

    @Transactional
    public ShopDto signUp(ShopDto shopDto) {
        Shop target = userRepository.findOneByPhoneNumber(shopDto.getPhoneNumber());
            if (ObjUtils.isEmpty(target)) {
                return new ShopDto(userRepository.save(shopDto.toEntity()));
        }
        return new ShopDto(target);
    }

    // TODO 이메일 찾기
    
    public UserDetails loadUserByEmail(String eamil) throws UsernameNotFoundException {
        ShopDetails userDetailsDto = new ShopDetails(userRepository.findOneByEmail(eamil));
        if (ObjUtils.isEmpty(userDetailsDto)) {
            throw new UsernameNotFoundException(MessageUtils.getMessage("exception.UsernameNotFound"));
        }
        return userDetailsDto;
    }

    // @Transactional
    // public Shop emailFind(ShopDto shopDto, String email) {
    //     Shop shopEmail = userRepository.findOneByEmail(shopDto.getEmail())
    //         .orElseGet(() -> {
    //             return new Shop();
    //         });
    //     return shopEmail;
    // }
}