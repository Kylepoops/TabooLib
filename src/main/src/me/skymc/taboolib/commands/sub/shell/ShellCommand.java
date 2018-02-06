package me.skymc.taboolib.commands.sub.shell;

import java.io.File;

import org.bukkit.command.CommandSender;

import me.skymc.taboolib.commands.SubCommand;
import me.skymc.taboolib.javashell.JavaShell;
import me.skymc.taboolib.message.MsgUtils;

public class ShellCommand extends SubCommand {

	public ShellCommand(CommandSender sender, String[] args) {
		super(sender, args);
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("load")) {
				new ShellLoadCommand(sender, args);
			}
			else if (args[1].equalsIgnoreCase("unload")) {
				new ShellUnloadCommand(sender, args);
			}
		}
		else {
			MsgUtils.send(sender, "&4ָ�����");
		}
	}

	@Override
	public boolean command() {
		return true;
	}

}
