package com.welltech.globalcash.V21.globalcash.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMS {
	
	@Autowired
	private final RestTemplate restTemplate;
	
	public SMS(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String sendSMS(String phone,String message) {
		String apiUrl = "https://sms.nalosolutions.com/smsbackend/clientapi/Resl_Nalo/send-message/?username=cliff_ye&password=cliff20$$$&type=0&destination="+phone+"&dlr=1&source=GBL-C+ALERT&message="+message;
		String responseData = restTemplate.getForObject(apiUrl, String.class);
		return responseData;
	} 

}
