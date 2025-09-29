package com.thc.sprbasic2025summer.service.impl;

import com.thc.sprbasic2025summer.domain.Permissionuser;
import com.thc.sprbasic2025summer.domain.User;
import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionuserDto;
import com.thc.sprbasic2025summer.mapper.PermissionuserMapper;
import com.thc.sprbasic2025summer.repository.PermissionuserRepository;
import com.thc.sprbasic2025summer.repository.UserRepository;
import com.thc.sprbasic2025summer.service.PermissionuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionuserServiceImpl implements PermissionuserService {

    final PermissionuserRepository permissionuserRepository;
    final PermissionuserMapper permissionuserMapper;
    final UserRepository userRepository;

    /**/

    @Override
    public DefaultDto.CreateResDto create(PermissionuserDto.CreateReqDto param) {
        if(param.getUserId() == null) {
            if(param.getUsername() == null) {
                throw new RuntimeException("not enough params");
            } else {
                User user = userRepository.findByUsername(param.getUsername());
                if(user == null) {
                    throw new RuntimeException("user not found");
                } else {
                    param.setUserId(user.getId());
                }
            }
        }

        Permissionuser permissionuser = permissionuserRepository.save(param.toEntity());
        return permissionuser.toCreateResDto();
    }

    @Override
    public void update(PermissionuserDto.UpdateReqDto param) {
        Permissionuser permissionuser = permissionuserRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permissionuser.update(param);
        permissionuserRepository.save(permissionuser);
    }

    @Override
    public void delete(PermissionuserDto.UpdateReqDto param) {
        update(PermissionuserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PermissionuserDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PermissionuserDto.DetailResDto res = permissionuserMapper.detail(param);
        return res;
    }

    @Override
    public PermissionuserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }
    @Override
    public List<PermissionuserDto.DetailResDto> list(PermissionuserDto.ListReqDto param) {
        return addList(permissionuserMapper.list(param));
    }

    public List<PermissionuserDto.DetailResDto> addList(List<PermissionuserDto.DetailResDto> list) {
        List<PermissionuserDto.DetailResDto> returnList = new ArrayList<>();
        for(PermissionuserDto.DetailResDto each : list){
            returnList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return returnList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PermissionuserDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(permissionuserMapper.pagedListCount(param));
        res.setList(addList(permissionuserMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PermissionuserDto.DetailResDto> scrollList(PermissionuserDto.ScrollListReqDto param) {
        param.init();
        return addList(permissionuserMapper.scrollList(param));
    }
}
