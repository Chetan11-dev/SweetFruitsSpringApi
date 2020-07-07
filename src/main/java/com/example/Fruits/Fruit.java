package com.example.Fruits;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Fruit {
	@Id 
	public String id;
	public String name;
	public String origin;
	public String largestCountry;
	public double productionInBillions;

	@Override
	public String toString() {
		return "Fruit [id=" + id + ", name=" + name + ", origin=" + origin + ", largestCountry=" + largestCountry
				+ ", productionInBillions=" + productionInBillions + "]";
	}

	public Fruit(String name, String origin, String largestCountry, double productionInBillions) {
		this.name = name;
		this.largestCountry = largestCountry;
		this.origin = origin;
		this.productionInBillions = productionInBillions;
	}
}
