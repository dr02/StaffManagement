package com.flm.staff.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flm.staff.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{
	@Query("SELECT s FROM Staff s WHERE "
			+ "(:name IS NULL OR :name = '') OR "
			+ "LOWER(s.firstname) LIKE LOWER(CONCAT('%',:name,'%')) OR "
			+ "LOWER(s.lastname) LIKE LOWER(CONCAT('%',:name,'%')) OR "
			+ "LOWER(CONCAT(s.firstname,' ',s.lastname)) LIKE LOWER(CONCAT('%',:name,'%'))" )
	List<Staff> findStaffByName(String name);
	
	boolean existsByPhoneNumberAndStaffIdNot(String phoneNumber, Long staffId);
	
	@Query("SELECT COUNT(s)>0 FROM Staff s WHERE s.staffDetails.email = :email AND s.staffId != :id")
	boolean isEmailTakenBySomeoneElse(@Param("email") String email, @Param("id") Long id);
}
