package com.thc.sprbasic2025summer.controller;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionuserDto;
import com.thc.sprbasic2025summer.security.PrincipalDetails;
import com.thc.sprbasic2025summer.service.PermissionuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/permissionuser")
@RestController
public class PermissionuserRestController {

    final PermissionuserService permissionuserService;

    /**/
    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails != null){ return principalDetails.getUser().getId(); }
        return null;
    }

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PermissionuserDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(permissionuserService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PermissionuserDto.UpdateReqDto param){
        permissionuserService.update(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PermissionuserDto.UpdateReqDto param){
        permissionuserService.delete(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public ResponseEntity<PermissionuserDto.DetailResDto> detail(DefaultDto.DetailReqDto param){
        return ResponseEntity.ok(permissionuserService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PermissionuserDto.DetailResDto>> list(PermissionuserDto.ListReqDto param){
        return ResponseEntity.ok(permissionuserService.list(param));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PermissionuserDto.PagedListReqDto param){
        return ResponseEntity.ok(permissionuserService.pagedList(param));
    }
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("permitAll()")
    @GetMapping("/scrollList")
    public ResponseEntity<List<PermissionuserDto.DetailResDto>> scrollList(PermissionuserDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("userId : " + getReqUserId(principalDetails));
        return ResponseEntity.ok(permissionuserService.scrollList(param));
    }

}
