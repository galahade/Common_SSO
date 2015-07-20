package com.yang.young.common.sso.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yang.young.common.sso.persistance.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

}
