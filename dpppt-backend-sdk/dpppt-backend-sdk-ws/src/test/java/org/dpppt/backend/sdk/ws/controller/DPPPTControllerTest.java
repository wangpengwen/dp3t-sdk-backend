package org.dpppt.backend.sdk.ws.controller;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.dpppt.backend.sdk.model.ExposeeAuthData;
import org.dpppt.backend.sdk.model.ExposeeRequest;


@SpringBootTest(properties =
{
    "ws.app.jwt.publickey=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArLd5ATQ/aW1aUaKGd8s7H8ixVuUBH0FBOnVj38AJy78goBrzZnHOtnwTyoerytviOEvHxquQHXm36rB5Pv0ZLvjR59deU9jOr9kCUYpllDOGGzMNO6WkZbkfWNvulBJPOIcRm/WLqLX33mQkm7yHtIQk7M5XHTrkcgdpSAMGlEksq0n3/cTmKMhN9fng4Heh8SiXOtFzBUBo5cLI372VQiMzQPnQkR6ncSG0R6zzJUh+ZP2uP1z+fElHcZrE0d05KE7Y3Y9SykIQfYdyA5majGEFOd4GedVPoYVIgttCJQbiWGL3kCirFAhaRqGYIOrk+Fk8u67/x9Iinasuc18qAQIDAQAB"
 })
public class DPPPTControllerTest extends BaseControllerTest {

    private final String jwtToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1LVJxcVRUWW9tZnBWejA2VlJFT2ZyYmNxYTVXdTJkX1g4MnZfWlRNLVZVIn0.eyJqdGkiOiI4ZmE5MWRlMi03YmYwLTRhNmYtOWIzZC1hNzdiZDM3ZDdiMTMiLCJleHAiOjE1ODczMTYzMTgsIm5iZiI6MCwiaWF0IjoxNTg3MzE2MDE4LCJpc3MiOiJodHRwczovL2lkZW50aXR5LXIuYml0LmFkbWluLmNoL3JlYWxtcy9iYWctcHRzIiwic3ViIjoiMWVmYTliZWYtOWU5ZC00MjNjLTkxMjctZmQwYjAxNWQxOTY2IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicHRhLWFwcC1iYWNrZW5kIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZGVjYzQ3Y2YtMmUyZi00NjZlLThiNTAtZmQ4MTAzZmQ4ZDNhIiwiYWNyIjoiMSIsInNjb3BlIjoiZXhwb3NlZCIsIm9uc2V0IjoiMjAyMC0wNS0yNSJ9.J5beGE6GjgRWEZfwzB9_G6X1uTZcZdm7Mkng8od5Fr3UPT4BbkKgPbpGRscouiAPBjOlZDCs3rcT_qiioX5wAZ0UjqLTe370K53vb1I_f4nQKfTMBYfzvdpS5i5V64LoKbXHpF7PLsGSiox6dA8g5Ssqf5uoTHz1_NY-6GvVq-LmFozV6_1zzYkBVZCLVh0gsqcG9EH2peuhEt9akv_Jmc1Ls0lZQeU1szeRmsk44mg8_FbG33yB3F0azhs0pfEuuYCzGbAqdFCU2RDnRCOXXr7o8Z_klrKE6NArWgbHbk8CE0a-3UwEdi6zw0xm1VNwbnMtjxVcyxECw7V2bSNu9A";

    private final String correctPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArLd5ATQ/aW1aUaKGd8s7H8ixVuUBH0FBOnVj38AJy78goBrzZnHOtnwTyoerytviOEvHxquQHXm36rB5Pv0ZLvjR59deU9jOr9kCUYpllDOGGzMNO6WkZbkfWNvulBJPOIcRm/WLqLX33mQkm7yHtIQk7M5XHTrkcgdpSAMGlEksq0n3/cTmKMhN9fng4Heh8SiXOtFzBUBo5cLI372VQiMzQPnQkR6ncSG0R6zzJUh+ZP2uP1z+fElHcZrE0d05KE7Y3Y9SykIQfYdyA5majGEFOd4GedVPoYVIgttCJQbiWGL3kCirFAhaRqGYIOrk+Fk8u67/x9Iinasuc18qAQIDAQAB";

    private final String wrongPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXw/iVHSGes+0NGAJ77uyjAKU6j1gdImrqPgF/nctUt0ASIIlJ3nPZYuhU9gHkDjGx+ESvHP6Z+3fD8BxOJons5+cB/cNC1sQj5IFTgUV1VMLKFwOG6OohLLP5BN/runhkMSf9vRxRNshRpDNlDdXeQ4kSRyfn4TqwO3l564qXqwIDAQAB";

    @Test
    public void testHello() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/v1"))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse();

        assertNotNull(response);
        assertEquals("Hello from DP3T WS", response.getContentAsString());
    }

    @Test
    public void testJWT() throws Exception {
        ExposeeRequest exposeeRequest = new ExposeeRequest();
        exposeeRequest.setAuthData(new ExposeeAuthData());
        exposeeRequest.setOnset("2020-04-10");
        exposeeRequest.setKey(Base64.getEncoder().encodeToString("test".getBytes("UTF-8")));
        MockHttpServletResponse response = mockMvc.perform(post("/v1/exposed")
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .header("Authorization", "Bearer " + jwtToken)
                                                            .header("User-Agent", "MockMVC")
                                                            .content(json(exposeeRequest)))
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse();
    }

}