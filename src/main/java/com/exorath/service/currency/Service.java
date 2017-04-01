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

import com.exorath.service.currency.res.GetBalanceReq;
import com.exorath.service.currency.res.GetBalanceRes;
import com.exorath.service.currency.res.IncrementReq;
import com.exorath.service.currency.res.IncrementSuccess;

/**
 * Created by toonsev on 4/1/2017.
 */
public interface Service {
    /**
     * Increments or decrements (negative amount) the balance of a player's currency
     * @param req the increment req
     * @return whether or not this was successful
     */
    IncrementSuccess increment(IncrementReq req);

    GetBalanceRes getBalance(GetBalanceReq req);
}
