package csf.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csf.server.models.Order;
import csf.server.service.OrderService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrder(@RequestBody String payload) {

        System.out.printf(">>>Payload: %s /n", payload);

        JsonObject jsonObj = Order.fromString(payload);
        Order order = Order.createOrder(jsonObj);
        
        String orderId = orderSvc.saveOrder(order);

        JsonObject resp = Json.createObjectBuilder()
            .add("orderId", orderId)
            .build();

        return ResponseEntity.ok(resp.toString());

    }

}