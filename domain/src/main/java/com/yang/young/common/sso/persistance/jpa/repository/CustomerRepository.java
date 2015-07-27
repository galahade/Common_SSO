package com.yang.young.common.sso.persistance.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{
	
	Optional<CustomerEntity> findByUsername(String username);
}
