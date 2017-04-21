package com.imaginecup.bug5.vista.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kataii on 4/20/2017.
 */

public class CustomDateTime {

    public static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String formatDateTime = df.format(calendar.getTime());
        return formatDateTime;
    }//Current Date Time

    public static String formatDate(String strDate) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
//        String outputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH, mm";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(strDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }//format Date
}
