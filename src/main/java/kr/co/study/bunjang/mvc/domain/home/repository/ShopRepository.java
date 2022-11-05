package kr.co.study.bunjang.mvc.domain.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, SearchShopRepository {
    Shop findByShopId(String shopId);
}