package com.yang.young.common.sso.persistance.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{

}
