package com.gusparis.monthpicker.builder;

import android.widget.NumberPicker;

import java.text.DateFormatSymbols;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthFormatter {

    public static NumberPicker.Formatter getMonthFormatter(String type, DateFormatSymbols dfs, Locale locale) {
        switch (type) {
            case "short":
                return new ShortMonth(dfs);
            case "number":
                return new NumberMonth();
            case "shortNumber":
                return new ShortNumberMonth();
            default:
                return new FullMonth(dfs, locale);
        }
    }

    private static class FullMonth implements NumberPicker.Formatter {
        private String[] months;

        public FullMonth(DateFormatSymbols dfs, Locale locale) {
            String[] _months = new String[12];
            SimpleDateFormat monthDisplay = new SimpleDateFormat("LLLL", locale);
            SimpleDateFormat monthParse = new SimpleDateFormat("MM", locale);
            for (int i = 0; i < 12; i++) {
                String month = String.valueOf(i+1);
                _months[i] = monthDisplay.format(monthParse.parse(month,new ParsePosition(0)));
            }
            months = _months;
        }

        @Override
        public String format(int i) {
            return months[i];
        }
    }

    private static class ShortMonth implements NumberPicker.Formatter {
        private String[] months;

        public ShortMonth(DateFormatSymbols dfs) {
            months = dfs.getShortMonths();
        }

        @Override
        public String format(int i) {
            return months[i];
        }
    }

    private static class NumberMonth implements NumberPicker.Formatter {

        @Override
        public String format(int i) {
            return String.format("%02d", i + 1);
        }
    }

    private static class ShortNumberMonth implements NumberPicker.Formatter {

        @Override
        public String format(int i) {
            return String.valueOf(i + 1);
        }
    }
}
