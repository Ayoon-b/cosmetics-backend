package com.teamtbd.cosmetics.review;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
//m:n연결 필요 member와도 연결돼야하고, product랑도 연결돼야함
//상품명, 쓴사람id , 별점, 리뷰제목, 리뷰내용, 사진
public class Review {
    @Id
    private String id; //리뷰만의 고유id

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private int star;

    //이미지 어캐넣을건지 고민

}
