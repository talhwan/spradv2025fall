package com.thc.sprbasic2025summer.domain;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionuserDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Permissionuser extends AuditingField{
    Long permissionId;
    Long userId;

    protected Permissionuser() {}
    private Permissionuser(Long permissionId, Long userId) {
        this.permissionId = permissionId;
        this.userId = userId;
    }

    public static Permissionuser of(Long permissionId, Long userId) {
        return new Permissionuser(permissionId, userId);
    }

    public void update(PermissionuserDto.UpdateReqDto param) {
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
