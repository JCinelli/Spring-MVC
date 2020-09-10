package dev.hotel.web.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreerReservationRequestDto {

	@NotNull
	private LocalDate dateDebut;

	@NotNull
	private LocalDate dateFin;

	@NotNull
	private UUID idClient;

	@NotEmpty
	private List<UUID> listIdChambre;

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the idClient
	 */
	public UUID getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(UUID idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the listIdChambre
	 */
	public List<UUID> getListIdChambre() {
		return listIdChambre;
	}

	/**
	 * @param listIdChambre the listIdChambre to set
	 */
	public void setListIdChambre(List<UUID> listIdChambre) {
		this.listIdChambre = listIdChambre;
	}

}
