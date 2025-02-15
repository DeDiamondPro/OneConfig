/*
 * This file is part of OneConfig.
 * OneConfig - Next Generation Config Library for Minecraft: Java Edition
 * Copyright (C) 2021, 2022 Polyfrost.
 *   <https://polyfrost.cc> <https://github.com/Polyfrost/>
 * Co-author: MicrocontrollersDev <https://github.com/MicrocontrollersDev> (non-copyrightable contribution)
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *   OneConfig is licensed under the terms of version 3 of the GNU Lesser
 * General Public License as published by the Free Software Foundation, AND
 * under the Additional Terms Applicable to OneConfig, as published by Polyfrost,
 * either version 1.0 of the Additional Terms, or (at your option) any later
 * version.
 *
 *   This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 * License.  If not, see <https://www.gnu.org/licenses/>. You should
 * have also received a copy of the Additional Terms Applicable
 * to OneConfig, as published by Polyfrost. If not, see
 * <https://polyfrost.cc/legal/oneconfig/additional-terms>
 */

package cc.polyfrost.oneconfig.utils.hypixel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the location of the player in Hypixel.
 * <p>
 * Locraw utilities taken from Seraph by Scherso under LGPL-2.1
 * <a href="https://github.com/Scherso/Seraph/blob/master/LICENSE">https://github.com/Scherso/Seraph/blob/master/LICENSE</a>
 * </p>
 *
 * @see HypixelUtils
 */
public class LocrawInfo implements Serializable {
    @SerializedName("server")
    private String serverId;
    @SerializedName("mode")
    private String gameMode = "lobby";
    @SerializedName("map")
    private String mapName;
    @SerializedName("gametype")
    private String rawGameType;
    private GameType gameType;

    /**
     * @return The serverID of the server you are currently on, ex: mini121
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @return The GameType of the server as a String.
     */
    public String getRawGameType() {
        if (rawGameType == null) rawGameType = "UNKNOWN";
        return rawGameType;
    }

    /**
     * @return The GameMode of the server, ex: solo_insane
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * @return The GameType of the server as an Enum.
     */
    public GameType getGameType() {
        return gameType;
    }

    /**
     * @param gameType The GameType to set it to.
     */
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    /**
     * @return The map of the server, ex: Shire.
     */
    public String getMapName() {
        return mapName;
    }

    @Override
    public String toString() {
        return "LocrawInfo{" + "serverId='" + serverId + '\'' + ", gameMode='" + gameMode + '\'' + ", mapName='" + mapName + '\'' + ", rawGameType='" + rawGameType + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocrawInfo that = (LocrawInfo) o;
        return Objects.equals(serverId, that.serverId) && Objects.equals(gameMode, that.gameMode) && Objects.equals(mapName, that.mapName) && Objects.equals(rawGameType, that.rawGameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverId, gameMode, mapName, rawGameType);
    }

    public enum GameType {
        UNKNOWN(""), LIMBO("LIMBO"), BEDWARS("BEDWARS"), SKYWARS("SKYWARS"), PROTOTYPE("PROTOTYPE"), SKYBLOCK("SKYBLOCK"), MAIN("MAIN"), MURDER_MYSTERY("MURDER_MYSTERY"), HOUSING("HOUSING"), ARCADE_GAMES("ARCADE"), BUILD_BATTLE("BUILD_BATTLE"), DUELS("DUELS"), PIT("PIT"), UHC_CHAMPIONS("UHC"), SPEED_UHC("SPEED_UHC"), TNT_GAMES("TNTGAMES"), CLASSIC_GAMES("LEGACY"), COPS_AND_CRIMS("MCGO"), BLITZ_SG("SURVIVAL_GAMES"), MEGA_WALLS("WALLS3"), SMASH_HEROES("SUPER_SMASH"), WARLORDS("BATTLEGROUND"), DROPPER("DROPPER");

        private final String serverName;

        GameType(String serverName) {
            this.serverName = serverName;
        }

        public static GameType getFromLocraw(String gameType) {
            if (gameType == null) return UNKNOWN;
            for (GameType value : values()) {
                if (value.serverName.equals(gameType)) {
                    return value;
                }
            }

            return UNKNOWN;
        }

        public String getServerName() {
            return serverName;
        }
    }
}
