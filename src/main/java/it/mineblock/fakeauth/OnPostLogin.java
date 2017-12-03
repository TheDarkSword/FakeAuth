package it.mineblock.fakeauth;

import it.mineblock.mbcore.Chat;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2017 by Lorenzo Magni
 * All rights reserved. No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior written permission
 * of the creator.
 */
public class OnPostLogin implements Listener {

    //private final long TIME = 10;
    //private ScheduledTask task;

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String username = player.getName();

        if(Main.passwords.containsKey(username)) {
            Main.cooldown.put(username, System.currentTimeMillis());
            ProxyServer.getInstance().getScheduler().schedule(ProxyServer.getInstance().getPluginManager().getPlugin("FakeAuth"), () -> {
                Iterator<Map.Entry<String, Long>> it = Main.cooldown.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, Long> pair = it.next();
                    if(System.currentTimeMillis() - pair.getValue() >= (10 * 1000)){
                        for(String username1 : Main.config.getStringList("fake-players")){
                            if(Main.cooldown.containsKey(username1)){
                                for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()){
                                    if(players.getName().equals(username1)){
                                        players.disconnect(new TextComponent("Password timed out!"));
                                    }
                                }
                                it.remove();
                            }
                        }
                    }
                }
            }, 10, TimeUnit.SECONDS);
        }
    }
}
