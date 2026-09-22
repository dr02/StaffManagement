package com.flm.staff.builder;

import com.flm.staff.dto.StaffAddressDTO;
import com.flm.staff.dto.StaffDetailsDTO;
import com.flm.staff.model.Staff;
import com.flm.staff.model.StaffAddress;

public class StaffDTOBuilder {
	
	public static StaffDetailsDTO buildStaffDetailsDTOUsingStaff(Staff staff) {
		return StaffDetailsDTO.builder()
				.staffId(String.valueOf(staff.getStaffId()))
				.firstName(staff.getFirstname())
				.lastName(staff.getLastname())
				.phoneNumber(staff.getPhoneNumber())
				.email(staff.getStaffDetails().getEmail())
				.role(staff.getRole())
				.gender(staff.getGender())
				.dateOfJoining(staff.getDateOfJoining())
				.experienceInYears(staff.getExperienceInYears())
				.specialization(staff.getSpecialization())
				.staffType(staff.getStaffType())
				.isEmployeeActive(staff.getIsEmployeeActive())
				.canLogin(staff.getCanLogin())
				.staffAddressDTO(buildStaffAddressDTO(staff.getStaffAddress()))
				.build();
	}
	
	private static StaffAddressDTO buildStaffAddressDTO(StaffAddress staffAddress) {
		return StaffAddressDTO.builder()
				.state(staffAddress.getState())
				.city(staffAddress.getCity())
				.landmark(staffAddress.getLandmark())
				.country(staffAddress.getCity())
				.pincode(staffAddress.getPincode())
				.build();
	}
}
