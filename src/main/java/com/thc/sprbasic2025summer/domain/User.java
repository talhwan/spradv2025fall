package com.thc.sprbasic2025summer.domain;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class User extends AuditingField{
    @Column(unique = true, nullable = false)
    String username; // 아이디 기능!
    String password; // 비밀번호!!
    String name;
    String phone;

    protected User() {}
    private User(String username, String password, String name, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public static User of(String username, String password, String name, String phone) {
        return new User(username, password, name, phone);
    }

    //fetch 타입 바꾸고, toString 순환 참조 수정
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserRoleType> userRoleType = new ArrayList<>();
    //권한 관련한 기능 추가
    public List<UserRoleType> getRoleList(){
        if(!this.userRoleType.isEmpty()){
            return userRoleType;
        }
        return new ArrayList<>();
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
