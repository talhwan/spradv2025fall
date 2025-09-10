package com.thc.sprbasic2025summer.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TokenFactory {

    public int dueTerm = 10;

    public String generateToken(Long userId) {

        String due = null;
        Date date = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Java 시간 더하기
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, dueTerm);

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
