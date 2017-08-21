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

import com.exorath.exoHUD.HUDText;
import com.exorath.service.currency.res.GetBalanceReq;
import com.exorath.service.currency.res.GetBalanceRes;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by toonsev on 8/21/2017.
 */
public class CurrencyHUDText implements HUDText {
    private CurrencyServiceAPI currencyServiceAPI;
    private String currencyName;
    private String currencyId;
    private Player player;

    public CurrencyHUDText(CurrencyServiceAPI currencyServiceAPI, String currencyName, String currencyId, Player player) {
        this.currencyServiceAPI = currencyServiceAPI;
        this.currencyName = currencyName;
        this.currencyId = currencyId;
        this.player = player;
    }

    @Override
    public Observable<List<net.md_5.bungee.api.chat.TextComponent>> getTextObservable() {
        return Observable.<List<TextComponent>>create(s -> {
            GetBalanceRes res = currencyServiceAPI.getBalance(new GetBalanceReq(currencyId, player.getUniqueId().toString()));
            TextComponent openBracket = new TextComponent(currencyName + ":");
            openBracket.setColor(ChatColor.WHITE);
            TextComponent value = new TextComponent(res.getBalance() == null ? "Unknown" : " " + res.getBalance());
            openBracket.setColor(ChatColor.WHITE);
            s.onNext(Arrays.asList(openBracket, value));
        }).subscribeOn(Schedulers.io());
    }
}
