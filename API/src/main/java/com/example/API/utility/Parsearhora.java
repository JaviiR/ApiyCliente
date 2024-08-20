package com.example.API.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parsearhora {
    public static LocalTime parsearString(String hora){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime localTime=LocalTime.parse(hora,formatter);
        return localTime;
    }
}
