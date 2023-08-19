package Assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Assignment.model.Item;
import Assignment.model.Item.Brand;
import Assignment.repository.ItemRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder()
					.name("Small Nomad Bag")
					.createYear(2024)
					.brandFrom(Brand.DIOR)
					.price(new BigDecimal(4700)).build());

			repository.save(Item.builder()
					.name("B30 Sneaker")
					.createYear(2023)
					.brandFrom(Brand.DIOR)
					.price(new BigDecimal(1350)).build());

			repository.save(Item.builder()
					.name("Sweater")
					.createYear(2022)
					.brandFrom(Brand.DIOR)
					.price(new BigDecimal(4000)).build());

			repository.save(Item.builder()
					.name("Box Logo Hoodie")
					.createYear(2022)
					.brandFrom(Brand.SUPREME)
					.price(new BigDecimal(1100)).build());

			repository.save(Item.builder()
					.name("Box Logo Backpack")
					.createYear(2022)
					.brandFrom(Brand.SUPREME)
					.price(new BigDecimal(2100)).build());

			repository.save(Item.builder()
					.name("Tourist varsity \"Black\" jacket")
					.createYear(2022)
					.brandFrom(Brand.SUPREME)
					.price(new BigDecimal(1350)).build());

			repository.save(Item.builder()
					.name("Air Jordan 1 Retro High Off-White Chicago")
					.createYear(2022)
					.brandFrom(Brand.OFFWHITE)
					.price(new BigDecimal(7000)).build());

			repository.save(Item.builder()
					.name("Script Logo Opp Over Skate Hoodie")
					.createYear(2022)
					.brandFrom(Brand.OFFWHITE)
					.price(new BigDecimal(1065)).build());

			repository.save(Item.builder()
					.name("Nike Air Force 1 Low Off-White")
					.createYear(2023)
					.brandFrom(Brand.OFFWHITE)
					.price(new BigDecimal(2500)).build());

			repository.save(Item.builder()
					.name("Nike Air Force 1 Low Off-White Volt")
					.createYear(2024)
					.brandFrom(Brand.OFFWHITE)
					.price(new BigDecimal(2100)).build());
		};
	}
}
