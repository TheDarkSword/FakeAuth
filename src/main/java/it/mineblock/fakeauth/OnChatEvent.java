package it.mineblock.fakeauth;

import it.mineblock.mbcore.Chat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Copyright © 2017 by Lorenzo Magni
 * All rights reserved. No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior written permission
 * of the creator.
 */
public class OnChatEvent implements Listener {

    @EventHandler
    public void onChatEvent(ChatEvent event) {
        CommandSender sender = (CommandSender) event.getSender();

        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            String username = player.getName();
            String msg = event.getMessage();
            String[] args = msg.split(" ");

            if(Main.passwords.containsKey(username)) {
                if(args[0].toLowerCase().equals("login")){
                    if(args[1].equals(Main.passwords.get(username))){
                        Main.passwords.remove(username);
                        Main.cooldown.remove(username);
                        Chat.send("&2Password accepted!", player, true);
                    } else {
                        player.disconnect(new TextComponent("Incorrect Password!"));
                    }
                } else {
                    player.disconnect(new TextComponent("Incorrect Password!"));
                }
                event.setCancelled(true);
            }
        }
    }
}
