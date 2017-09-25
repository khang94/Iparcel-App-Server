package msg.iparcel.delivery.service;

import java.util.List;

import domain.iparcel.delivery.Delivery;

public interface DeliveryService {

	Delivery pushNotification(String deviceKey, String authKeyFcm) throws Exception;

	Delivery getModel() throws Exception;

	Delivery getRandomDelivery() throws Exception;

	boolean pushNotificationTest(String deviceKey, String authKeyFcm) throws Exception;

	List<Delivery> getAll() throws Exception;

}
