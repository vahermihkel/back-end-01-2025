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
// 3. Frontend: Mudelid, Kustutamine, Kui kategooriat kustutame, siis toode on sees exception.
// Teeme frontendis võimaluse URL-de vahel liikuda, Bootstrap: Navbar+Nupud, MUI, Toastify.  Lisamine
// 4. Renderduste selgitused. Tabel MaintainProducts. Muuda Active. Custom Repository päringud, Pagination
// 5. Kümnevõistlus. Frontend: Tõlge, 1 peale sattumine (URL parameeter).


// 5. T 21.01  13.00
// 6. N 23.01  13.00
// 7. E 27.01  13.00
// 8. N 30.01  13.00
// 9. E 03.02  13.00
//10. K 05.02  13.00
