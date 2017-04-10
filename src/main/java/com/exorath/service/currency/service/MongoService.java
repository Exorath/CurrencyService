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

package com.exorath.service.currency.service;

import com.exorath.service.commons.mongoProvider.MongoProvider;
import com.exorath.service.commons.tableNameProvider.TableNameProvider;
import com.exorath.service.currency.Service;
import com.exorath.service.currency.res.GetBalanceReq;
import com.exorath.service.currency.res.GetBalanceRes;
import com.exorath.service.currency.res.IncrementReq;
import com.exorath.service.currency.res.IncrementSuccess;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

/**
 * accounts db doc example: {"currency": "coins", "uuid": "xxx", "balance": 1234}
 * Created by toonsev on 4/1/2017.
 */
public class MongoService implements Service {
    private MongoClient mongoClient;
    private MongoCollection<Document> accounts;

    public MongoService(MongoProvider mongoProvider, TableNameProvider dbNameProvider) {
        this.mongoClient = mongoProvider.getClient();
        this.accounts = mongoClient.getDatabase(dbNameProvider.getTableName()).getCollection("accounts");
    }


    @Override
    public IncrementSuccess increment(IncrementReq req) {
        Document filter = getFilter(req.getCurrency(), req.getUuid());
        Document result;
        if (req.getMin() != null) {
            filter.append("balance", req.getMin());
            result = accounts.findOneAndUpdate(filter, Updates.inc("balance", req.getAmount()), new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER));
            if (result == null)
                return new IncrementSuccess(1, "Insufficient funds");
        } else
            result = accounts.findOneAndUpdate(filter, Updates.inc("balance", req.getAmount()), new FindOneAndUpdateOptions().upsert(true));
        if (result == null)
            return new IncrementSuccess(-1, "Unknown error");
        return new IncrementSuccess(result.getInteger("balance"));
    }

    private Document getFilter(String currency, String uuid) {
        return new Document("currency", currency).append("uuid", uuid);
    }

    @Override
    public GetBalanceRes getBalance(GetBalanceReq req) {
        Document first = accounts.find(getFilter(req.getCurrency(), req.getUuid())).first();
        if (first == null || !first.containsKey("balance"))
            return new GetBalanceRes(0);
        return new GetBalanceRes(first.getInteger("balance"));
    }
}
