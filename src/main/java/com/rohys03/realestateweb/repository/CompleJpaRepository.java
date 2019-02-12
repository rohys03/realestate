package com.rohys03.realestateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompleJpaRepository extends JpaRepository<ComplexJpaVo, String> {
    List<ComplexJpaVo> findAllByComplexNo(String complexNo);
}
