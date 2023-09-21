package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.entity.CaptchaEntity;

@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaEntity, Long>{

}
