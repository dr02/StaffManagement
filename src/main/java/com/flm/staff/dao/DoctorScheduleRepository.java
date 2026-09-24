package com.flm.staff.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.flm.staff.model.DoctorSchedule;
import com.flm.staff.model.Staff;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long>{
	
	@Modifying
	@Transactional
	@Query("DELETE FROM DoctorSchedule ds WHERE ds.staff.staffId = :staffId AND ds.unavailableDate IN :dates")
	void deleteUnavailableDatesByStaffIdAndDates(@Param("staffId") Long staffId, @Param("dates") List<LocalDate> dates);
	
	@Query("SELECT ds.unavailableDate FROM DoctorSchedule ds WHERE ds.staff.staffId = :staffId")
	Set<LocalDate> getUnavailableDatesUsingStaffId(@Param("staffId") String staffId);
	
	@Query("SELECT ds.staff FROM DoctorSchedule ds WHERE ds.staff.staffId = :staffId")
	Optional<Staff> getStaffByStaffId(@Param("staffId") String staffId);
	
	boolean existsByStaffAndUnavailableDate(Staff staff, LocalDate date);
}
