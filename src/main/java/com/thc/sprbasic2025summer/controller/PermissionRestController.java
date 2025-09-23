package com.thc.sprbasic2025summer.controller;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionDto;
import com.thc.sprbasic2025summer.security.PrincipalDetails;
import com.thc.sprbasic2025summer.service.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/permission")
@RestController
public class PermissionRestController {

    final PermissionService permissionService;

    /**/
    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails != null){ return principalDetails.getUser().getId(); }
        return null;
    }

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PermissionDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(permissionService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PermissionDto.UpdateReqDto param){
        permissionService.update(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PermissionDto.UpdateReqDto param){
        permissionService.delete(param);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public ResponseEntity<PermissionDto.DetailResDto> detail(DefaultDto.DetailReqDto param){
        return ResponseEntity.ok(permissionService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PermissionDto.DetailResDto>> list(PermissionDto.ListReqDto param){
        return ResponseEntity.ok(permissionService.list(param));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PermissionDto.PagedListReqDto param){
        return ResponseEntity.ok(permissionService.pagedList(param));
    }
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("permitAll()")
    @GetMapping("/scrollList")
    public ResponseEntity<List<PermissionDto.DetailResDto>> scrollList(PermissionDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("userId : " + getReqUserId(principalDetails));
        return ResponseEntity.ok(permissionService.scrollList(param));
    }

}
