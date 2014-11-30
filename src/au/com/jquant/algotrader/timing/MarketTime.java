/*
 * Copyright 2014 davidherod.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.com.jquant.algotrader.timing;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Date;

/**
 *
 * @author davidherod
 */
public class MarketTime {

    public static boolean marketIsOpen() {
        return true;
    }
    
    public static Timestamp timeInNY(){
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now(zone);
      
        Timestamp ts = Timestamp.valueOf(dateTime);
        return ts;
    }

    public static void timeUntilOpen() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now(zone);
        ZonedDateTime zonedDateTime = dateTime.atZone(zone);

        LocalTime openTime = LocalTime.parse("09:30:00");
        LocalTime currentTime = zonedDateTime.toLocalTime();

        long difference = ChronoUnit.MILLIS.between(openTime, currentTime);

        if (difference > 0) {
            difference -= 86400000;
        }
        
        int days = (int) (((difference / 1000) / 60) / 60) / 24;
        int hours = (int) ((difference / 1000) / 60) / 60;
        int minutes = (int) ((difference / 1000) / 60) % 60;
        int seconds = (int) ((difference / 1000) % 60) % 60;
        System.out.println(Math.abs(hours) + ":" + Math.abs(minutes) + ":" + Math.abs(seconds));

    }
    
        public static void timeUntilClose() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now(zone);
        ZonedDateTime zonedDateTime = dateTime.atZone(zone);

        LocalTime openTime = LocalTime.parse("16:00:00");
        LocalTime currentTime = zonedDateTime.toLocalTime();

        long difference = ChronoUnit.MILLIS.between(openTime, currentTime);

        if (difference > 0) {
            difference -= 86400000;
        }
        
        int days = (int) (((difference / 1000) / 60) / 60) / 24;
        int hours = (int) ((difference / 1000) / 60) / 60;
        int minutes = (int) ((difference / 1000) / 60) % 60;
        int seconds = (int) ((difference / 1000) % 60) % 60;
        System.out.println(Math.abs(hours) + ":" + Math.abs(minutes) + ":" + Math.abs(seconds));


    }

    public static long millisecondsUntilOpen() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now(zone);
        ZonedDateTime zonedDateTime = dateTime.atZone(zone);

        LocalTime openTime = LocalTime.parse("09:30:00");
        LocalTime currentTime = zonedDateTime.toLocalTime();

        long difference = ChronoUnit.MILLIS.between(openTime, currentTime);
        return Math.abs((difference > 0) ? difference - 86400000L: difference);
    }

    public static long millisecondsUntilClose() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now(zone);
        ZonedDateTime zonedDateTime = dateTime.atZone(zone);

        LocalTime openTime = LocalTime.parse("16:00:00");
        LocalTime currentTime = zonedDateTime.toLocalTime();

        long difference = ChronoUnit.MILLIS.between(openTime, currentTime);
        return Math.abs((difference > 0) ? difference - 86400000L: difference);
    }

    public static long millisecondsUntilPreClose() {
        ZoneId zone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now(zone);
        ZonedDateTime zonedDateTime = dateTime.atZone(zone);

        LocalTime openTime = LocalTime.parse("15:55:00");
        LocalTime currentTime = zonedDateTime.toLocalTime();

        long difference = ChronoUnit.MILLIS.between(openTime, currentTime);
        return (difference > 0) ? difference : difference - 86400000L;
    }

}
