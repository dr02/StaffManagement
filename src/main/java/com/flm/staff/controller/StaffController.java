package com.flm.staff.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flm.staff.dto.RegisterStaffDTO;
import com.flm.staff.dto.StaffDetailsDTO;
import com.flm.staff.service.StaffService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
	private final StaffService staffService;
	
	@PostMapping("/register")
	public ResponseEntity<StaffDetailsDTO> registerNewStaffUser(@RequestBody RegisterStaffDTO registerStaffDTO) {
		StaffDetailsDTO staffDetails = staffService.registerStaff(registerStaffDTO);
		return new ResponseEntity<StaffDetailsDTO>(staffDetails, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{staffId}")
	public ResponseEntity<StaffDetailsDTO> updateStaff(@PathVariable String staffId, @RequestBody RegisterStaffDTO registerStaffDTO) {
		StaffDetailsDTO updatedStaffDetailsDTO = staffService.updateStaff(staffId, registerStaffDTO);
		return new ResponseEntity<StaffDetailsDTO>(updatedStaffDetailsDTO, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{staffId}")
	public ResponseEntity<StaffDetailsDTO> getStaffByStaffId(@PathVariable String staffId) {
		StaffDetailsDTO staffDetailsDTO = staffService.getStaffByStaffId(staffId);
		return ResponseEntity.ok(staffDetailsDTO);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<StaffDetailsDTO>> getStaffByStaffName(@RequestParam String name) {
		List<StaffDetailsDTO> staffList = staffService.getStaffByStaffName(name);
		return ResponseEntity.ok(staffList);
	}
	
	@DeleteMapping("/{staffId}")
	public ResponseEntity<String> deleteStaff(@PathVariable String staffId) {
		String deletedStaffName = staffService.deleteStaffByStaffId(staffId);
		return ResponseEntity.ok(deletedStaffName);
	}
}
