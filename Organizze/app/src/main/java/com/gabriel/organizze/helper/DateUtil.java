package com.gabriel.organizze.helper;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String dataAtual(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
