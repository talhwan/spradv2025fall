package com.thc.sprbasic2025summer.mapper;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionDto;

import java.util.List;

public interface PermissionMapper {
    /**/
    PermissionDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param);
    List<PermissionDto.DetailResDto> pagedList(PermissionDto.PagedListReqDto param);
    int pagedListCount(PermissionDto.PagedListReqDto param);
    List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param);
}
