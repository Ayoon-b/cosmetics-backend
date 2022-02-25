package com.teamtbd.cosmetics.review;

import com.teamtbd.cosmetics.member.Member;
import com.teamtbd.cosmetics.product.Product;
import lombok.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
//m:n연결 필요 member와도 연결돼야하고, product랑도 연결돼야함
//상품명(또는 상품의 고유 id product랑 연결), 쓴사람id(member랑 연결) , 별점, 리뷰제목, 리뷰내용, 사진
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //리뷰만의 고유id

    @Column
    private Long reviewerId;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column
    private String title;

    @Column
    private String review;

    @Column
    @Enumerated(EnumType.STRING)
    private Rating rating;

    //이미지 어캐넣을건지 고민

}
