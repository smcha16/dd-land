package com.project.dd.register.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.register.domain.MemberDTO;
import com.project.dd.register.mapper.RegisterMapper;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 등록과 관련된 데이터 액세스 오브젝트의 구현체 클래스.
 * @author 김형우
 */
@Primary
@Repository
@RequiredArgsConstructor
public class RegisterDAOImpl implements RegisterDAO {

    private final RegisterMapper mapper;

    /**
     * 이메일 중복 체크를 수행하는 메서드.
     *
     * @param email 확인할 이메일.
     * @return 중복된 경우 1, 그렇지 않은 경우 0.
     */
    @Override
    public int check(String email) {
        return mapper.check(email);
    }

    /**
     * 회원 등록을 수행하는 메서드.
     *
     * @param memberDTO 등록할 회원 정보 DTO 객체.
     * @return 등록 성공 시 1, 실패 시 0.
     */
    @Override
    public int register(MemberDTO memberDTO) {
        return mapper.register(memberDTO);
    }
}
