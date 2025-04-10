package ee.mihkel.movie_store.service;

import ee.mihkel.movie_store.entity.Film;
import ee.mihkel.movie_store.entity.FilmType;
import ee.mihkel.movie_store.entity.Person;
import ee.mihkel.movie_store.entity.Rental;
import ee.mihkel.movie_store.model.FilmRentalDTO;
import ee.mihkel.movie_store.repository.FilmRepository;
import ee.mihkel.movie_store.repository.PersonRepository;
import ee.mihkel.movie_store.repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RentalServiceTest {

    // https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea

//    @Autowired
//    RentalService rentalService;

    @Mock
    RentalRepository rentalRepository;

    @Mock
    FilmRepository filmRepository;

    @InjectMocks
    RentalService rentalService;

    Film film = new Film();
    List<FilmRentalDTO> filmRentalDTOs = new ArrayList<>();

    Rental rental = new Rental();

    Person person = new Person();

    @BeforeEach
    void setUp() {
        when(filmRepository.findById(any())).thenReturn(Optional.of(film));

        FilmRentalDTO filmRentalDTO = new FilmRentalDTO();
        filmRentalDTO.setDays(5);
        filmRentalDTOs.add(filmRentalDTO);

        when(rentalRepository.save(any())).thenReturn(rental);
    }

    @Test
    void givenFilmIsOldAndRentedForFiveDays_whenRentalStarts_thenInitialFeeIs3() {
        film.setType(FilmType.OLD);

        rentalService.saveRental(filmRentalDTOs, new Person(), 0);
        assertEquals(3, rental.getInitialFee());
    }

    @Test
    void givenFilmIsNewAndRentedForFiveDays_whenRentalStarts_thenInitialFeeIs20() {
        film.setType(FilmType.NEW);

        rentalService.saveRental(filmRentalDTOs, new Person(), 0);
        assertEquals(20, rental.getInitialFee());
    }


    @Test
    void givenFilmIsAlreadyRented_whenRentalStarts_thenFilmRentedExceptionIsThrown() {
        film.setDaysRented(1);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> rentalService.checkIfAllAvailable(filmRentalDTOs,person,0));
        assertTrue(exception.getMessage().startsWith("ERROR_FILM_RENTED"));
    }

    @Test
    void givenNotEnoughBonusPoints_whenRentalStarts_thenNotEnoughExceptionIsThrown() {
        film.setType(FilmType.NEW);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> rentalService.checkIfAllAvailable(filmRentalDTOs,person,1));
        assertTrue(exception.getMessage().startsWith("ERROR_NOT_ENOUGH_BONUS_POINTS"));
    }

    @Test
    void givenAllCorrect_whenRentalStarts_thenNoExceptionIsThrown() {
        film.setType(FilmType.NEW);

        person.setBonusPoints(50);
        assertDoesNotThrow(() -> rentalService.checkIfAllAvailable(filmRentalDTOs,person,2));
    }

    // TODO: KOJU
    @Test
    void calculateLateFee() {

    }

    // given_when_then
    @Test
    void givenFilmIsNewAndLateForOneDay_whenFilmIsReturned_thenLateFeeIs4() {
        film.setType(FilmType.NEW);
        film.setDaysRented(4);
        double filmCost = rentalService.getFilmLateFee(film, 5);
        assertEquals(4.0, filmCost);
    }

    @Test
    void givenFilmIsOldAndLateForFourDays_whenFilmIsReturned_thenLateFeeIs12() {
        film.setType(FilmType.OLD);
        film.setDaysRented(3);
        double filmCost = rentalService.getFilmLateFee(film, 7);
        assertEquals(12.0, filmCost);
    }
}