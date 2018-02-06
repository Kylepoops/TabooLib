package me.skymc.taboolib.anvil.versions;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.skymc.taboolib.methods.MethodsUtils;
import net.minecraft.server.v1_9_R2.BlockPosition;
import net.minecraft.server.v1_9_R2.ChatMessage;
import net.minecraft.server.v1_9_R2.ContainerAnvil;
import net.minecraft.server.v1_9_R2.EntityHuman;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.PacketPlayOutOpenWindow;

public class AnvilContainer_V1_9_4 extends ContainerAnvil {

	public AnvilContainer_V1_9_4(EntityHuman player)
	{
		super(player.inventory, player.world, new BlockPosition(0, 0, 0), player);
	}
	
	public boolean a(EntityHuman player)
	{
		return true;
	}
	  
	/** 
	 * @deprecated 方法已过期，已有新的方法
	 */
	public static void openAnvil(Player p)
	{
		EntityPlayer player = ((CraftPlayer)p).getHandle();
		AnvilContainer_V1_9_4 container = new AnvilContainer_V1_9_4(player);
	    int c = player.nextContainerCounter();
	    player.playerConnection.sendPacket(new PacketPlayOutOpenWindow(c, "minecraft:anvil", new ChatMessage("Repairing", new Object[0]), 0));
	    player.activeContainer = container;
	    player.activeContainer.windowId = c;
	    player.activeContainer.addSlotListener(player);
	}

}
