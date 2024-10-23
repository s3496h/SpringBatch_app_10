package com.koreait.exam.springbatch_app_10.app.member.repository;

import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
