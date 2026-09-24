package com.flm.staff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flm.staff.builder.StaffBuilder;
import com.flm.staff.builder.StaffDTOBuilder;
import com.flm.staff.dao.StaffRepository;
import com.flm.staff.dto.RegisterStaffDTO;
import com.flm.staff.dto.StaffDetailsDTO;
import com.flm.staff.exception.StaffNotFoundException;
import com.flm.staff.model.Staff;
import com.flm.staff.service.StaffService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

	private final StaffRepository staffRepository;
	
	@Override
	public StaffDetailsDTO registerStaff(RegisterStaffDTO registerStaffDTO) {
		Staff staff = StaffBuilder.buildStaffUsingRegisterStaffDTO(registerStaffDTO);
		Staff savedStaffDetails = staffRepository.save(staff);
		StaffDetailsDTO staffDetailsDto = StaffDTOBuilder.buildStaffDetailsDTOUsingStaff(savedStaffDetails);
		return staffDetailsDto;
	}

	@Override
	public StaffDetailsDTO updateStaff(String staffId, RegisterStaffDTO registerStaffDTO) {
		Long id = Long.parseLong(staffId);
		Optional<Staff> optional = findStaffByStaffId(id);
		Staff staff = optional.orElseThrow(() -> new StaffNotFoundException("Staff ID: "+staffId+" not found."));
		
		boolean existsByPhoneNumberAndIdNot = staffRepository.existsByPhoneNumberAndStaffIdNot(registerStaffDTO.getPhoneNumber(), staff.getStaffId());
		if(existsByPhoneNumberAndIdNot) throw new IllegalArgumentException("Phone Number "+registerStaffDTO.getPhoneNumber()+" is already registered.");
		
		boolean emailTakenBySomeoneElse = staffRepository.isEmailTakenBySomeoneElse(registerStaffDTO.getEmail(), staff.getStaffId());
		if(emailTakenBySomeoneElse) throw new IllegalArgumentException("Email "+registerStaffDTO.getEmail()+" is already registered.");
		
		Staff updatedStaffDetails = StaffBuilder.updateStaffUsingRegisterStaffDTO(staff, registerStaffDTO);
		Staff updatedStaff = staffRepository.save(updatedStaffDetails);
		
		return StaffDTOBuilder.buildStaffDetailsDTOUsingStaff(updatedStaff);
	}

	private Optional<Staff> findStaffByStaffId(Long id) {
		Optional<Staff> optional = staffRepository.findById(id);
		return optional;
	}

	@Override
	public StaffDetailsDTO getStaffByStaffId(String staffId) {
		Long id = Long.parseLong(staffId);
		Optional<Staff> optional = findStaffByStaffId(id);
		Staff staff = optional.orElseThrow(() -> new StaffNotFoundException("Staff ID: "+staffId+" not found."));
		return StaffDTOBuilder.buildStaffDetailsDTOUsingStaff(staff);
	}

	@Override
	public List<StaffDetailsDTO> getStaffByStaffName(String name) {
		List<Staff> staffList = staffRepository.findStaffByName(name);
		return staffList
				.stream()
				.map(StaffDTOBuilder::buildStaffDetailsDTOUsingStaff)
				.toList();
	}

	@Override
	public String deleteStaffByStaffId(String staffId) {
		Long id = Long.parseLong(staffId);
		Optional<Staff> optional = findStaffByStaffId(id);
		Staff staff = optional.orElseThrow(() -> new StaffNotFoundException("Staff ID: "+staffId+" not found."));
		staff.setIsEmployeeActive(false);
		staff.setCanLogin(false);
		Staff updatedStaff = staffRepository.save(staff);
		String fullName = new StringBuilder().append(updatedStaff.getFirstname()).append(updatedStaff.getLastname()).toString();
		return fullName;
	}

}
