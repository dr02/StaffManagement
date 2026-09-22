package com.flm.staff.dto;

import java.time.LocalDate;

import com.flm.staff.model.Specialization;
import com.flm.staff.model.StaffType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStaffDTO {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String role;
	private String gender;
	private String email;
	private LocalDate dateOfJoining;
	private int experienceInYears;
	private StaffAddressDTO staffAddressDTO;
	private Specialization specialization;
	private StaffType staffType;
}
