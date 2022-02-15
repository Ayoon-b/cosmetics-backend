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
            if(member.getUserName()!=null)
                selectUser.setUserName(member.getUserName());
            if(member.getNickName()!=null)
                selectUser.setNickName(member.getNickName());
            if(member.getBirth()!=null)
                selectUser.setBirth(member.getBirth());
            if(member.getEmail()!=null)
                selectUser.setEmail(member.getEmail());
            if(member.getGender()!=null)
                selectUser.setGender(member.getGender());
            if(member.getSkinType()!=null)
                selectUser.setSkinType(member.getSkinType());
            if(member.getPhoneNumber()!=null)
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