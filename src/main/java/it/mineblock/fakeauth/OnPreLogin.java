package it.mineblock.fakeauth;

import it.mineblock.mbcore.Chat;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Copyright © 2017 by Lorenzo Magni
 * All rights reserved. No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior written permission
 * of the creator.
 */
public class OnPreLogin implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        PendingConnection connection = event.getConnection();
        String username = connection.getName();

        if(!Main.config.getStringList("fake-players").contains(username)) {
            Chat.getLogger("&cUsername &f&o" + username + " &cnot in list!", "info");
            return;
        }

        RandomString password = new RandomString(10);
        String pwd = password.nextString();
        Main.passwords.put(username, pwd);

        Chat.getLogger("&6Username: &c" + username + " &6Password: &c" + pwd, "info");
        connection.setOnlineMode(false);
    }
}
