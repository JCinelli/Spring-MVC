package dev.hotel.web.reservation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Reservation;
import dev.hotel.exception.HotelException;
import dev.hotel.service.ReservationService;

@RestController
@RequestMapping("reservations")
public class ReservationController {

	ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@PostMapping
	public ResponseEntity<?> creerReservation(@RequestBody @Valid CreerReservationRequestDto reservation,
			BindingResult resultatValidation) {

		if (!resultatValidation.hasErrors()) {

			Reservation reservationCree = reservationService.creerReservation(reservation.getDateDebut(),
					reservation.getDateFin(), reservation.getIdClient(), reservation.getListIdChambre());

			CreerReservationResponseDto reservationResponse = new CreerReservationResponseDto(reservationCree);

			return ResponseEntity.ok(reservationResponse);

		} else {

			return ResponseEntity.badRequest().body(" Tous les champs sont obligatoires !");

		}

	}

	@ExceptionHandler(HotelException.class)
	public ResponseEntity<List<String>> onHotelException(HotelException ex) {

		return ResponseEntity.badRequest().body(ex.getMessagesErreurs());

	}

}
