package com.snipr.nameping;

import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.snipr.nameping.listeners.NamePingChatListener;
import javax.annotation.Nonnull;

public class NamePingPlugin extends JavaPlugin {
    public NamePingPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }
    @Override
    protected void start() {
        this.getEventRegistry().registerGlobal(PlayerChatEvent.class, NamePingChatListener::onPlayerChat);
    }
}
