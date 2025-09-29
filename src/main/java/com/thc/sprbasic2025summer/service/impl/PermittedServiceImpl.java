package com.thc.sprbasic2025summer.service.impl;

import com.thc.sprbasic2025summer.dto.PermissionDto;
import com.thc.sprbasic2025summer.mapper.PermissionMapper;
import com.thc.sprbasic2025summer.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PermittedServiceImpl implements PermittedService {

    final PermissionMapper permissionMapper;

    @Override
    public void allow(String target, Integer func, Long reqUserId) {
        System.out.println("permitted?");
        if(!permitted(target, func, reqUserId)){
            //throw new RuntimeException("no auth");
            System.out.println("not permitted..");
        }
    }

    @Override
    public boolean permitted(String target, Integer func, Long reqUserId) {
        int count = permissionMapper.exist(PermissionDto.ExistReqDto.builder().target(target).func(func).userId(reqUserId).build());
        System.out.println("permitted : " + count);
        return count > 0;
    }

    /**/

}
