package com.thc.sprbasic2025summer.service.impl;

import com.thc.sprbasic2025summer.domain.Permissiondetail;
import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissiondetailDto;
import com.thc.sprbasic2025summer.mapper.PermissiondetailMapper;
import com.thc.sprbasic2025summer.repository.PermissiondetailRepository;
import com.thc.sprbasic2025summer.service.PermissiondetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissiondetailServiceImpl implements PermissiondetailService {

    final PermissiondetailRepository permissiondetailRepository;
    final PermissiondetailMapper permissiondetailMapper;


    @Override
    public DefaultDto.CreateResDto toggle(PermissiondetailDto.ToggleReqDto param) {
        Permissiondetail permissiondetail = permissiondetailRepository.findByPermissionIdAndTargetAndFunc(param.getPermissionId(), param.getTarget(), param.getFunc());

        Boolean alive = param.getAlive();
        if(permissiondetail == null){
            if(alive){
                return create(PermissiondetailDto.CreateReqDto.builder().permissionId(param.getPermissionId()).target(param.getTarget()).func(param.getFunc()).build());
            }
        } else {
            permissiondetail.setDeleted(!alive);
            permissiondetailRepository.save(permissiondetail);
        }

        return DefaultDto.CreateResDto.builder().id((long) 0).build();
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(PermissiondetailDto.CreateReqDto param) {
        Permissiondetail permissiondetail = permissiondetailRepository.save(param.toEntity());
        return permissiondetail.toCreateResDto();
    }

    @Override
    public void update(PermissiondetailDto.UpdateReqDto param) {
        Permissiondetail permissiondetail = permissiondetailRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permissiondetail.update(param);
        permissiondetailRepository.save(permissiondetail);
    }

    @Override
    public void delete(PermissiondetailDto.UpdateReqDto param) {
        update(PermissiondetailDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PermissiondetailDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PermissiondetailDto.DetailResDto res = permissiondetailMapper.detail(param);
        return res;
    }

    @Override
    public PermissiondetailDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }
    @Override
    public List<PermissiondetailDto.DetailResDto> list(PermissiondetailDto.ListReqDto param) {
        return addList(permissiondetailMapper.list(param));
    }

    public List<PermissiondetailDto.DetailResDto> addList(List<PermissiondetailDto.DetailResDto> list) {
        List<PermissiondetailDto.DetailResDto> returnList = new ArrayList<>();
        for(PermissiondetailDto.DetailResDto each : list){
            returnList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return returnList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PermissiondetailDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(permissiondetailMapper.pagedListCount(param));
        res.setList(addList(permissiondetailMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PermissiondetailDto.DetailResDto> scrollList(PermissiondetailDto.ScrollListReqDto param) {

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
        return addList(permissiondetailMapper.scrollList(param));
    }
}
