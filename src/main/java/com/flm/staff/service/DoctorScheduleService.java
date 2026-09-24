package com.flm.staff.service;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleService {
	boolean markAvailability(String staffId, List<LocalDate> dates);
	String markUnavailbility(String staffId, List<LocalDate> dates);
	boolean checkDoctorAvailabilityOnDate(String staffId, String date);
}
