package com.yang.young.common.sso.persistance.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yang.young.common.sso.persistance.jpa.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

	Optional<GroupEntity> findByName(String name);
}
