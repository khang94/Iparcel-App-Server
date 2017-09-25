package msg.iparcel.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.iparcel.delivery.Delivery;
import domain.iparcel.http.JsonHttp;
import msg.iparcel.delivery.common.JsonHttpService;
import msg.iparcel.delivery.service.DeliveryService;

@Controller
public class DeliveryController {
	
	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	JsonHttpService jsonHttpService;
	
	@RequestMapping(value = "delivery/about", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> about() {
		String message = "Welcome to Delivery Microservice group !";
		return ResponseEntity.ok(message);
	}
	
	@RequestMapping(value = "delivery/model", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getModel() throws Exception {
		Delivery delivery = deliveryService.getModel();
		return ResponseEntity.ok(delivery);
	}
	
	@RequestMapping(value = "delivery/push", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> pushNotification(@RequestParam("deviceKey") String deviceKeyId,
			@RequestParam("authKeyFcm") String authKeyFcm) throws Exception {
		
		String AUTH_KEY_FCM = authKeyFcm;
		String deviceKey = deviceKeyId;
		
		Delivery delivery = deliveryService.pushNotification(deviceKey, AUTH_KEY_FCM);
		JsonHttp jsonHttp = new JsonHttp();
		
		try {
			jsonHttp = jsonHttpService.saveDataSuccess(delivery, "Get delivery data success");
		} catch (Exception ex){
			jsonHttp = jsonHttpService.saveDataSuccess(delivery, "Get delivery failed..");
		}
		
		
		return new ResponseEntity<>(jsonHttp , jsonHttp.getHttpCode());
	}
	
	@RequestMapping(value = "delivery/getAll", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllDelivery() throws Exception {
		
		
		List<Delivery> deliveries = deliveryService.getAll();
		JsonHttp jsonHttp = new JsonHttp();
		
		try {
			jsonHttp = jsonHttpService.saveDataSuccess(deliveries, "Get delivery data success");
		} catch (Exception ex){
			jsonHttp = jsonHttpService.saveDataSuccess(deliveries, "Get delivery failed..");
		}
	
		return new ResponseEntity<>(jsonHttp , jsonHttp.getHttpCode());
	}

}
