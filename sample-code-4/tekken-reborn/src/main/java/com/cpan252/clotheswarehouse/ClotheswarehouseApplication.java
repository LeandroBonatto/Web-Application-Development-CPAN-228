package com.cpan252.clotheswarehouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cpan252.clotheswarehouse.model.Item;
import com.cpan252.clotheswarehouse.repository.ItemRepository;

/**
 * mvn spring-boot:run does following steps
 * 1. mvn clean
 * 2. mvn compile
 * 3. mvn package
 * 4. java -jar target/tekkenreborn-0.0.1-SNAPSHOT.jar
 * 5. deploys jar to embedded tomcat
 */
@SpringBootApplication
public class ClotheswarehouseApplication {

	/**
	 * This is the main method that starts the application
	 * Spring Application Context is created here
	 * You can configure your application properties using @param args
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClotheswarehouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder()
					.id(01)
					.name("Tekken")
					.brand("Balenciaga")
					.yearOfCreation(2023)
					.price(3500)
					.build());

			repository.save(Item.builder()
					.id(02)
					.name("Tekken")
					.brand("Stone Island")
					.yearOfCreation(2023)
					.price(1980)
					.build());

			repository.save(Item.builder()
					.id(03)
					.name("Tekken")
					.brand("Dior")
					.yearOfCreation(2023)
					.price(1550)
					.build());
		};
	}
}
