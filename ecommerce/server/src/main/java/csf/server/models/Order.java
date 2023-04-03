package csf.server.models;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {

	private String orderId = "";
	private String name;
	private String email;
	private List<LineItem> lineItems = new LinkedList<>();

	//Constructor
	public Order() {
	}

	//Getters
	public String getOrderId() {
		return orderId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	//Setters
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	//Convert JsonObject to Order Object
	//1. Gets value from jsonobject to set into object
    //2. Unpack the jsonarray into list<object> and set list into object
	public static Order createOrder(JsonObject jsonObj) {

		Order order = new Order();

		//1.
		// if (jsonObj.containsKey("orderId") && (!jsonObj.isNull("orderId"))) {
		// 	order.setOrderId(jsonObj.getString("orderId"));
		// }
		order.setName(jsonObj.getString("name"));
		order.setEmail(jsonObj.getString("email"));
		
		//2.
		List<LineItem> lineItems = jsonObj.getJsonArray("lineItems")
			.stream()
			.map(i -> (JsonObject)i)
			.map(i -> LineItem.createLineItem(i))
			.toList();
		order.setLineItems(lineItems);

		return order;
	}

	//Convert JsonString to JsonObject
	public static JsonObject fromString(String jsonString) {
        Reader reader = new StringReader(jsonString);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }
	
	//Build and return a document object with key-value pairs 
    public Document toDocument() {
        Document doc = new Document();
        doc.put("orderId", getOrderId());
        doc.put("name", getName());
        doc.put("email", getEmail());
        doc.put("lineItems", getLineItems());
        return doc;    
    }
	
}