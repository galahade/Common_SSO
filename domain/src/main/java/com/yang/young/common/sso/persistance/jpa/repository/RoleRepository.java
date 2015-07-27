package com.yang.young.common.sso.persistance.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yang.young.common.sso.persistance.jpa.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	Optional<RoleEntity> findByName(String name);

}
