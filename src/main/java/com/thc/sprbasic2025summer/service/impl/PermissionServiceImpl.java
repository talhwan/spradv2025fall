package com.thc.sprbasic2025summer.service.impl;

import com.thc.sprbasic2025summer.domain.Permission;
import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionDto;
import com.thc.sprbasic2025summer.mapper.PermissionMapper;
import com.thc.sprbasic2025summer.repository.PermissionRepository;
import com.thc.sprbasic2025summer.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    final PermissionRepository permissionRepository;
    final PermissionMapper permissionMapper;

    /**/

    @Override
    public DefaultDto.CreateResDto create(PermissionDto.CreateReqDto param) {
        Permission permission = permissionRepository.save(param.toEntity());
        return permission.toCreateResDto();
    }

    @Override
    public void update(PermissionDto.UpdateReqDto param) {
        Permission permission = permissionRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permission.update(param);
        permissionRepository.save(permission);
    }

    @Override
    public void delete(PermissionDto.UpdateReqDto param) {
        update(PermissionDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PermissionDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PermissionDto.DetailResDto res = permissionMapper.detail(param);
        return res;
    }

    @Override
    public PermissionDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }
    @Override
    public List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param) {
        return addList(permissionMapper.list(param));
    }

    public List<PermissionDto.DetailResDto> addList(List<PermissionDto.DetailResDto> list) {
        List<PermissionDto.DetailResDto> returnList = new ArrayList<>();
        for(PermissionDto.DetailResDto each : list){
            returnList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return returnList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PermissionDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(permissionMapper.pagedListCount(param));
        res.setList(addList(permissionMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param) {

        /*try{
            String data = "112233";
            String encodedData = AES256Cipher.AES_Encode(null, data);
            System.out.println("data : " +  data );
            System.out.println( encodedData );
            String decodedData = AES256Cipher.AES_Decode(null, encodedData);
            System.out.println("decodedData : " +  decodedData );
        } catch (Exception e){

        }*/

        param.init();
        return addList(permissionMapper.scrollList(param));
    }
}
