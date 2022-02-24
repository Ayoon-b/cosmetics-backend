package com.teamtbd.cosmetics.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional //데이터베이스의 상태를 변경, 한번에 수행되어야하는 연산
@Service
public class ReviewService {
    //리뷰작성 , 저장
    //리뷰수정
    //리뷰삭제
}
