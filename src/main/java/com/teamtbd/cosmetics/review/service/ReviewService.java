package com.teamtbd.cosmetics.review.service;

import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.repository.ProductRepository;
import com.teamtbd.cosmetics.review.Review;
import com.teamtbd.cosmetics.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional //데이터베이스의 상태를 변경, 한번에 수행되어야하는 연산
@Service
public class ReviewService {
    //<요구사항>
    //리뷰작성(프엔에서) , 저장 -> 구매한 상품에 대해서만 작성해야하는데 이부분은 어떻게 해야할지 생각
    //리뷰수정
    //리뷰삭제
    //상품별 별점 계산
    //내가 작성한 후기 목록 보기

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;

    //리뷰 저장 -> 이때마다 상품 별 별점 계산
    public Review save(Review review){
        Product product=review.getProduct();

        //리뷰 개수 갱신
        int beforeReviewCount =product.getReviewCount();
        product.setReviewCount(beforeReviewCount+1);

        //누적 별점 갱신
        int beforeUpdateStar=product.getTotalRating();
        int afterUpdateStar=beforeUpdateStar+review.getRating();
        product.setTotalRating(afterUpdateStar);

        return reviewRepository.save(review);
    }

    //리뷰 수정 // 더 나은 방법 찾기
    public Optional<Review> update(Long id, Review review){
        Optional<Review> updateReview = reviewRepository.findById(id);
        //바꿀수없는것 id,join date
        updateReview.ifPresent(selectReview->{
            if(review.getTitle()!=null)
                selectReview.setTitle(review.getTitle());
            if(review.getBody()!=null)
                selectReview.setBody(review.getBody());
            if(review.getRating()!=null)
            selectReview.setRating(review.getRating());

            reviewRepository.save(selectReview);
        });
        return updateReview;
    }
}