/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opentable.util;

import java.util.concurrent.TimeUnit;

/**
 * Utility class to pretty-print sizes.
 */
public final class Sizes
{
    private Sizes() { }

    /**
     * Given a size in bytes, format as a pretty size string.
     * The output will look like "15.4 MB".
     * @param size in bytes
     * @return nicely formatted file size (e.g. 15.4 MB)
     */
    public static String formatSize(long count)
    {
        return humanReadableByteCount(count, false);
    }

    /**
     * Given a size in bytes and a duration, format as a pretty
     * throughput string.  The output will look like "15.4 MB/s".
     * @param count size of data transfered
     * @param time amount of time that data was transfered in
     * @param units the unit time is measured in
     * @return nicely formatted data rate (e.g. 15.4 MB/s)
     */
    public static String formatRate(long count, long time, TimeUnit units)
    {
        double rate = count * 1000.0;
        rate /= TimeUnit.MILLISECONDS.convert(time, units);
        return formatSize((long) rate) + "/s";
    }

    // User "aioobe" http://stackoverflow.com/users/276052/aioobe
    // http://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
    // CC-BY-SA
    /**
     * Format byte size
     * @param bytes number of bytes
     * @param si whether to use SI units (as opposed to binary units, i.e. MB vs MiB)
     * @return human readable byte count (e.g. 110.6 kB)
     */
    private static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
