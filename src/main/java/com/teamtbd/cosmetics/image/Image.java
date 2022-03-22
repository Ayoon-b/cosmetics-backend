package com.teamtbd.cosmetics.image;

import com.teamtbd.cosmetics.domain.BaseTimeEntity;
import com.teamtbd.cosmetics.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
//한사람의 리뷰 1개당 여러 사진첨부 가능함 1:N 관계
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="reviewid")
    private Review review;

    @Column(nullable = false)
    private String originalFileName; //파일 원본명 //동일한 파일명 주의

    @Column(nullable = false)
    private String filePath; // 파일 저장 경로

    @Column
    private Long fileSize;

    @Builder
    public Image(String originalFileName, String filePath, Long fileSize){
        this.filePath=filePath;
        this.fileSize=fileSize;
        this.originalFileName=originalFileName;
    }
}
