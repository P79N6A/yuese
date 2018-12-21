/**
 *
 * Copyright 2003-2007 Jive Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.filer;

import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.Packet;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.PacketFilter;




/**
 * Filters for packets with a particular packet ID.
 *
 * @author Matt Tucker
 */
public class PacketIDFilter implements PacketFilter {

    private String packetID;

    /**
     * Creates a new packet ID filter using the specified packet's ID.
     *
     * @param packet the packet which the ID is taken from.
     */
    public PacketIDFilter(Packet packet) {
        this(packet.getPacketID());
    }

    /**
     * Creates a new packet ID filter using the specified packet ID.
     *
     * @param packetID the packet ID to filter for.
     */
    public PacketIDFilter(String packetID) {
        if (packetID == null) {
            throw new IllegalArgumentException("Packet ID must not be null.");
        }
        this.packetID = packetID;
    }

    public boolean accept(Packet packet) {
        return packetID.equals(packet.getPacketID());
    }

    public String toString() {
        return "PacketIDFilter by id: " + packetID;
    }
}