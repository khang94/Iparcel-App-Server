package domain.iparcel.delivery;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import domain.iparcel.elements.Coordinates;
import lombok.Data;

@Entity
@Data
public class Delivery {
	
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	@Type(type = "pg-uuid")
	private UUID uuid;

	@Version
	Long version;
	
	private String driverName;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Coordinates coorFrom;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Coordinates coorTo;
	
	private String fromPoint;
	private String toPoint;
	
	private String deliveryName;
	private String deliveryDescription;
	
	private String distance;
	
	private Date createdDate;
}
	