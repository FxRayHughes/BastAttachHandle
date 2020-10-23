package ray.mintcat.bastattachhandle

import io.izzel.taboolib.module.command.base.*
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault
import org.serverct.ersha.jd.AttributeAPI
import org.serverct.ersha.jd.manager.player.handle.AttachHandle

@BaseCommand(name = "bastattachhandle", aliases = ["bah", "bh"], permissionDefault = PermissionDefault.TRUE)
class AttachHandleCommand:BaseMainCommand() {

    @SubCommand
    var take: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "扣除属性点 [返还]"
        }
        override fun hasPermission(sender: CommandSender?): Boolean {
            return if (sender is Player){
                sender.hasPermission("bastattachhandle.command.take")
            }else{
                true
            }
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("属性"), Argument("点数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            val attach = AttributeAPI.getAttachData(player)
            val ath = attach.getAttachLevel(args[1])
            if (ath < args[2].toInt()){
                player.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7您的 §f" + args[1] + " §7属性点不足")
                return
            }
            attach.deductAttach(args[1], args[2].toInt())
            attach.increasePoint(args[2].toInt(),true)
        }
    }

    @SubCommand
    var add: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "增加属性点 [扣除]"
        }
        override fun hasPermission(sender: CommandSender?): Boolean {
            return if (sender is Player){
                sender.hasPermission("bastattachhandle.command.add")
            }else{
                true
            }
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("属性"), Argument("点数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            val attach = AttributeAPI.getAttachData(player)
            attach.increaseAttach(args[1], args[2].toInt(),true)
            attach.deductPoint(args[2].toInt())
        }
    }

    @SubCommand
    var remove: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "扣除属性点 [不返还]"
        }
        override fun hasPermission(sender: CommandSender?): Boolean {
            return if (sender is Player){
                sender.hasPermission("bastattachhandle.command.remove")
            }else{
                true
            }
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("属性"), Argument("点数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            val attach = AttributeAPI.getAttachData(player)
            val ath = attach.getAttachLevel(args[1])
            if (ath < args[2].toInt()){
                player.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7您的 §f" + args[1] + " §7属性点不足")
                return
            }
            attach.deductAttach(args[1], args[2].toInt())
        }
    }

    @SubCommand
    var give: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "增加属性点 [不扣除]"
        }
        override fun hasPermission(sender: CommandSender?): Boolean {
            return if (sender is Player){
                sender.hasPermission("bastattachhandle.command.give")
            }else{
                true
            }
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("属性"), Argument("点数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            val attach = AttributeAPI.getAttachData(player)
            attach.increaseAttach(args[1], args[2].toInt(),true)
        }
    }

    @SubCommand
    var givepoint: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "增加自由点"
        }
        override fun hasPermission(sender: CommandSender?): Boolean {
            return if (sender is Player){
                sender.hasPermission("bastattachhandle.command.give")
            }else{
                true
            }
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("点数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("§f§l[§fBastAttachHandle§f§l]§f " + "§7目标 §f" + args[0] + " §7离线.")
                return
            }
            val attach = AttributeAPI.getAttachData(player)
            attach.increasePoint(args[1].toInt(),true)
        }
    }

}