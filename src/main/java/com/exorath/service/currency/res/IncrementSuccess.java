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
public class IncrementSuccess extends Success {
    private Integer newBalance;

    public IncrementSuccess(Integer newBalance) {
        super(true);
        this.newBalance = newBalance;
    }

    public IncrementSuccess(Integer code, String error) {
        super(false, code, error);
    }

    public Integer getNewBalance() {
        return newBalance;
    }
}
