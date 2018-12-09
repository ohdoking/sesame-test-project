package com.sesame.appointment.sesametestproject.domain;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Appointment")
public class Appointment {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name_of_doctor")
	private String nameOfDoctor;
	
	//- (Available/Booked)
	private String status;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="appointment_date")
	private Date appointmentDate;
	
	@Column(name="appointment_duration")
	private int appointmentDuration;
	
	private double price;
	
	public Appointment() {
		
	}
	
	public Appointment(String nameOfDoctor, String status, Date appointmentDate, int appointmentDuration,
			double price) {
		super();
		this.nameOfDoctor = nameOfDoctor;
		this.status = status;
		this.appointmentDate = appointmentDate;
		this.appointmentDuration = appointmentDuration;
		this.price = price;
	}
}

