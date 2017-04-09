package com.exorath.service.currency.service;

import com.exorath.service.currency.Service;
import com.exorath.service.currency.res.GetBalanceReq;
import com.exorath.service.currency.res.GetBalanceRes;
import com.exorath.service.currency.res.IncrementReq;
import com.exorath.service.currency.res.IncrementSuccess;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * Created by Toon on 4/7/2017.
 */
public class MongoService implements Service {
    private MongoCollection accountsCollection;
    public MongoService(MongoClient client, String databaseName) {
        MongoDatabase db = client.getDatabase(databaseName);
        accountsCollection = db.getCollection("accounts");
    }
    @Override
    public IncrementSuccess increment(IncrementReq req) {
        if(req.getMin() != null){
            //No upsert
        }else{

        }
        return null;
    }

    @Override
    public GetBalanceRes getBalance(GetBalanceReq req) {
        return null;
    }
}
