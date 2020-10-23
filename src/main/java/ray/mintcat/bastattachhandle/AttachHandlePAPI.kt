package ray.mintcat.bastattachhandle

import io.izzel.taboolib.module.inject.THook
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.serverct.ersha.jd.AttributeAPI

@THook
class AttachHandlePAPI : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "attachhandle"
    }

    override fun getAuthor(): String {
        return "Ray_Hughes"
    }

    override fun getVersion(): String {
        return "0.0.1"
    }

    override fun persist(): Boolean {
        return true
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {
        if (player.isOnline){
            val attach = AttributeAPI.getAttachData(player)
            val param = params.split("_".toRegex())
            when (param[0]) {
                "level" -> return attach.getAttachLevel(param[1]).toString()
                "point" -> return attach.getPoint().toString()
                else -> return "N?A"
            }
        }
        return "N?A"
    }

}