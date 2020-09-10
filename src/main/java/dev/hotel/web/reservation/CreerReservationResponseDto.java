package dev.hotel.web.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;

public class CreerReservationResponseDto extends CreerReservationRequestDto {

	private UUID uuid;

	public CreerReservationResponseDto(Reservation reservation) {
		this.uuid = reservation.getUuid();
		this.setDateDebut(reservation.getDateDebut());
		this.setDateFin(reservation.getDateFin());
		this.setIdClient(reservation.getClient().getUuid());

		List<UUID> listIdChambre = new ArrayList<>();

		for (Chambre chambre : reservation.getChambres()) {
			listIdChambre.add(chambre.getUuid());
		}

		this.setListIdChambre(listIdChambre);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
