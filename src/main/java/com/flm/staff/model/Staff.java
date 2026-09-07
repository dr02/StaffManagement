package com.flm.staff.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private Long staffId;
	
	private String firstname;
	
	private String lastname;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "staff_type")
	private StaffType staffType;
	
	private String gender;
	
	private String role;
	
	@Enumerated(EnumType.STRING)
	private Specialization specialization;
	
	@Column(name = "date_of_joining")
	private LocalDate doj;
	
	@Column(name = "experience_in_years")
	private String experienceInYears;
	
	@Column(name = "can_login")
	private Boolean canLogin;
	
	@Column(name = "is_employee_active")
	private Boolean isEmployeeActive;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_details_id")
	private StaffDetails staffDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_address_id")
	private StaffAddress staffAddress;
}
