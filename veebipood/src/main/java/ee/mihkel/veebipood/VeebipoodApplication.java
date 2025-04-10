package ee.mihkel.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
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
// 5. Kümnevõistlus. Rendipood
// 6. Rendipood jätk. Swagger.
// 7. Card-game.
// 8. Frontend: Tõlge, 1 peale sattumine (URL parameeter), muutmine. Early return. Refide asemel Objecti sisestamine. LocalStorage
// 9. CSS, Order (kokkuarvutamine, uus mudel - kogus ostukorvis) ja Person. onChange abil vormi sisestamine.
// 10. DTO - Data Transfer Object, ModelMapper, Beanid. Unit testid (rental)
// 11. Auth:
// a)+Kontrolleris võimekuse tegemine emaili passwordi vastu võtmiseks
// b)+Emaili ja Passwordi mudeli saatmine Frondist Backi, Stringi tagastamine
// c)+Stringi salvestame sessionStorage-sse, et jääks alles märk, et on sisselogitud
// d)+Context Reactis ---> globaalne muutuja
// +Contexti paremaks arusaamiseks ka ostukorvi kogusumma Frondis Navbaris
// e)+Nuppude ära peitmine. +URLde ära peitmine.
// f)+Ei tagasta enam Token123 vaid JSON WEB TOKENI
// g)+Frondis peab hakkama päringuid tegema, kas on ikka õige Token
// +Paneme peale API OTSPUNKTIDE KAITSE ---> pikk ja keeruline
// h)+API otspunktidele paneme Authorization peale
// i)+Rollid -> admin / tavakasutaja
// j)+Paneme andmebaasis igale Personile külge, kas on admin või mitte
// k)+Paneme igale API otspunktile külge, kas  tavainimene või admin saab ligi
// l)+Frondis nuppudele kas admin või tavainimene saab ligi
// m)+Tellimust tehes ei pea enam isikut saatma

// Endale: Bootstrapis as= on punane

// +Rakendusesest välja API päringud: RestTemplate
// +Front-end Context (redux)

// 5. T 21.01  13.00
// 6. N 23.01  13.00
// 7. E 27.01  13.00
// 8. K 29.01  13.00
// 9. E 03.02  13.00
//10. K 05.02  13.00
//xx. K 12.02  -----  T ja K ei saa
//11. N 13.02  9.00 -> Auth
//12. E 17.02  9.00 -> Auth
//13. K 19.02  9.00 -> Auth
//14. K 26.02 13.00 -> Auth   profiil, tellimused, rollid
//15. E 03.03 13.00 -> ROLLIDE VAHETUS. RestTemplate -> päringud rakendusest välja (pakiautomaadid, makse), Makse lõpetamine
//16. N 06.03 14.00 CRON, Cache, Shell-script.
//17. E 10.03 14.30-16.30 Muutujate väljatõste config faili. Profiilid. Email. Front-endis TypeScript errorid.
//18. K 26.03 14.00-16.30 Redis. Bowling. lõpuprojekti esitlemine
// allkirjalehele 26.03 asemel 12.03

// Näited päringud rakendusest välja:
// Makse->Kas on makstud
// Pakiautomaadid
// Tarnija

// Smart-ID
// Eesti.ee -> on laps