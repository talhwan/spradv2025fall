package com.thc.sprbasic2025summer.dto;

import com.thc.sprbasic2025summer.domain.Permissionuser;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PermissionuserDto {

    /**/

    @Setter @Getter @Builder
    public static class CreateReqDto {
        Long permissionId;
        Long userId;
        String username;

        public Permissionuser toEntity(){
            return Permissionuser.of(getPermissionId(), getUserId());
        }
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        Long permissionId;
        Long userId;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long permissionId;
        Long userId;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long permissionId;
        Long userId;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long permissionId;
        Long userId;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long permissionId;
        Long userId;
    }
}
