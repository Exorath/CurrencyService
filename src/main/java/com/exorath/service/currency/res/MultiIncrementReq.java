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

package com.exorath.service.currency.res;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by toonsev on 4/1/2017.
 */
public class MultiIncrementReq {
    private String uuid;
    private Collection<IncrementReq> requests = new HashSet<>();

    public MultiIncrementReq() {

    }

    public MultiIncrementReq(String uuid) {
        this.uuid = uuid;
    }

    public void addIncrement(String currency, int amount, Integer min) {
        requests.add(new IncrementReq(currency, uuid, amount, min));
    }

    public Collection<IncrementReq> getRequests() {
        return requests;
    }

    public String getUuid() {
        return uuid;
    }
}
