package lol.oce.atlantis.commands;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import lol.oce.atlantis.match.Match;
import lol.oce.atlantis.match.MatchManager;
import lol.oce.atlantis.match.types.MatchStatus;
import lol.oce.atlantis.match.types.MatchType;
import lol.oce.atlantis.utils.QuickUtils;
import org.bukkit.entity.Player;

@Command(name = "matchstart", aliases = {"s"})
public class MatchStartCommand {
    @Execute
    @SuppressWarnings("unused")
    @Permission("atlantic.admin")
    public void execute(@Context Player player, @Arg MatchType type) {
        String typeName = type.name();

        QuickUtils.debug("Player " + player.getName() + " started a match of type " + typeName);

        Match match = Match.builder()
                .setType(type)
                .setStatus(MatchStatus.WAITING)
                .setPvp(false)
                .build();

        MatchManager.getInstance().create(match);

        player.sendMessage("Match created with ID " + match.getUuid() + " and type " + typeName + ".");
    }
}
