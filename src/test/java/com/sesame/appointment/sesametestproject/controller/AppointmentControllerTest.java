package com.sesame.appointment.sesametestproject.controller;

import java.sql.Date;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sesame.appointment.sesametestproject.domain.Appointment;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppointmentControllerTest {
	
	String api = "/api/v1/sesame/";
	
	@Autowired
    private WebApplicationContext wac;
	
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void getAppointmentTest() throws Exception {
        mockMvc
        	.perform(MockMvcRequestBuilders.get(api + "appointment"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void createAppointmentTest() throws Exception {
    	
        mockMvc
        	.perform(MockMvcRequestBuilders
        				.post(api + "appointment")
        				.contentType(MediaType.APPLICATION_JSON)
        				.param("appointmentDate", "2018-12-17")
        				.param("appointmentDuration", "10")
        				.param("nameOfDoctor", "Davide")
        				.param("price", "100.0")
        				.param("status", "booked")
        				.sessionAttr("appointment", new Appointment())
    				)
        	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    void updateAppointmentTest() throws Exception {
    	
        mockMvc
        	.perform(MockMvcRequestBuilders
        				.put(api + "appointment/10")
        				.contentType(MediaType.APPLICATION_JSON)
        				.param("appointmentDate", "2018-12-12")
        				.param("appointmentDuration", "10")
        				.param("nameOfDoctor", "Davide")
        				.param("price", "100.0")
        				.param("status", "booked")
        				.sessionAttr("appointment", new Appointment())
    				)
        	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @Disabled
    void deleteAppointmentTest() throws Exception {
           
        mockMvc
        	.perform(MockMvcRequestBuilders
	        			.delete(api + "appointment/5")
	        			.sessionAttr("appointment", new Appointment())
        			)
        	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    


}
