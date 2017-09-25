package msg.iparcel.delivery.service;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.iparcel.delivery.Delivery;
import msg.iparcel.delivery.common.Constant;
import msg.iparcel.delivery.common.CoreHelper;
import msg.iparcel.delivery.config.Configuration;
import msg.iparcel.delivery.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryServiceImpl.class);

	@Autowired
	DeliveryRepository deliveryRepo;

	@Autowired
	CoreHelper coreHelper;

	@Autowired
	Configuration configuration;

	public static List<Delivery> deliveries = new ArrayList<>();

	@PostConstruct
	List<Delivery> initDeliveries() throws Exception {
		deliveries = configuration.initListDelivery();

		return deliveries;
	}

	@Override
	public boolean pushNotificationTest(String deviceKey, String authKeyFcm) throws Exception {

		boolean isPushSuccess = false;

		String authKey = authKeyFcm; // You FCM AUTH key
		String FMCurl = Constant.API_URL_FCM;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		// json.put("to",userDeviceIdKey.trim());
		JSONObject info = new JSONObject();
		info.put("title", "Hello Khang"); // Notification title
		info.put("body", "You are being success"); // Notification body
		json.put("notification", info);
		json.put("to", deviceKey.trim());
		// json.put("to", "d6f7ebefeb9f9f36");

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		conn.getInputStream();

		isPushSuccess = true;

		return isPushSuccess;
	}

	@Override
	public Delivery pushNotification(String deviceKey, String authKeyFcm) throws Exception {

		boolean isPushSuccess = false;

		String authKey = authKeyFcm; // You FCM AUTH key
		String FMCurl = Constant.API_URL_FCM;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		// json.put("to",userDeviceIdKey.trim());
		JSONObject info = new JSONObject();

		// Get random delivery
		Delivery d = new Delivery();
		d = this.getRandomDelivery();

		info.put("title", "Hello there"); // Notification title
		info.put("body", "New Delivery ! " + d.getDeliveryName()); // Notification
																	// body
		json.put("notification", info);
		json.put("to", deviceKey.trim());

		LOGGER.info("Save delivery to db....");
		d.setCreatedDate(new Date());
		deliveryRepo.save(d);

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		conn.getInputStream();

		isPushSuccess = true;
		LOGGER.info("Push notification success");

		return d;
	}

	@Override
	public Delivery getModel() throws Exception {
		Delivery d = (Delivery) coreHelper.createModelStructure(new Delivery());
		return d;
	}

	@Override
	public Delivery getRandomDelivery() throws Exception {
		int randomIndex = configuration.randInt(0, 8);

		return configuration.initListDelivery().get(randomIndex);
	}

	@Override
	public List<Delivery> getAll() throws Exception {
		return deliveryRepo.getAll();
	}
}
