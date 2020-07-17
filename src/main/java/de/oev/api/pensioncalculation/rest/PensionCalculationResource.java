package de.oev.api.pensioncalculation.rest;

import de.oev.api.pensioncalculation.service.PensionCalculationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO;
@RequestMapping(value = "/api")
public class PensionCalculationResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static final String PARAM_AGE = "age";
	public static final String PARAM_WORK_START = "workStart";
	public static final String PARAM_GROSS_YEARLY_SALARY = "grossYearlySalary";

	private PensionCalculationService pensionCalculationService;

	public PensionCalculationResource(PensionCalculationService pensionCalculationService) {
		this.pensionCalculationService = pensionCalculationService;
	}

	@ApiOperation(value = "calculates gross monthly pension")
	@GetMapping(path = "/pension")
	public ResponseEntity<?> calculateGrossMonthlyPension(@RequestParam(name = PARAM_AGE) int age,
														  					@ApiParam(value = "workStart [yyyy-mm-dd]")
														  					@RequestParam(name = PARAM_WORK_START)
																		   @DateTimeFormat(iso = ISO.DATE)
														  					LocalDate workStart,
																		   @RequestParam(name = PARAM_GROSS_YEARLY_SALARY) int grossSalary) {


		if (age <= 16) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Age should be > 16.");
		}

		logger.info("Calculating gross monthly pension");

		return ResponseEntity.status(HttpStatus.OK)
							 .body(pensionCalculationService.calculateGrossPension(age, workStart, grossSalary));

	}
}
