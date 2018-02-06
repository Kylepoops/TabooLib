package me.skymc.taboolib.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import me.skymc.taboolib.commands.SubCommand;
import me.skymc.taboolib.jsonformatter.JSONFormatter;
import me.skymc.taboolib.jsonformatter.click.SuggestCommandEvent;
import me.skymc.taboolib.jsonformatter.hover.ShowTextEvent;

public class AttributesCommand extends SubCommand {

	public AttributesCommand(CommandSender sender, String[] args) {
		super(sender, args);
		
		sender.sendMessage("��f");
		sender.sendMessage("��b��l----- ��3��lItemStack Attributes ��b��l-----");
		sender.sendMessage("��f");
		
		String[] attributes = new String[] { "damage", "speed", "attackspeed", "health", "knockback", "armor", "luck" };
		
		for (String name : attributes) {
			if (isPlayer()) {
				JSONFormatter json = new JSONFormatter();
				json.append(" ��7- ��f" + name);
				json.appendHoverClick(" ��8(�������)", new ShowTextEvent("��f�������"), new SuggestCommandEvent(name));
				json.send((Player) sender);
			}
			else {
				sender.sendMessage(" ��7- ��f" + name);
			}
		}
		sender.sendMessage("��f");
	}
}
