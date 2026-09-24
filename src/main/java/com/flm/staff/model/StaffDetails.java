package com.flm.staff.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="staff_details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_details_id")
	private Long staffDetailsId;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "reset_otp")
	private Boolean resetOtp;
	
	@Column(name = "otp_expiry_time")
	private LocalTime otpExpiryTime;
	
	@Builder.Default
	@Column(name = "required_password_reset")
	private Boolean requiredPasswordReset = Boolean.FALSE;
}
