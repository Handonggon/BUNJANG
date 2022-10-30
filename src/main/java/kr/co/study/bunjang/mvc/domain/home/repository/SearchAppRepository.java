package kr.co.study.bunjang.mvc.domain.home.repository;

import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.App;
import kr.co.study.bunjang.mvc.dto.search.AppSearchDto;

@Repository
public interface SearchAppRepository {

    App searchForPageBy(AppSearchDto appSearchDto);
}