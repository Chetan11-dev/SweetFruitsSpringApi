package com.example.Fruits;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * My custom exception class.
 */
class NoFruitVarietyException extends Exception {
	public NoFruitVarietyException(String fid) {
		super("No fruit varieties found for fruit id:" + fid);
	}
}

@RestController
@RequestMapping("/fruits")
public class FruitController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostMapping("/")
	public String addFruit(@RequestBody Fruit fruit) {
		Fruit a = mongoTemplate.save(fruit);
		return "Added" + a.toString();
	}

	@GetMapping("/fruitvariety/{fid}")
	List<FruitVariety> getFruitVariety(@PathVariable String fid) throws NoFruitVarietyException {

		Query query = new Query();
		query.addCriteria(Criteria.where("fruit_id").is(fid));

		List<FruitVariety> fs = mongoTemplate.find(query, FruitVariety.class);
		if (fs == null) {
			throw new NoFruitVarietyException(fid);
		}

		return fs;
	}

	@PostMapping("/fruitvariety/{fid}")
	public String addFruitVariety(@RequestBody FruitVariety fruitVariety, @PathVariable String fid) {
		fruitVariety.fruit_id = fid;
		FruitVariety a = mongoTemplate.save(fruitVariety);
		return "Added" + a.toString();
	}

	@DeleteMapping("/fruitvariety/{fid}/{vid}")
	public String removeFruitVariety(@PathVariable String fid, @PathVariable String vid) {
		if (getFruit(fid) == null) {
			return "No Fruit with id: " + vid;
		}

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(vid));
		if (mongoTemplate.findOne(query, FruitVariety.class) == null) {
			return "No fruit variety exists with following fruid id:" + vid;
		}
		mongoTemplate.remove(query, FruitVariety.class);
		return "Deleted fruit variety with id: " + vid;
	}

	@DeleteMapping("/{id}")
	public String removeFruit(@PathVariable String id) {
		if (getFruit(id) == null) {
			return "No Fruit with id: " + id;
		}

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		mongoTemplate.remove(query, Fruit.class);
		return "Deleted fruit with " + id;
	}

	@PutMapping("/{id}")
	Fruit replaceFruit(@RequestBody Fruit f, @PathVariable String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));

		Update update = new Update();
		if (f.name != null) {

			update.set("name", f.name);
		}
		if (f.productionInBillions != 0) {
			update.set("productionInBillions", f.productionInBillions);
		}
		if (f.origin != null) {

			update.set("origin", f.origin);

		}
		if (f.largestCountry != null) {

			update.set("largestCountry", f.largestCountry);
		}
		mongoTemplate.findAndModify(query, update, Fruit.class);
		return getFruit(id);
	}

	@GetMapping("/{id}")
	Fruit getFruit(@PathVariable String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Fruit.class);
	}

	@GetMapping("/")
	List<Fruit> getFruits() {
		return mongoTemplate.findAll(Fruit.class);
	}

}
