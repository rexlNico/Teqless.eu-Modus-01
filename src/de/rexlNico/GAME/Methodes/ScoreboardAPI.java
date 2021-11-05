package de.rexlNico.GAME.Methodes;


import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rexlManu on 29.08.2017.
 * Plugin by rexlManu
 * https://rexlGames.de
 * Coded with IntelliJ
 */
public class ScoreboardAPI {

    private Scoreboard sb;
    private ScoreboardObjective ob;
    private List<String> lines;
    private Player p;

    public ScoreboardAPI(Player p, String title){
        this.p = p;
        String title1 = title;
        sb = new Scoreboard();
        ob = sb.registerObjective("API", IScoreboardCriteria.b);
        lines = new ArrayList<>();
        ob.setDisplayName(title);
    }
    public void addLine(String text){
        lines.add(text);
    }
    public void sendScoreboard(){

        PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(ob,0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1,ob);

        List<ScoreboardScore> scores = new ArrayList<>();
        int i = lines.size();
        for(String st : lines){
            ScoreboardScore score = new ScoreboardScore(sb, ob, st);
            score.setScore(i);
            scores.add(score);
            i--;
        }

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(ob, 1);
        List<PacketPlayOutScoreboardScore> scores2 = new ArrayList<>();
        for(ScoreboardScore scoreboardScore : scores){
            PacketPlayOutScoreboardScore a = new PacketPlayOutScoreboardScore(scoreboardScore);
            scores2.add(a);
        }

        sendPacketMitDHL(p, removePacket);
        sendPacketMitDHL(p, createPacket);
        sendPacketMitDHL(p, display);

        for(PacketPlayOutScoreboardScore score : scores2){
            sendPacketMitDHL(p, score);
        }

    }
    private void sendPacketMitDHL(Player p, Packet packet){
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }
}