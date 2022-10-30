package kr.co.study.bunjang.mvc.domain.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, SearchUserRepository {
    User findByUserId(String userId);
}