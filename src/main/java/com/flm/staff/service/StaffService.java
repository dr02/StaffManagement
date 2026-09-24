package com.flm.staff.service;

import java.util.List;

import com.flm.staff.dto.RegisterStaffDTO;
import com.flm.staff.dto.StaffDetailsDTO;

public interface StaffService {
	StaffDetailsDTO registerStaff(RegisterStaffDTO registerStaffDTO);
	StaffDetailsDTO updateStaff(String staffId, RegisterStaffDTO registerStaffDTO);
	StaffDetailsDTO getStaffByStaffId(String staffId);
	List<StaffDetailsDTO> getStaffByStaffName(String name);
	String deleteStaffByStaffId(String staffId);
}
