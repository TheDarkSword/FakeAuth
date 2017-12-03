package it.mineblock.fakeauth;

import it.mineblock.mbcore.Chat;
import it.mineblock.mbcore.bungeecord.Config;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2017 by Lorenzo Magni
 * All rights reserved. No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior written permission
 * of the creator.
 */
public class Main extends Plugin {

    static Configuration config;
    static HashMap<String, String> passwords = new HashMap<>();
    static  Map<String, Long> cooldown = new HashMap<>();

    private static final String PLUGIN_NAME = "FakeAuth";
    private static final String CONFIG = "config.yml";
    private Config configuration = Config.getInstance();

    public void onEnable() {
        config = configuration.autoloadConfig(this, PLUGIN_NAME, getResourceAsStream(CONFIG), new File(getDataFolder(), CONFIG), CONFIG);

        getProxy().getPluginManager().registerListener(this, new OnPreLogin());
        getProxy().getPluginManager().registerListener(this, new OnPostLogin());
        getProxy().getPluginManager().registerListener(this, new OnQuitPlayer());
        getProxy().getPluginManager().registerListener(this, new OnChatEvent());

        Chat.getLogger("&2Plugin FakeAuth Loaded", "info");
    }
}
