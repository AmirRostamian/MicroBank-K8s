package app.demo.payment.user;

import app.demo.payment.common.testing.integration.test.ApiControllerIntegrationTest;
import app.demo.payment.common.testing.integration.testcase.TestCase;
import app.demo.payment.user.domain.model.User;
import app.demo.payment.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static app.demo.payment.user.api.UserApiMethodDictionary.GET_MY_INFO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserPublicApiControllerTest extends ApiControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @TestCase({
            "integration/public-api/get-my-info/success/user.json",
            "integration/public-api/get-my-info/success/response.json"
    })
    public void whenTopUpRequested_thenWalletBalanceIncreases(User user,
                                                              String response) throws Exception {

        userRepository.save(user);

        mockMvc.perform(get(GET_MY_INFO)
                        .header(HttpHeaders.AUTHORIZATION, userToken()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }
}
