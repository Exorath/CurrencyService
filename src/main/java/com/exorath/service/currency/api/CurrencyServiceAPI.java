/*
 * Copyright 2017 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.service.currency.api;

import com.exorath.service.currency.res.*;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

/**
 * Created by toonsev on 4/10/2017.
 */
public class CurrencyServiceAPI {
    private static final Gson GSON = new Gson();
    private String address;

    public CurrencyServiceAPI(String address) {
        this.address = address;
    }

    public IncrementSuccess increment(IncrementReq req) {
        try {
            HttpRequestWithBody httpRequest = Unirest.put(url("/currencies/{currency}/players/{uuid}/inc"))
                    .routeParam("currency", req.getCurrency())
                    .routeParam("uuid", req.getUuid())
                    .queryString("amount", req.getAmount());
            if (req.getMin() != null)
                httpRequest = httpRequest.queryString("min", req.getMin());
            return GSON.fromJson(httpRequest.asString().getBody(), IncrementSuccess.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new IncrementSuccess(-1, e.getMessage());
        }
    }

    public Success multiIncrement(MultiIncrementReq req) {
        try {
            String body = Unirest.put(url("/currencies/{currency}/players/{uuid}/incMulti"))
                    .body(GSON.toJson(req)).asString().getBody();
            return GSON.fromJson(body, IncrementSuccess.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new Success(-1, e.getMessage());
        }
    }

    public GetBalanceRes getBalance(GetBalanceReq req) {
        try {
            HttpRequest httpRequest = Unirest.get(url("/currencies/{currency}/players/{uuid}"))
                    .routeParam("currency", req.getCurrency())
                    .routeParam("uuid", req.getUuid());
            return GSON.fromJson(httpRequest.asString().getBody(), GetBalanceRes.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String url(String endpoint) {
        return address + endpoint;
    }
}
