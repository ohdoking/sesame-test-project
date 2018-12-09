package com.sesame.appointment.sesametestproject.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sesame.appointment.sesametestproject.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Page<Appointment> findByAppointmentDateBetweenOrderByPriceDesc(Timestamp startTime, Timestamp finishTime, Pageable pageable);

}
