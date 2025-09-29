package com.thc.sprbasic2025summer.service.impl;

import com.thc.sprbasic2025summer.domain.Permission;
import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionDto;
import com.thc.sprbasic2025summer.dto.PermissiondetailDto;
import com.thc.sprbasic2025summer.mapper.PermissionMapper;
import com.thc.sprbasic2025summer.repository.PermissionRepository;
import com.thc.sprbasic2025summer.service.PermissionService;
import com.thc.sprbasic2025summer.service.PermissiondetailService;
import com.thc.sprbasic2025summer.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    final PermissionRepository permissionRepository;
    final PermissionMapper permissionMapper;
    final PermissiondetailService permissiondetailService;
    final PermittedService permittedService;

    String target = "permission";

    /**/

    @Override
    public DefaultDto.CreateResDto create(PermissionDto.CreateReqDto param, Long reqUserId) {
        //권한을 체크할수 있습니다!
        System.out.println("create : " + target + "//" + reqUserId);
        permittedService.allow(target, 110, reqUserId);

        Permission permission = permissionRepository.save(param.toEntity());
        return permission.toCreateResDto();
    }

    @Override
    public void update(PermissionDto.UpdateReqDto param, Long reqUserId) {
        Permission permission = permissionRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permission.update(param);
        permissionRepository.save(permission);
    }

    @Override
    public void delete(PermissionDto.UpdateReqDto param, Long reqUserId) {
        update(PermissionDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public PermissionDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        PermissionDto.DetailResDto res = permissionMapper.detail(param);
        if(res == null){
            throw new RuntimeException("no data");
        }
        res.setTargets(PermissionDto.targets);
        res.setDetails(
                permissiondetailService.list(PermissiondetailDto.ListReqDto.builder().deleted(false).permissionId(res.getId()).build())
        );
        return res;
    }

    @Override
    public PermissionDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }
    @Override
    public List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param, Long reqUserId) {
        return addList(permissionMapper.list(param), reqUserId);
    }

    public List<PermissionDto.DetailResDto> addList(List<PermissionDto.DetailResDto> list, Long reqUserId) {
        List<PermissionDto.DetailResDto> returnList = new ArrayList<>();
        for(PermissionDto.DetailResDto each : list){
            returnList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return returnList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PermissionDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(permissionMapper.pagedListCount(param));
        res.setList(addList(permissionMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param, Long reqUserId) {

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
        return addList(permissionMapper.scrollList(param), reqUserId);
    }
}
