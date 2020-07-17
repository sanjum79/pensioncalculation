package de.oev.api.pensioncalculation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PensionResponseDTO {
	private LocalDate workStart;
	private long yearsWorked;
	private long monthlyGrossPension;
}
