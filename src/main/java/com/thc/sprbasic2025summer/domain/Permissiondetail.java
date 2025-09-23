package com.thc.sprbasic2025summer.domain;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissiondetailDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Permissiondetail extends AuditingField{
    Long permissionId;
    String target; //어떤 테이블에 대한 권한인지 ex) user, notice
    Integer func; // 어떤 기능인지 ex) 100 read, 210 create, 220 update

    protected Permissiondetail() {}
    private Permissiondetail(Long permissionId, String target, Integer func) {
        this.permissionId = permissionId;
        this.target = target;
        this.func = func;
    }

    public static Permissiondetail of(Long permissionId, String target, Integer func) {
        return new Permissiondetail(permissionId, target, func);
    }

    public void update(PermissiondetailDto.UpdateReqDto param) {
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
