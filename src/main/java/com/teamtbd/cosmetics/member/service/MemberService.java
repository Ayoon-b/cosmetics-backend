package com.teamtbd.cosmetics.member.service;

import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional //데이터베이스의 상태를 변경, 한번에 수행되어야하는 연산
@Service
public class MemberService {
    private final MemberRepository memberRepository ;

    //회원정보 수정
    public Optional<Member> updateUser(Long id, Member member){
        Optional<Member> updateUser = memberRepository.findById(id);
        //바꿀수없는것 id,join date
        updateUser.ifPresent(selectUser->{
            selectUser.setUserName(member.getUserName());
            selectUser.setNickName(member.getNickName());
            selectUser.setBirth(member.getBirth());
            selectUser.setEmail(member.getEmail());
            selectUser.setGender(member.getGender());
            selectUser.setSkinType(member.getSkinType());
            selectUser.setPhoneNumber(member.getPhoneNumber());

            memberRepository.save(selectUser);
        });
        return updateUser;
    }

    //회원탈퇴
    public Member deleteUser(Long id, Member member){
        Optional<Member> updateUser = memberRepository.findById(id);

        updateUser.ifPresent(selectUser->{

            memberRepository.delete(selectUser);
        });
        return updateUser.get();
    }

}