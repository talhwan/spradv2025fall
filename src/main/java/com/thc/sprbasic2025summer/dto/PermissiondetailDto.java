package com.thc.sprbasic2025summer.dto;

import com.thc.sprbasic2025summer.domain.Permissiondetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PermissiondetailDto {

    /**/

    @Setter @Getter @Builder
    public static class ToggleReqDto {
        Long permissionId;
        Boolean alive;
        String target;
        Integer func;
    }
    @Setter @Getter @Builder
    public static class CreateReqDto {
        Long permissionId;
        String target;
        Integer func;

        public Permissiondetail toEntity(){
            return Permissiondetail.of(getPermissionId(), getTarget(), getFunc());
        }
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        Long permissionId;
        String target;
        Integer func;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long permissionId;
        String target;
        Integer func;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long permissionId;
        String target;
        Integer func;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long permissionId;
        String target;
        Integer func;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long permissionId;
        String target;
        Integer func;
    }
}
