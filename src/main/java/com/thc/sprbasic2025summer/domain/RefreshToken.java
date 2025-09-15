package com.thc.sprbasic2025summer.domain;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class RefreshToken extends AuditingField{
    Long userId;
    @Column(length = 4000) String token;

    protected RefreshToken() {}
    private RefreshToken(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public static RefreshToken of(Long userId, String token) {
        return new RefreshToken(userId, token);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
