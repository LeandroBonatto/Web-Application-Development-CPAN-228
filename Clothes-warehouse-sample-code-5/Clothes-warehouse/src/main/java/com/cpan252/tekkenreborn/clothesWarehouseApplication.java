package com.cpan252.tekkenreborn;

import com.cpan252.tekkenreborn.model.Item;
import com.cpan252.tekkenreborn.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * mvn spring-boot:run does following steps
 * 1. mvn clean
 * 2. mvn compile
 * 3. mvn package
 * 4. java -jar target/tekkenreborn-0.0.1-SNAPSHOT.jar
 * 5. deploys jar to embedded tomcat
 */
@SpringBootApplication
public class clothesWarehouseApplication {

	/**
	 * This is the main method that starts the application
	 * Spring Application Context is created here
	 * You can configure your application properties using @param args
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(clothesWarehouseApplication.class, args);
	}

	@Component
	public class DatabaseLoader implements CommandLineRunner {

		private final ItemRepository itemRepository;

		@Autowired
		public DatabaseLoader(ItemRepository itemRepository) {
			this.itemRepository = itemRepository;
		}

		@Override
		public void run(String... args) {
			Item item1 = new Item();
			Item item2 = new Item();
			Item item3 = new Item();

			itemRepository.save(item1);
			itemRepository.save(item2);
			itemRepository.save(item3);
		}
	}
}
