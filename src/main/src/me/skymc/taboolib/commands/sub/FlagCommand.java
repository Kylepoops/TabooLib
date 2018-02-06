package me.skymc.taboolib.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import me.skymc.taboolib.commands.SubCommand;
import me.skymc.taboolib.jsonformatter.JSONFormatter;
import me.skymc.taboolib.jsonformatter.click.SuggestCommandEvent;
import me.skymc.taboolib.jsonformatter.hover.ShowTextEvent;

public class FlagCommand extends SubCommand {

	public FlagCommand(CommandSender sender, String[] args) {
		super(sender, args);
		
		sender.sendMessage("��f");
		sender.sendMessage("��b��l----- ��3��lItemStack Flags ��b��l-----");
		sender.sendMessage("��f");
		
		for (ItemFlag flag : ItemFlag.values()) {
			if (isPlayer()) {
				JSONFormatter json = new JSONFormatter();
				json.append(" ��7- ��f" + flag.name());
				json.appendHoverClick(" ��8(�������)", new ShowTextEvent("��f�������"), new SuggestCommandEvent(flag.name()));
				json.send((Player) sender);
			}
			else {
				sender.sendMessage(" ��7- ��f" + flag.name());
			}
		}
		sender.sendMessage("��f");
	}
}
