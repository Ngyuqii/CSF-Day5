package csf.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class LineItem {

	private String item;
	private Integer quantity;

	//Constructor
	public LineItem() {
	}
	
	//Getters
	public String getItem() {
		return item;
	}
	public Integer getQuantity() {
		return quantity;
	}

	//Setters
	public void setItem(String item) {
		this.item = item;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	//Convert JsonObject to LineItem Object
	public static LineItem createLineItem(JsonObject j) {
		LineItem lineItem = new LineItem();
		lineItem.setItem(j.getString("item"));
		lineItem.setQuantity(j.getInt("quantity"));
		return lineItem;
	}

	//Convert LineItem Object to JsonObject
	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("item", getItem())
			.add("quantity", getQuantity())
			.build();
	}

}