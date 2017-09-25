package msg.iparcel.delivery.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import domain.iparcel.delivery.Delivery;

@Service
@RepositoryRestResource(collectionResourceRel = "deliveryRest", path = "deliveryRest")
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, UUID> {

	@Query("select d from Delivery d order by d.createdDate desc")
	List<Delivery> getAll();
}
