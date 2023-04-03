package csf.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csf.server.models.Order;
import csf.server.repository.OrderRepository;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    //Method to save bson into Mongodb
    //Generate and return orderId
    public String saveOrder(Order order) {
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);
		orderRepo.saveOrder(order);
		return orderId;
	}

}
