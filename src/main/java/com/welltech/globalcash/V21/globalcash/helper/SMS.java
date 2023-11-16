package com.welltech.globalcash.V21.globalcash.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	public String sendSMS2(String phone,String message) {
		String apiUrl = "https://sms.nalosolutions.com/smsbackend/clientapi/Resl_Nalo/send-message/?username=cliff_ye&password=cliff20$$$&type=0&destination="+phone+"&dlr=1&source=Glbl-c+1.0&message="+message;
		String responseData = restTemplate.getForObject(apiUrl, String.class);
		return responseData;
	}

	public void executeApiUrls(String phone1,String phone2, String message1, String message2) {
		// Execute the first API URL
		String apiUrl1 = "https://sms.nalosolutions.com/smsbackend/clientapi/Resl_Nalo/send-message/?username=cliff_ye&password=cliff20$$$&type=0&destination="+phone1+"&dlr=1&source=GBL-C+ALERT&message="+message1;
		ResponseEntity<String> response1 = restTemplate.getForEntity(apiUrl1, String.class);

		// Process the response from the first API URL as needed
		//String responseBody1 = response1.getBody();
		//System.out.println("Response from API URL 1: " + responseBody1);

		// Execute the second API URL
		String apiUrl2 = "https://sms.nalosolutions.com/smsbackend/clientapi/Resl_Nalo/send-message/?username=cliff_ye&password=cliff20$$$&type=0&destination="+phone2+"&dlr=1&source=Glbl-c+1.0&message="+message2;
		ResponseEntity<String> response2 = restTemplate.getForEntity(apiUrl2, String.class);

		// Process the response from the second API URL as needed
		//String responseBody2 = response2.getBody();
		//System.out.println("Response from API URL 2: " + responseBody2);


	}

}
