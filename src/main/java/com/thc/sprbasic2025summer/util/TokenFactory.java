package com.thc.sprbasic2025summer.util;

import com.thc.sprbasic2025summer.domain.RefreshToken;
import com.thc.sprbasic2025summer.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Component
public class TokenFactory {

    private final RefreshTokenRepository refreshTokenRepository;

    public int refreshTokenTerm = 60 * 6;
    public int accessTokenTerm = 30;

    public void revokeRefreshToken(Long userId) {
        List<RefreshToken> list = refreshTokenRepository.findByUserId(userId);
        if(list != null && !list.isEmpty()){
            refreshTokenRepository.deleteAll(list);
        }
    }
    public String createRefreshToken(Long userId) {
        revokeRefreshToken(userId);
        return generateToken(userId, refreshTokenTerm);
    }
    public String createAccessToken(Long userId) {
        return generateToken(userId, accessTokenTerm);
    }
    public Long validateRefreshToken(String token){

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElse(null);
        if(refreshToken == null){
            return null;
        } else {
            Long tempUserId = refreshToken.getUserId();
            Long tokenUserId = validateToken(token);
            if(tempUserId.equals(tokenUserId)){
                return tokenUserId;
            } else {
                return null;
            }
        }
    }
    public Long validateAccessToken(String token){
        return validateToken(token);
    }

    public String generateToken(Long userId, int term) {

        String due = null;
        Date date = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Java 시간 더하기
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, term);

        due = sdformat.format(cal.getTime());
        System.out.println("만료일시 : " + due);

        String data = userId + "_" + due;
        String token = null;
        try{
            token = AES256Cipher.AES_Encode(null, data);
        } catch(Exception e){
        }
        return token;
    }

    public Long validateToken(String token){
        String data = null;
        try{
            data = AES256Cipher.AES_Decode(null, token);
        } catch (Exception e){

        }
        if(data == null){
            return null;
        }
        String[] dataArr = data.split("_");
        if(dataArr.length != 2){
            return null; //유효하지 않은 토큰!
        }
        System.out.println(dataArr[0]);
        System.out.println(dataArr[1]);
        Long userId = Long.parseLong(dataArr[0]);
        String due = dataArr[1];

        String now = null;
        Date date = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Java 시간 더하기
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        now = sdformat.format(cal.getTime());

        System.out.println("now : " + now);
        System.out.println("due : " + due);

        String[] nowArr = {now, due};
        Arrays.sort(nowArr);
        //
        if(!now.equals(nowArr[0])){
            return null;
        }

        return userId;
    }

}
