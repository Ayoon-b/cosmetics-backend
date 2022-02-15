package com.teamtbd.cosmetics.service;

import com.teamtbd.cosmetics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private final UserRepository memberRepository;


    public MemberService(UserRepository userRepository) {
        this.memberRepository = userRepository;
    }
}