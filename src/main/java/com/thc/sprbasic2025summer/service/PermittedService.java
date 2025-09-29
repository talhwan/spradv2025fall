package com.thc.sprbasic2025summer.service;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermittedService {
    /**/
    void allow(String target, Integer func, Long reqUserId);
    boolean permitted(String target, Integer func, Long reqUserId);
}