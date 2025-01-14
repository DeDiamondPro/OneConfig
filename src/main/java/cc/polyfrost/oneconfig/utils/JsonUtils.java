/*
 * This file is part of OneConfig.
 * OneConfig - Next Generation Config Library for Minecraft: Java Edition
 * Copyright (C) 2021, 2022 Polyfrost.
 *   <https://polyfrost.cc> <https://github.com/Polyfrost/>
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

package cc.polyfrost.oneconfig.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Various utility methods for working with JSON.
 */
public final class JsonUtils {

    /**
     * The instance of the parser.
     */
    public static final JsonParser PARSER = new JsonParser();

    /**
     * Parses a string into a {@link JsonElement}.
     *
     * @param string          The string to parse.
     * @param catchExceptions Whether to catch exceptions.
     * @return The {@link JsonElement}.
     * @see JsonParser#parse(String)
     */
    public static JsonElement parseString(String string, boolean catchExceptions) {
        try {
            return PARSER.parse(string);
        } catch (Exception e) {
            if (catchExceptions) {
                return null;
            } else {
                throw e;
            }
        }
    }

    /**
     * Parses a string into a {@link JsonElement}.
     *
     * @param string The string to parse.
     * @return The {@link JsonElement}.
     * @see JsonUtils#parseString(String, boolean)
     */
    public static JsonElement parseString(String string) {
        return parseString(string, true);
    }
}
