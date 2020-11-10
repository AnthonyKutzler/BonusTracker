package com.company;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {



    public static void main(String[] args) throws Exception {
        new Everythigng(1);




        /**
         *
         * PK - Employee ID - First Name - Last Name - FK Table
         *
         * PK - FK - Date - Reg Hours - OT Hours
         *
         *
         * Find date, get hours column (reg), +1 row (ot hours)
         *
         */
    }

}
