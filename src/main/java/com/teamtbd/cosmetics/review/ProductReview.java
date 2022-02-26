package com.teamtbd.cosmetics.review;

//상품 후기가 등록될 때 마다 별점 집계 가능하도록
//상품하나가 등록될때마다 같이 생성되어야함 -> product 등록 코드에서 추가하기 ???..
public class ProductReview {

    private int oneCount;
    private int twoCount;
    private int threeCount;
    private int fourCount;
    private int fiveCount;

    private int totalCount;
    private int totalRate;
    private double ratingAvg;

    public void calculate(Rating rating){

    }
}