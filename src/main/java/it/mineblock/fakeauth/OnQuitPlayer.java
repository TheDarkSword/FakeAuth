package it.mineblock.fakeauth;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;

/**
 * Copyright © 2017 by Michele Giacalone
 * All rights reserved. No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior written permission
 * of the creator.
 */
public class OnQuitPlayer implements Listener {

    public void onQuit(PlayerDisconnectEvent event){
        ProxiedPlayer player = event.getPlayer();
        String username = player.getName();

        if(Main.cooldown.containsKey(username)){
            Main.cooldown.remove(username);
        }
        if(Main.passwords.containsKey(username)){
            Main.passwords.remove(username);
        }
    }
}
