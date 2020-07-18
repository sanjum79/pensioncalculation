package de.oev.api.pensioncalculation.service;

import de.oev.api.pensioncalculation.model.PensionResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class PensionCalculationService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${early.pension.year:1958}")
	private int earlyPensionYear;

	@Value("${early.pension.age:66}")
	private int earlyPensionAge;

	@Value("${normal.pension.age:67}")
	private int normalPensionAge;

	public PensionResponseDTO calculateGrossPension(int age, LocalDate workStart, int grossYearlySalary) {

		int workingYears = calculateWorkYearsTillPension(workStart, calculatePensionYear(age));
		logger.info("Number of years worked : " + workingYears);

		long monthlyPension = (grossYearlySalary / 1000) * workingYears;

		return new PensionResponseDTO(workStart, workingYears, monthlyPension) ;
	}

	protected int calculateWorkYearsTillPension(LocalDate workStartYear, LocalDate pensionYear) {
		return Period.between(workStartYear, pensionYear).getYears();
	}

	protected LocalDate calculatePensionYear(int age) {
		LocalDate yearOfBirth = calculateYearOfBirth(age);
		if(yearOfBirth.getYear() == earlyPensionYear) {
			return yearOfBirth.plusYears(earlyPensionAge);
		}
		return yearOfBirth.plusYears(normalPensionAge);
	}

	protected LocalDate calculateYearOfBirth(int age) {
		return LocalDate.now().minusYears(age);
	}
}
