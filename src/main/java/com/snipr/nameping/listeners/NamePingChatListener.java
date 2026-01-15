package com.snipr.nameping.listeners;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import javax.annotation.Nonnull;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamePingChatListener {
    private static final NamePingFormatter FORMATTER = new NamePingFormatter();
    public static void onPlayerChat(@Nonnull PlayerChatEvent event) {
        event.setFormatter(FORMATTER);
    }
    private static class NamePingFormatter implements PlayerChatEvent.Formatter {
        private static final Pattern MENTION_PATTERN = Pattern.compile("@([A-Za-z0-9_]+)");
        @Override
        @Nonnull
        public Message format(@Nonnull PlayerRef playerRef, @Nonnull String message) {
            String senderName = playerRef.getUsername();
            Universe.get().getPlayers().forEach(recipient -> {
                if (!recipient.isValid()) return;
                String recipientName = recipient.getUsername();
                Message content = buildContentForRecipient(message, recipientName);
                List<Message> parts = new ArrayList<>();
                parts.add(Message.raw("<").color(Color.GRAY));
                parts.add(Message.raw(senderName).color(Color.WHITE));
                parts.add(Message.raw("> ").color(Color.GRAY));
                parts.add(content);
                recipient.sendMessage(Message.join(parts.toArray(new Message[0])));
            });
            return Message.raw("");
        }
        private static Message buildContentForRecipient(String message, String recipientName) {
            List<Message> segments = new ArrayList<>();
            Matcher m = MENTION_PATTERN.matcher(message);
            int last = 0;
            while (m.find()) {
                if (m.start() > last) {
                    segments.add(Message.raw(message.substring(last, m.start())).color(Color.WHITE));
                }
                String name = m.group(1);
                boolean highlight = name.equalsIgnoreCase(recipientName);
                segments.add(Message.raw("@" + name).color(highlight ? Color.GREEN : Color.WHITE));
                last = m.end();
            }
            if (last < message.length()) {
                segments.add(Message.raw(message.substring(last)).color(Color.WHITE));
            }
            return Message.join(segments.toArray(new Message[0]));
        }
    }
}
