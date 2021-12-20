package discordwebhookplugin.discordwebhookplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[DiscordWebhookSender]" + ChatColor.WHITE
                + "DiscordWebhookSender Enabled.");

        getCommand("discordwebhooksender").setExecutor(this);
        getCommand("dws").setExecutor(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[DiscordWebhookSender]" + ChatColor.WHITE
                + "DiscordWebhookSender Disabled.");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("discordwebhooksender")) {
                if (args.length == 0) {
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.DARK_AQUA + "DISCORD WEBHOOK SENDER " + ChatColor.YELLOW + "- By x64");
                    player.sendMessage(ChatColor.BOLD + "COMMANDS: " + ChatColor.GOLD + "/dws <WebhookURL> <Message>");
                    player.sendMessage(" ");
                }

                else if (args.length >= 2) {
                    DiscordWebhook webhook = new DiscordWebhook(args[0]);
                    webhook.setContent(args[1]);

                    try {
                        webhook.execute();
                    } catch (java.io.IOException e) {
                        getLogger().severe(e.getStackTrace().toString());
                    }
                }

                else player.sendMessage("Usage: /dws <WebhookURL> <Message>");
            }
        }

        else {
            if (args.length >= 2) {
                DiscordWebhook webhook = new DiscordWebhook(args[0]);
                webhook.setContent(args[1]);

                try {
                    webhook.execute();
                } catch (java.io.IOException e) {
                    getLogger().severe(e.getStackTrace().toString());
                }
            }
            else Bukkit.getConsoleSender().sendMessage("Usage: /dws <WebhookURL> <Message>");

        }

        return true;
    }
}
