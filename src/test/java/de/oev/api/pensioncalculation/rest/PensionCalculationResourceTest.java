package de.oev.api.pensioncalculation.rest;

import de.oev.api.pensioncalculation.PensioncalculationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static de.oev.api.pensioncalculation.rest.PensionCalculationResource.PARAM_AGE;
import static de.oev.api.pensioncalculation.rest.PensionCalculationResource.PARAM_GROSS_YEARLY_SALARY;
import static de.oev.api.pensioncalculation.rest.PensionCalculationResource.PARAM_WORK_START;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PensioncalculationApplication.class)
@AutoConfigureMockMvc
public class PensionCalculationResourceTest {

	private final String TEST_PARAM_VALUE_AGE = "42";
	private final String TEST_PARAM_VALUE_INVALID_AGE = "16";
	private final String TEST_PARAM_VALUE_WORK_START = "2008-01-01";
	private final String TEST_PARAM_VALUE_WORK_START_INVALID = "2008-01-1";
	private final String TEST_PARAM_VALUE_YEARLY_SALARY = "50000";

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGet() throws Exception{
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/pension")
				.param(PARAM_AGE, TEST_PARAM_VALUE_AGE)
				.param(PARAM_WORK_START, TEST_PARAM_VALUE_WORK_START)
				.param(PARAM_GROSS_YEARLY_SALARY, TEST_PARAM_VALUE_YEARLY_SALARY);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(OK.value(), result.getResponse().getStatus());

	}

	@Test
	public void testInvalidAge() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/pension")
				.param(PARAM_AGE, TEST_PARAM_VALUE_INVALID_AGE)
				.param(PARAM_WORK_START, TEST_PARAM_VALUE_WORK_START)
				.param(PARAM_GROSS_YEARLY_SALARY, TEST_PARAM_VALUE_YEARLY_SALARY);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(UNAUTHORIZED.value(), result.getResponse().getStatus());

	}

	@Test
	public void testInvalidDateFormat() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/pension")
				.param(PARAM_AGE, TEST_PARAM_VALUE_AGE)
				.param(PARAM_WORK_START, TEST_PARAM_VALUE_WORK_START_INVALID)
				.param(PARAM_GROSS_YEARLY_SALARY, TEST_PARAM_VALUE_YEARLY_SALARY);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(BAD_REQUEST.value(), result.getResponse().getStatus());

	}

}