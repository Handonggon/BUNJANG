package kr.co.study.bunjang.mvc.domain.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.SsoHistory;

@Repository
public interface SsoHistoryRepository extends JpaRepository<SsoHistory, Long> {
    
}