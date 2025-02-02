package taboolib.platform

import cn.nukkit.Server
import cn.nukkit.command.CommandSender
import cn.nukkit.player.Player
import taboolib.common.platform.*
import taboolib.common.platform.service.PlatformAdapter
import taboolib.platform.type.NukkitCommandSender
import taboolib.platform.type.NukkitPlayer

/**
 * TabooLib
 * taboolib.platform.NukkitAdapter
 *
 * @author CziSKY
 * @since 2021/6/20 0:46
 */
@Awake
@PlatformSide([Platform.NUKKIT])
class NukkitAdapter : PlatformAdapter {

    override fun console(): ProxyCommandSender {
        return adaptCommandSender(Server.getInstance().consoleSender)
    }

    override fun onlinePlayers(): List<ProxyPlayer> {
        return Server.getInstance().onlinePlayers.values.map { adaptPlayer(it) }
    }

    override fun adaptPlayer(any: Any): ProxyPlayer {
        return NukkitPlayer(any as Player)
    }

    override fun adaptCommandSender(any: Any): ProxyCommandSender {
        return if (any is Player) adaptPlayer(any) else NukkitCommandSender(any as CommandSender)
    }
}