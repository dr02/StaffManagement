package com.flm.staff.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flm.staff.service.DoctorScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctorSchedule")
public class DoctorScheduleController {
	private final DoctorScheduleService doctorScheduleService;
	
	@PostMapping("/markUnavailable")
	public ResponseEntity<String> markUnavailability(@RequestBody String staffId, @RequestBody List<LocalDate> dates) {
		String result = doctorScheduleService.markUnavailbility(staffId, dates);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/markAvailable")
	public ResponseEntity<String> markAvailability(@RequestBody String staffId, @RequestBody List<LocalDate> dates) {
		doctorScheduleService.markAvailability(staffId, dates);
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("/isDoctorAvailable")
	public ResponseEntity<Boolean> checkDoctorAvailabilityOnDate(@RequestParam String staffId, @RequestParam String date) {
		boolean doctorAvailabilityOnDate = doctorScheduleService.checkDoctorAvailabilityOnDate(staffId, date);
		return ResponseEntity.ok(doctorAvailabilityOnDate);
	}
}
