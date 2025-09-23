package com.thc.sprbasic2025summer.service;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    /**/
    DefaultDto.CreateResDto create(PermissionDto.CreateReqDto param);
    void update(PermissionDto.UpdateReqDto param);
    void delete(PermissionDto.UpdateReqDto param);
    PermissionDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PermissionDto.PagedListReqDto param);
    List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param);
}