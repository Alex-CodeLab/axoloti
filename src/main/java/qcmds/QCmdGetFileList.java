/**
 * Copyright (C) 2013, 2014 Johannes Taelman
 *
 * This file is part of Axoloti.
 *
 * Axoloti is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Axoloti is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Axoloti. If not, see <http://www.gnu.org/licenses/>.
 */
package qcmds;

import axoloti.connection.IConnection;

/**
 *
 * @author Johannes Taelman
 */
public class QCmdGetFileList implements QCmdSerialTask {

    boolean done = true;

    @Override
    public String getStartMessage() {
        return "";
    }

    @Override
    public String getDoneMessage() {
        if (done) {
            return "Finished receiving sdcard file list";
        } else {
            return "Incomplete sdcard file list...";
        }
    }

    @Override
    public QCmd performAction(IConnection connection) {
        connection.clearSync();
        connection.clearReadSync();
        connection.transmitGetFileList();
        int timeout = 0;
        while (!connection.waitReadSync()) {
            timeout++;
            if (timeout > 20) {
                done = false;
                break;
            }
        }
        return this;
    }
}
