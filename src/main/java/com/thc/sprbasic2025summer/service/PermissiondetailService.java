package com.thc.sprbasic2025summer.service;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissiondetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissiondetailService {
    /**/
    DefaultDto.CreateResDto toggle(PermissiondetailDto.ToggleReqDto param);
    DefaultDto.CreateResDto create(PermissiondetailDto.CreateReqDto param);
    void update(PermissiondetailDto.UpdateReqDto param);
    void delete(PermissiondetailDto.UpdateReqDto param);
    PermissiondetailDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PermissiondetailDto.DetailResDto> list(PermissiondetailDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PermissiondetailDto.PagedListReqDto param);
    List<PermissiondetailDto.DetailResDto> scrollList(PermissiondetailDto.ScrollListReqDto param);
}