package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;

import java.util.HashMap;
import java.util.Map;

public class CartApiService extends ApiService {

    private Map<String, String> getCookies(String sid) {
        Map<String, String> cookie = new HashMap<>();
        cookie.put("md.sid", sid);
        return cookie;
    }

    public AssertableResponse addItemToCart(String id, String sid) {
        return new AssertableResponse(setup()
                 .cookies(getCookies(sid))
                 .when()
                 .body("{\"id\":\"" + id + "\"}")
                 .post("/cart"));
    }

    public AssertableResponse getCartItem(String sid) {
        return new AssertableResponse(setup()
                .cookies(getCookies(sid))
                .when()
                .get("/cart"));
    }
}
