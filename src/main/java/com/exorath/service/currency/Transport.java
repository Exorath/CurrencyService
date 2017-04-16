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

package com.exorath.service.currency;

import com.exorath.service.commons.portProvider.PortProvider;
import com.exorath.service.currency.res.GetBalanceReq;
import com.exorath.service.currency.res.IncrementReq;
import com.exorath.service.currency.res.MultiIncrementReq;
import com.exorath.service.currency.res.Success;
import com.google.gson.Gson;
import spark.Route;

import static spark.Spark.*;

/**
 * Created by toonsev on 11/3/2016.
 */
public class Transport {
    private static final Gson GSON = new Gson();

    public static void setup(Service service, PortProvider portProvider) {
        port(portProvider.getPort());

        get("/currencies/:currency/players/:uuid", getGetBalanceRoute(service), GSON::toJson);
        put("/currencies/:currency/players/:uuid/inc", getIncrementRoute(service), GSON::toJson);
    }

    private static Route getGetBalanceRoute(Service service) {
        return (req, res) -> {
            return service.getBalance(new GetBalanceReq(req.params("currency"), req.params("uuid")));
        };
    }

    private static Route getIncrementRoute(Service service) {
        return (req, res) -> {
            try {
                Integer amount = Integer.valueOf(req.queryParams("amount"));
                Integer minimum = req.queryParams("min") != null ? Integer.valueOf(req.queryParams("min")) : null;
                return service.increment(new IncrementReq(req.params("currency"), req.params("uuid"), amount, minimum));
            } catch (Exception e) {
                e.printStackTrace();
                return new Success(-1, e.getMessage());
            }
        };
    }

    private static Route getMultiIncrementRoute(Service service) {
        return (req, res) -> {
            try {
                return service.multiIncrement(GSON.fromJson(req.body(), MultiIncrementReq.class));
            } catch (Exception e) {
                e.printStackTrace();
                return new Success(-1, e.getMessage());
            }
        };
    }
}
