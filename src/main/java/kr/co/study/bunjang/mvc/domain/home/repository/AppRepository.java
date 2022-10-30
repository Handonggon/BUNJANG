package kr.co.study.bunjang.mvc.domain.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long>, SearchAppRepository {

    App findByClientId(String ClientId);

    App findByEntityId(String entityId);
}