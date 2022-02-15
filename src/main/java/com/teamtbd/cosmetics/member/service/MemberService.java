package com.teamtbd.cosmetics.member.service;

import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional //데이터베이스의 상태를 변경, 한번에 수행되어야하는 연산
@Service
public class MemberService {
    private final MemberRepository memberRepository ;

    //임시로 회원가입 수행
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memId){
        return memberRepository.findById(memId);
    }
}