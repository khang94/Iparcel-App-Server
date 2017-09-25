package msg.iparcel.delivery.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.iparcel.delivery.Delivery;
import msg.iparcel.delivery.common.CoreHelper;

@Service
public class Configuration {
	
	@Autowired
	CoreHelper coreHelper;
	
	
	
	public List<Delivery> initListDelivery() throws Exception{
		
		Delivery delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		List<Delivery> lstRandom = new ArrayList<Delivery>();
		
		delivery.getCoorTo().setLatitude("10.831911391128");
		delivery.getCoorTo().setLongitude("106.63768418133259");
		
		delivery.setDistance("2.7 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Cao Dang Van Hoa Nghe Thuat");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.827190474381839");
		delivery.getCoorTo().setLongitude("106.62873432040215");
		
		delivery.setDistance("2.5 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Dai Phu Restaurant");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.827385095118284");
		delivery.getCoorTo().setLongitude("106.62396132946014");
		
		delivery.setDistance("1.9 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Saigon Fashion Company");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.820368460200989");
		delivery.getCoorTo().setLongitude("106.63040064275266");
		
		delivery.setDistance("2.8 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Khu Cong Nghiep Tan Binh");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.817819221094837");
		delivery.getCoorTo().setLongitude("106.63131091743708");
		
		delivery.setDistance("3.5 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Supermarket Coopmart Thang Loi");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.823039852470988");
		delivery.getCoorTo().setLongitude("106.63278110325336");
		delivery.setDistance("4 km");
		
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Tan Tru Elementary School");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.828623617688185");
		delivery.getCoorTo().setLongitude("106.62464093416928");
		
		delivery.setDistance("3.7 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Cho Lon Supermarket");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.827623184273632");
		delivery.getCoorTo().setLongitude("106.62532489746809");
		
		delivery.setDistance("3.2 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("FPT Shop");
		lstRandom.add(delivery);
		
		delivery = (Delivery) coreHelper.createModelStructure(new Delivery());
		delivery.getCoorTo().setLatitude("10.826682681611313");
		delivery.getCoorTo().setLongitude("106.62624321877956");
		
		delivery.setDistance("3.15 km");
		
		delivery.setToPoint(delivery.getCoorTo().toString());
		delivery.setDeliveryName("Agribank Truong Chinh");
		lstRandom.add(delivery);
		
		return lstRandom;
	}
	
	public int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    
	    return randomNum;
	}
}