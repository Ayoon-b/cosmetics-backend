package com.teamtbd.cosmetics.dto;

import com.teamtbd.cosmetics.domain.Role;
import com.teamtbd.cosmetics.domain.User;
import lombok.Builder;
import lombok.Getter;
import java.util.Map;
import java.lang.String;


@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey, name, email, picture;
    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }


    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        //google, naver, kakao
        switch (registrationId) {
            case "naver":
                return ofNaver("id", attributes);
            case "kakao":
                return ofKakao("id", attributes);
            case "facebook":
                return ofFacebook("id", attributes);

        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName,
                                          Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String,Object>)attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String,Object> response = (Map<String, Object>)attributes.get("kakao_account");
        Map<String,Object> profile = (Map<String, Object>) response.get("profile");
        return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email((String) response.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }




    private static OAuthAttributes ofFacebook(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttributes(attributes,
                userNameAttributeName,
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture")); }



    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttributes(attributes,
                userNameAttributeName,
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture")); }


    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}

