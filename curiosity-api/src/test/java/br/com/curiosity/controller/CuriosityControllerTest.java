package br.com.curiosity.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.curiosity.service.CuriosityService;
import br.com.curiosity.utils.type.IdentifyProbeEnum;

@RunWith(PowerMockRunner.class)
@Configuration
@ComponentScan("br.com.curiosity")
public class CuriosityControllerTest {

	@InjectMocks
	private CuriosityController curiosityController;

	@Mock
	private CuriosityService curiosityService;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(curiosityController).build();
	}

	@Test
	public void testExplorer() throws Exception {
		String statusPoseidon = "4 5 S";
		String statusAtenas = "1 0 N";
		when(curiosityService.status(IdentifyProbeEnum.POSEIDON)).thenReturn(
				statusPoseidon);
		when(curiosityService.status(IdentifyProbeEnum.ATENAS)).thenReturn(
				statusAtenas);

		Mockito.doReturn(curiosityService).when(curiosityService)
				.startPlateau(any(String.class));
		Mockito.doReturn(curiosityService)
				.when(curiosityService)
				.startConfigProbe(any(IdentifyProbeEnum.class),
						any(String.class));
		Mockito.doReturn(curiosityService)
				.when(curiosityService)
				.instructionProbe(any(IdentifyProbeEnum.class),
						any(String.class));
		Mockito.doNothing().when(curiosityService).run();

		MvcResult result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/explorer")
								.param("plateaus", "5 5")
								.param("probe1", "0 0 N")
								.param("intruction1", "M")
								.param("probe2", "5 5 S")
								.param("intruction2", "M"))
				.andExpect(status().isOk()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertThat(content).contains(statusPoseidon,statusAtenas);

	}

}
