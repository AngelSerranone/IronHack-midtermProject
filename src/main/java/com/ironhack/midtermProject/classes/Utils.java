package com.ironhack.midtermProject.classes;

import java.time.LocalDate;
import java.time.Period;

public class Utils {

    /** Method to calculate the integer amount of years between two dates **/
    public static Integer calculateYears(LocalDate date){
        Period age = Period.between(date, LocalDate.now());
        return age.getYears();
    }

    /** Method to calculate the integer amount of months between two dates **/
    public static Integer calculateMonths(LocalDate lastUpdate){
        Period age = Period.between(lastUpdate, LocalDate.now());
        return age.getMonths();
    }
}
