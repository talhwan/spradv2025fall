package com.thc.sprbasic2025summer.controller;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissiondetailDto;
import com.thc.sprbasic2025summer.security.PrincipalDetails;
import com.thc.sprbasic2025summer.service.PermissiondetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/permissiondetail")
@RestController
public class PermissiondetailRestController {

    final PermissiondetailService permissiondetailService;

    @PostMapping("/toggle")
    public ResponseEntity<DefaultDto.CreateResDto> toggle(@RequestBody PermissiondetailDto.ToggleReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(permissiondetailService.toggle(param));
    }

    /**/
    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails != null){ return principalDetails.getUser().getId(); }
        return null;
    }

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PermissiondetailDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(permissiondetailService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PermissiondetailDto.UpdateReqDto param){
        permissiondetailService.update(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PermissiondetailDto.UpdateReqDto param){
        permissiondetailService.delete(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public ResponseEntity<PermissiondetailDto.DetailResDto> detail(DefaultDto.DetailReqDto param){
        return ResponseEntity.ok(permissiondetailService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PermissiondetailDto.DetailResDto>> list(PermissiondetailDto.ListReqDto param){
        return ResponseEntity.ok(permissiondetailService.list(param));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PermissiondetailDto.PagedListReqDto param){
        return ResponseEntity.ok(permissiondetailService.pagedList(param));
    }
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("permitAll()")
    @GetMapping("/scrollList")
    public ResponseEntity<List<PermissiondetailDto.DetailResDto>> scrollList(PermissiondetailDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("userId : " + getReqUserId(principalDetails));
        return ResponseEntity.ok(permissiondetailService.scrollList(param));
    }

}
