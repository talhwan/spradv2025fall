package com.thc.sprbasic2025summer.service;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionuserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionuserService {
    /**/
    DefaultDto.CreateResDto create(PermissionuserDto.CreateReqDto param);
    void update(PermissionuserDto.UpdateReqDto param);
    void delete(PermissionuserDto.UpdateReqDto param);
    PermissionuserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PermissionuserDto.DetailResDto> list(PermissionuserDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PermissionuserDto.PagedListReqDto param);
    List<PermissionuserDto.DetailResDto> scrollList(PermissionuserDto.ScrollListReqDto param);
}