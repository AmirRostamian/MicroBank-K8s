package app.demo.payment.common.testing.integration.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import app.demo.payment.auth.sdk.authority.Authority;
import app.demo.payment.auth.sdk.util.JwtTokenProvider;
import app.demo.payment.common.testing.integration.annotation.IntegrationTest;
import app.demo.payment.common.testing.integration.annotation.WireMockEnabled;
import app.demo.payment.common.util.RandomStringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.String.format;

@IntegrationTest
@WireMockEnabled
public class ApiControllerIntegrationTest {

    @SuppressWarnings("squid:S6813")
    @Autowired
    protected MockMvc mockMvc;

    @SpyBean
    protected JwtTokenProvider provider;

    @SuppressWarnings("squid:S6813")
    @Autowired
    protected WireMockServer wireMockServer;

    @BeforeEach
    protected void init() {
        RandomStringUtil.removeMock();
        wireMockServer.resetAll();
    }

    protected String customToken(Authority ... authorities) {
        return asHeaderValue(provider.createToken(userName(), authorities));
    }

    protected String serviceToken() {
        return asHeaderValue(provider.createToken(userName(), Authority.ROLE_SERVICE));
    }

    protected String userToken() {
        return asHeaderValue(provider.createToken(userName(), Authority.ROLE_USER));
    }

    protected String userName() {
        return "user";
    }

    protected String asHeaderValue(String token) {
        return format("Bearer %s", token);
    }
}
