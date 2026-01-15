# Snipr NamePingPlugin

A chat utility plugin for Hytale servers that highlights player mentions.

## Features

- **Mention Highlighting** - Mentions (e.g., `@PlayerName`) are highlighted in **Green** for the mentioned player.
- **Privacy Focused** - Only the mentioned player sees the highlight; others see standard text.
- **Multiple Mentions** - Supports tagging multiple players in a single message.
- **Case-Insensitive** - Works with `@playername`, `@PlayerName`, etc.

## Installation

1. Place `NamePingPlugin-1.0.0.jar` in your server's `mods/` or `plugins/` folder.
2. Restart your server.

## Usage

Simply type `@PlayerName` in the chat to mention someone.

- **Sender sees:** `<You> Hello @Bob` (White text)
- **Bob sees:** `<You> Hello @Bob` (@Bob is **Green**)
- **Others see:** `<You> Hello @Bob` (White text)

## Author

Created by **Snipr**
