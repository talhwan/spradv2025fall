package com.thc.sprbasic2025summer.mapper;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissiondetailDto;

import java.util.List;

public interface PermissiondetailMapper {
    /**/
    PermissiondetailDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PermissiondetailDto.DetailResDto> list(PermissiondetailDto.ListReqDto param);
    List<PermissiondetailDto.DetailResDto> pagedList(PermissiondetailDto.PagedListReqDto param);
    int pagedListCount(PermissiondetailDto.PagedListReqDto param);
    List<PermissiondetailDto.DetailResDto> scrollList(PermissiondetailDto.ScrollListReqDto param);
}
