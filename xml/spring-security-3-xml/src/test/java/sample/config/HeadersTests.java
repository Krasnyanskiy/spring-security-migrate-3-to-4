package sample.config;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HeadersTests {

	MockHttpServletRequest request;
	MockHttpServletResponse response;
	MockFilterChain chain;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	@Before
	public void setup() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		chain = new MockFilterChain();
	}

	@Test
	public void headers() throws Exception {
		springSecurityFilterChain.doFilter(request, response, chain);

		assertThat(response.getHeader("X-Frame-Options")).isEqualTo("SAMEORIGIN");
		assertThat(response.getHeaderNames().size()).isEqualTo(1);
	}
}
