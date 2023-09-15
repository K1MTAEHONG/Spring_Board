package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.thymeleaf.util.StringUtils;

@Getter
@AllArgsConstructor                 //생성자 를 알아서 다 만들어 주는 어노테이션
public enum UserRole {

    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "회원");

    private String value;
    private String valueKor;

    public static UserRole findBy(String v ){
        for (UserRole r : UserRole.values()) {
            if (StringUtils.equals(r.getValue(), v)) {
                return r;
            }
        }

        return UserRole.USER;
    }
}
