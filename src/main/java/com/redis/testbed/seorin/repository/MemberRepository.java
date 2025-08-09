package com.redis.testbed.seorin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redis.testbed.seorin.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findByEmail(String email);
}
