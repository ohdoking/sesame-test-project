package com.sesame.appointment.sesametestproject.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sesame.appointment.sesametestproject.domain.Appointment;
import com.sesame.appointment.sesametestproject.error.AppointmentNotFoundException;
import com.sesame.appointment.sesametestproject.repository.AppointmentRepository;

/**
 * 
 * Appointment Controller
 * 
 * @author ohdoking
 *
 */


@RestController
@RequestMapping(value = "/appointment" )
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	/**
	 * 
	 * Retrieve all appointments that are scheduled between a date range and sorted by price.
	 * 
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public Page<Appointment> retrieveAllAppointments(Pageable pageable) {
		return appointmentRepository.findAll(pageable);
	}
	
	/**
	 * 
	 * Retrieve all appointments that are scheduled between a date range and sorted by price. a specific appointment from the database.
	 * 
	 * @param pageable
	 * @param startDate
	 * @param finishDate
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/search")
	public Page<Appointment> retrieveAllAppointmentsWithConditionDate(
			Pageable pageable, 
			@RequestParam(defaultValue = "19700101") String startDate, 
			@RequestParam(defaultValue = "29001230") String finishDate) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return appointmentRepository.findByAppointmentDateBetweenOrderByPriceDesc(new Timestamp(formatter.parse(startDate).getTime()), new Timestamp(formatter.parse(finishDate).getTime()), pageable);
	}
	
	
	/**
	 * 
	 * Retrieve a specific appointment from the database.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Appointment retrieveAppointment(@PathVariable Long id) {
		return appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException(id));
	}
	
	/**
	 * 
	 * Create new appointments
	 * 
	 * @param appointment
	 * @return
	 */
	@PostMapping
	public Appointment newAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	/**
	 * 
	 * Update the status of an existing appointment
	 * 
	 * @param newAppointment
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public Appointment replaceAppointment(Appointment newAppointment, @PathVariable Long id) {

		return appointmentRepository.findById(id)
			.map(appointment -> {
				
				appointment.setNameOfDoctor(newAppointment.getNameOfDoctor());
				appointment.setPrice(newAppointment.getPrice());
				appointment.setAppointmentDate(newAppointment.getAppointmentDate());
				appointment.setAppointmentDuration(newAppointment.getAppointmentDuration());
				appointment.setStatus(newAppointment.getStatus());
				
				return appointmentRepository.save(appointment);
			})
			.orElseGet(() -> {
				newAppointment.setId(id);
				return appointmentRepository.save(newAppointment);
			});
	}

	/**
	 * 
	 * Delete appointments from the database
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		appointmentRepository.deleteById(id);
	}
	
	
	
	
	
}
