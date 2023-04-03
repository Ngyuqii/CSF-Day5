package csf.server.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import csf.server.models.Order;

@Repository
public class OrderRepository {

    @Autowired
    private MongoTemplate template;

    //Method to convert order object to bson and insert into Mongodb
    //Datatbase ecommerce, Collection orders
    //db.orders.insert({Document})
    public void saveOrder (Order order) {
        Document doc = order.toDocument();
        System.out.printf(">>>DOC: %s", doc);
        template.insert(doc, "orders");
        
    }

}
