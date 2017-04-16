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

/**
 * Created by toonsev on 4/1/2017.
 */
public class IncrementReq {
    private String currency;
    private String uuid;
    private int amount;
    private Integer min;

    public IncrementReq(String currency, String uuid, int amount) {
        this.currency = currency;
        this.uuid = uuid;
        this.amount = amount;
    }

    public IncrementReq(String currency, String uuid, int amount, Integer min) {
        this.currency = currency;
        this.uuid = uuid;
        this.amount = amount;
        this.min = min;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUuid() {
        return uuid;
    }

    public int getAmount() {
        return amount;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
