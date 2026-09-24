package com.flm.staff.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.flm.staff.dao.DoctorScheduleRepository;
import com.flm.staff.exception.StaffNotFoundException;
import com.flm.staff.model.DoctorSchedule;
import com.flm.staff.model.Staff;
import com.flm.staff.model.StaffType;
import com.flm.staff.service.DoctorScheduleService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

	private final DoctorScheduleRepository doctorScheduleRepository;
	
	@Override
	public boolean markAvailability(String staffId, List<LocalDate> dates) {
		Long id = Long.parseLong(staffId);
		doctorScheduleRepository.deleteUnavailableDatesByStaffIdAndDates(id, dates);
		return true;
	}

	@Override
	public String markUnavailbility(String staffId, List<LocalDate> dates) {
		Staff staff = doctorScheduleRepository.getStaffByStaffId(staffId).get();
		Set<LocalDate> unavailableDates = doctorScheduleRepository.getUnavailableDatesUsingStaffId(staffId);
		List<LocalDate> filteredDates = dates
		.stream()
		.filter(date -> (!date.isBefore(LocalDate.now())))
		.filter(date -> (!unavailableDates.contains(date)))
		.toList();
		
		List<DoctorSchedule> newScheduleList = new ArrayList<DoctorSchedule>();
		
		for(LocalDate date: filteredDates) {
			DoctorSchedule ds = new DoctorSchedule(staff, date);
			newScheduleList.add(ds);
		}
		
		doctorScheduleRepository.saveAllAndFlush(newScheduleList);
		
		return "Successfully marked new "+filteredDates.size()+" dates.";
	}

	@Override
	public boolean checkDoctorAvailabilityOnDate(String staffId, String date) {
		Optional<Staff> optional = doctorScheduleRepository.getStaffByStaffId(staffId);
		Staff staff = optional.orElseThrow(() -> new StaffNotFoundException("Staff with ID: "+staffId+" not found."));
		
		if(!StaffType.NON_DOCTOR.equals(staff.getStaffType())) throw new IllegalArgumentException("Staff with ID: "+staffId+"is not a doctor.");
		
		return doctorScheduleRepository.existsByStaffAndUnavailableDate(staff, LocalDate.parse(date));
	}

}
