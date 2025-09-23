package com.thc.sprbasic2025summer.dto;

import com.thc.sprbasic2025summer.domain.Permission;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class PermissionDto {

    /**/

    @Setter @Getter @Builder
    public static class CreateReqDto {
        String title;
        String content;

        public Permission toEntity(){
            return Permission.of(getTitle(), getContent());
        }
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String title;
        String content;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        String title;
        String content;
    }

    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto {
        String title;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        String title;
    }
    @Setter @Getter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        String title;
    }
}
