package com.thc.sprbasic2025summer.controller;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PostDto;
import com.thc.sprbasic2025summer.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@RequestMapping("/api/auth") //전체 메서드에 해당하는 주소값 앞에 더하는 효과!!
@RestController
public class AuthRestController {

    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request) {
        String refreshToken = request.getHeader("RefreshToken");

        TokenFactory tokenFactory = new TokenFactory();
        Long userId = tokenFactory.validateToken(refreshToken);

        if(userId == null){
            throw new RuntimeException("Invalid token");
        }

        String token = tokenFactory.generateToken(userId);
        return ResponseEntity.ok().header("Authorization", token).build();
    }
}
