package com.welltech.globalcash.V21.globalcash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService{

    private final RestTemplate restTemplate;
    @Autowired
    public SmsServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public String sendSms(String phone, String message) {
        String apiUrl = "https://sms.nalosolutions.com/smsbackend/clientapi/Resl_Nalo/send-message/?username=cliff_ye&password=cliff20$$$&type=0&destination="+phone+"&dlr=1&source=GBL-C+ALERT&message="+message;
        String responseData = restTemplate.getForObject(apiUrl, String.class);
        return responseData;
    }
}
