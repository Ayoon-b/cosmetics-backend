package com.teamtbd.cosmetics.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional //데이터베이스의 상태를 변경, 한번에 수행되어야하는 연산
@Service
public class ReviewService {
    //<요구사항>
    //리뷰작성 , 저장 -> 구매한 상품에 대해서만 작성해야하는데 이부분은 어떻게 해야할지 생각
    //리뷰수정
    //리뷰삭제
    //상품별 별점 계산
    //내가 작성한 후기 목록 보기

    //리뷰 저장
    public Review saveReview(Review review){
        Review review1=reviewRepository.save(review);
        return review1;
    }
}
