package com.graduation.filmcomment.service;

import java.util.Map;

public interface SendSms {
    boolean send(String phone, String templateCode, Map<String,Object> map);
}
