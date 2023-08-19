package cpan228.finalprojectdistributioncenter;

import cpan228.finalprojectdistributioncenter.model.DistributionCenter;
import cpan228.finalprojectdistributioncenter.model.Item;
import cpan228.finalprojectdistributioncenter.repository.DistributionCenterRepository;
import cpan228.finalprojectdistributioncenter.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class FinalProjectDistributionCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectDistributionCenterApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository itemRepository, DistributionCenterRepository centerRepository) {
		return args -> {
			var distributionCenter1 = centerRepository
					.save(DistributionCenter.builder().name("location 1").latitude(10.0).longitude(10.9).build());
			itemRepository
					.save(Item.builder().name("Small Nomad Bag")
							.createYear(2024)
							.brandFrom(Item.Brand.DIOR)
							.price(new BigDecimal(4700))
							.quantity(10).distributionCenter(distributionCenter1).build());
			itemRepository
					.save(Item.builder()
							.name("B30 Sneaker")
							.createYear(2023)
							.brandFrom(Item.Brand.DIOR)
							.price(new BigDecimal(1350))
							.quantity(5).distributionCenter(distributionCenter1).build());
			itemRepository
					.save(Item.builder()
							.name("Sweater")
							.createYear(2022)
							.brandFrom(Item.Brand.DIOR)
							.price(new BigDecimal(4000)).distributionCenter(distributionCenter1).quantity(8).build());
		};
	}

	@Bean
	CommandLineRunner dataLoader2(ItemRepository itemRepository, DistributionCenterRepository centerRepository) {
		return args -> {
			var distributionCenter2 = centerRepository
					.save(DistributionCenter.builder().name("location 2").latitude(10.0).longitude(10.9).build());
			itemRepository
					.save(Item.builder()
							.name("Box Logo Hoodie")
							.createYear(2022)
							.brandFrom(Item.Brand.SUPREME)
							.price(new BigDecimal(1100)).quantity(20).distributionCenter(distributionCenter2).build());
			itemRepository
					.save(Item.builder()
							.name("Box Logo Backpack")
							.createYear(2022)
							.brandFrom(Item.Brand.SUPREME)
							.price(new BigDecimal(2100)).quantity(15).distributionCenter(distributionCenter2).build());
			itemRepository
					.save(Item.builder()
							.name("Tourist varsity \"Black\" jacket")
							.createYear(2022)
							.brandFrom(Item.Brand.SUPREME)
							.price(new BigDecimal(1350)).quantity(10).distributionCenter(distributionCenter2).build());
			itemRepository
					.save(Item.builder()
							.name("Sweater")
							.createYear(2022)
							.brandFrom(Item.Brand.DIOR)
							.price(new BigDecimal(4000)).distributionCenter(distributionCenter2).quantity(2).build());
		};
	}

	@Bean
	CommandLineRunner dataLoader3(ItemRepository itemRepository, DistributionCenterRepository centerRepository) {
		return args -> {
			var distributionCenter3 = centerRepository
					.save(DistributionCenter.builder().name("location 3").latitude(10.0).longitude(10.9).build());
			itemRepository
					.save(Item.builder()
							.name("Air Jordan 1 Retro High Off-White Chicago")
							.createYear(2022)
							.brandFrom(Item.Brand.OFFWHITE)
							.price(new BigDecimal(7000)).quantity(13).distributionCenter(distributionCenter3).build());
			itemRepository
					.save(Item.builder()
							.name("Script Logo Opp Over Skate Hoodie")
							.createYear(2022)
							.brandFrom(Item.Brand.OFFWHITE)
							.price(new BigDecimal(1550)).quantity(2).distributionCenter(distributionCenter3).build());
			itemRepository
					.save(Item.builder()
							.name("Nike Air Force 1 Low Off-White")
							.createYear(2024)
							.brandFrom(Item.Brand.OFFWHITE)
							.price(new BigDecimal(2250)).quantity(5).distributionCenter(distributionCenter3).build());
		};
	}

	@Bean
	CommandLineRunner dataLoader4(ItemRepository itemRepository, DistributionCenterRepository centerRepository) {
		return args -> {
			var distributionCenter4 = centerRepository
					.save(DistributionCenter.builder().name("location 4").latitude(10.0).longitude(10.9).build());
			itemRepository
					.save(Item.builder()
							.name("Jacket")
							.createYear(2023)
							.brandFrom(Item.Brand.STONEISLAND)
							.price(new BigDecimal(1100)).quantity(6).distributionCenter(distributionCenter4).build());
			itemRepository
					.save(Item.builder()
							.name("Sweater")
							.createYear(2025)
							.brandFrom(Item.Brand.STONEISLAND)
							.price(new BigDecimal(1550)).quantity(20).distributionCenter(distributionCenter4).build());
			itemRepository
					.save(Item.builder()
							.name("T-SHIRTS")
							.createYear(2023)
							.brandFrom(Item.Brand.STONEISLAND)
							.price(new BigDecimal(1150)).quantity(25).distributionCenter(distributionCenter4).build());
		};
	}
}
