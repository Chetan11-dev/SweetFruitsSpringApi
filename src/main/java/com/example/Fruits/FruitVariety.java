package com.example.Fruits;

import org.springframework.data.annotation.Id;

public class FruitVariety {
	@Id 
	public String id;
	public String fruit_id;
	public int popularityStars;
	public String name;
	@Override
	public String toString() {
		return "FruitVariety [id=" + id + ", fruit_id=" + fruit_id + ", popularityStars=" + popularityStars + ", name="
				+ name + "]";
	}
	public FruitVariety(String id, String fruit_id, int popularityStars, String name) {
		super();
		this.id = id;
		this.fruit_id = fruit_id;
		this.popularityStars = popularityStars;
		this.name = name;
	}
	
}
