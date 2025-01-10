package ee.mihkel.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeebipoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeebipoodApplication.class, args);
	}

}

// 1. Kontroller, Entity, Repository, Postgre-ga sidumine, PostMapping ja RuntimeException validatsioon
// 2. PathVariable/RequestParam/Body võrdlus, Kustutamine/võtmine, Patch, Muutmine, Veateadete haldamine, ResponseEntity, Kahe Entity sidumine, React algus
