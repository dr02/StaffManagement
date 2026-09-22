package com.flm.staff.builder;

import java.security.SecureRandom;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.flm.staff.dto.RegisterStaffDTO;
import com.flm.staff.model.Staff;
import com.flm.staff.model.StaffAddress;
import com.flm.staff.model.StaffDetails;

public class StaffBuilder {
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static Staff buildStaffUsingRegisterStaffDTO(RegisterStaffDTO registerStaffDTO) {
		return Staff.builder()
				.firstname(registerStaffDTO.getFirstName())
				.lastname(registerStaffDTO.getLastName())
				.phoneNumber(registerStaffDTO.getPhoneNumber())
				.role((registerStaffDTO.getRole()).toUpperCase())
				.staffType(registerStaffDTO.getStaffType())
				.gender(registerStaffDTO.getGender())
				.dateOfJoining(registerStaffDTO.getDateOfJoining())
				.experienceInYears(registerStaffDTO.getExperienceInYears())
				.specialization(registerStaffDTO.getSpecialization())
				.isEmployeeActive(true)
				.canLogin(true)
				.staffDetails(buildStaffDetailsUsingRegisterStaffDTO(registerStaffDTO))
				.staffAddress(buildStaffAddressUsingRegisterStaffDTO(registerStaffDTO))
				.build();
	}
	
	private static StaffDetails buildStaffDetailsUsingRegisterStaffDTO(RegisterStaffDTO registerStaffDTO) {
		String password = generateTemporaryPassword();
		threadLocal.set(password);
		return StaffDetails.builder()
				.email(registerStaffDTO.getEmail())
				.password(passwordEncoder.encode(password))
				.requiredPasswordReset(true)
				.build();
	}
	
	private static StaffAddress buildStaffAddressUsingRegisterStaffDTO(RegisterStaffDTO registerStaffDTO) {
		StaffAddress staffAddress = new StaffAddress();
		BeanUtils.copyProperties(registerStaffDTO, staffAddress);
		return staffAddress;
	}
	
	private static String generateTemporaryPassword() {
		int randomNumber = new SecureRandom().nextInt(1000000);
		return String.valueOf(randomNumber);
	}
	
	public static Staff updateStaffUsingRegisterStaffDTO(Staff staff, RegisterStaffDTO registerStaffDTO) {
		staff.setFirstname(registerStaffDTO.getFirstName());
		staff.setLastname(registerStaffDTO.getLastName());
		staff.setPhoneNumber(registerStaffDTO.getPhoneNumber());
		staff.setStaffType(registerStaffDTO.getStaffType());
		staff.setRole(registerStaffDTO.getRole().toUpperCase());
		staff.setGender(registerStaffDTO.getGender());
		staff.setSpecialization(registerStaffDTO.getSpecialization());
		staff.setDateOfJoining(registerStaffDTO.getDateOfJoining());
		staff.setExperienceInYears(registerStaffDTO.getExperienceInYears());

		BeanUtils.copyProperties(registerStaffDTO.getStaffAddressDTO(),staff.getStaffAddress());

		staff.getStaffDetails().setEmail(registerStaffDTO.getEmail());
		
		return staff;
	}
}
