package com.example.API.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParsearFecha {
    public static LocalDateTime parsearString(String fecha){
        return LocalDateTime.parse(fecha,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
