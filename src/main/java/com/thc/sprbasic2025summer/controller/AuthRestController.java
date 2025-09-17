package com.thc.sprbasic2025summer.controller;

import com.thc.sprbasic2025summer.dto.DefaultDto;
import com.thc.sprbasic2025summer.dto.PostDto;
import com.thc.sprbasic2025summer.exception.InvalidTokenException;
import com.thc.sprbasic2025summer.security.AuthService;
import com.thc.sprbasic2025summer.security.ExternalProperties;
import com.thc.sprbasic2025summer.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@RequiredArgsConstructor
@RequestMapping("/api/auth") //전체 메서드에 해당하는 주소값 앞에 더하는 효과!!
@RestController
public class AuthRestController {

    //String token_prefix = "Bearer ";
    //final TokenFactory tokenFactory;

    final AuthService authService;
    final ExternalProperties externalProperties;

    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request) {
        String refreshToken = request.getHeader(externalProperties.getRefreshKey());
        String prefix = externalProperties.getTokenPrefix();
        if(refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith(prefix)) {
            throw new InvalidTokenException("Refresh Token Not Found");
        }
        refreshToken = refreshToken.substring(prefix.length());
        String accessToken = authService.issueAccessToken(refreshToken);
        return ResponseEntity.ok().header(externalProperties.getAccessKey(), prefix + accessToken).build();
    }
    /*
    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request) {
        String refreshToken = request.getHeader("RefreshToken");

        if(refreshToken != null && !refreshToken.isEmpty() && refreshToken.startsWith(token_prefix)){
            // 정상 토큰 입니다!
            refreshToken = refreshToken.substring(token_prefix.length());
        } else {
            throw new RuntimeException("Invalid token");
        }

        Long userId = tokenFactory.validateRefreshToken(refreshToken);

        if(userId == null){
            throw new RuntimeException("Invalid token");
        }

        String token = tokenFactory.createAccessToken(userId);
        return ResponseEntity.ok().header("Authorization", token_prefix + token).build();
    }
    */
}
