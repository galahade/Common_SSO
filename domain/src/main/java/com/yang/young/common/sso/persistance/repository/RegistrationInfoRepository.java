package com.yang.young.common.sso.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yang.young.common.sso.persistance.entity.RegistrationInfoEntity;

public interface RegistrationInfoRepository extends JpaRepository<RegistrationInfoEntity, Long> {

	Optional<RegistrationInfoEntity> findByUsername(String username);
}
