package com.sesame.appointment.sesametestproject.error;
public class AppointmentNotFoundException extends RuntimeException {

	public AppointmentNotFoundException(Long id) {
		super("Could not find Appointment " + id);
	}
}