package main;

import com.example.housebooking.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.housebooking.dto.ReservationRequest;
import com.example.housebooking.model.Reservation;
import com.example.housebooking.repository.ReservationRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(classes = HousebookingApplicationTests.class)
public class HousebookingApplicationTests {

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationService reservationService;

	public void ReservationServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void createReservation() {
		ReservationRequest request = new ReservationRequest();
		request.setUserId("user1");
		request.setHouseId("house1");
		request.setDiscountCode("DISCOUNT10");

		Reservation reservation = new Reservation();
		reservation.setId(1L);
		reservation.setUserId("user1");
		reservation.setHouseId("house1");
		reservation.setDiscountCode("DISCOUNT10");

		when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

		Reservation createdReservation = reservationService.createReservation(request);

		assertNotNull(createdReservation);
		assertEquals("user1", createdReservation.getUserId());
		assertEquals("house1", createdReservation.getHouseId());
		assertEquals("DISCOUNT10", createdReservation.getDiscountCode());
	}

}
