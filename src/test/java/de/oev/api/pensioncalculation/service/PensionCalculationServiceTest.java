package de.oev.api.pensioncalculation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class PensionCalculationServiceTest {

	private final int TEST_AGE_NORMAL = 41;
	private final int TEST_AGE_EARLY = 62;
	private final int TEST_GROSS_YEARLY_SALARY = 50000;
	private final LocalDate TEST_WORK_START = LocalDate.of(1998, 3, 25);
	private final LocalDate TEST_WORK_START_2008 = LocalDate.of(2008, 1, 1);
	private final LocalDate TEST_PENSION_YEAR = LocalDate.of(2046, 1, 1);

	@Autowired
	private PensionCalculationService pensionCalculationService;

	@Test
	public void testCalculateWorkingYearsTillPension() {
		int numberOfWorkYears = pensionCalculationService.calculateWorkYearsTillPension(TEST_WORK_START_2008,
																						  TEST_PENSION_YEAR);
		assertEquals(numberOfWorkYears, 38);
	}

	@Test
	public void shouldCalculateGrossPension() {
		long grossMonthlyPension = pensionCalculationService.calculateGrossPension(TEST_AGE_NORMAL,
																				  TEST_WORK_START,
																				  TEST_GROSS_YEARLY_SALARY).getMonthlyGrossPension();
		assertNotNull(grossMonthlyPension);
	}

	@Test
	public void testCalculateNormalPensionYear() {
		LocalDate pensionYear = pensionCalculationService.calculatePensionYear(TEST_AGE_NORMAL);
		assertEquals(pensionYear.getYear(), 2046);
	}

	@Test
	public void testCalculateEarlyPensionYear() {
		LocalDate pensionYear = pensionCalculationService.calculatePensionYear(TEST_AGE_EARLY);
		assertEquals(pensionYear.getYear(), 2024);
	}

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public PensionCalculationService pensionCalculationService() {
			return new PensionCalculationService();
		}
	}

}
