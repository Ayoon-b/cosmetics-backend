package com.teamtbd.cosmetics.dto;

import com.teamtbd.cosmetics.domain.User;
import java.io.Serializable;
/**
 * 세션에 저장하려면 직렬화를 해야 하는데
 * User 엔티티는 추후 변경사항이 있을 수 있기 때문에
 * 직렬화를 하기 위한 별도의 SessionUser 클래스 생성
 */
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture(); }
    public SessionUser() { }
    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }
    public String getEmail() {
        return email; }
    public void setEmail(String email) {
        this.email = email; }
    public String getPicture() {
        return picture; }
    public void setPicture(String picture) {
        this.picture = picture; } }

