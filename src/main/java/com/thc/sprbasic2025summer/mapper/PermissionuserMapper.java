package com.thc.sprbasic2025summer.mapper;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionuserDto;

import java.util.List;

public interface PermissionuserMapper {
    /**/
    PermissionuserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PermissionuserDto.DetailResDto> list(PermissionuserDto.ListReqDto param);
    List<PermissionuserDto.DetailResDto> pagedList(PermissionuserDto.PagedListReqDto param);
    int pagedListCount(PermissionuserDto.PagedListReqDto param);
    List<PermissionuserDto.DetailResDto> scrollList(PermissionuserDto.ScrollListReqDto param);
}
