package com.example.demo.service;

import com.example.demo.enums.UserRole;
import com.example.demo.model.LoginUser;
import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        // DB에서 id라는 유저를 조회
        // TODO: 만드예정임
        UserModel user = userService.selectUserByUserId(username);

        System.out.println("로그인 인증:" + username);

        if(ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("없는 사용자입니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        //UserRole.findBy("ROLE_ADMIN"). getValueKor(); // "관리자"

        if("admin".equals(user.getUserId())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));

        }
        return new LoginUser(user,authorities);

    }
}
