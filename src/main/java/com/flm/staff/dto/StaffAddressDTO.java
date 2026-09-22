package com.flm.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffAddressDTO {
	private String landmark;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
