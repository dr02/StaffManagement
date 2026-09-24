package com.flm.staff.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor_schedule")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_schedule_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "staff_id", nullable = false)
	private Staff staff;
	
	@Column(name = "unavailable_date", nullable = false)
	private LocalDate unavailableDate;

	public DoctorSchedule(Staff staff, LocalDate unavailableDate) {
		super();
		this.staff = staff;
		this.unavailableDate = unavailableDate;
	}
}
