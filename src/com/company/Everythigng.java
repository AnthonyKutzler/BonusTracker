package com.company;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class
Everythigng {

    private XSSFWorkbook book;
    private XSSFSheet sheet;
    private Iterator<Row> rows;
    private List<Person> people;
    //private final File[] files = {new File("/home/gob/Documents/document.xlsx")};
    private final File file = new File("/home/gob/Downloads/document.xlsx");

    public Everythigng(int choice) throws Exception{
        book = new XSSFWorkbook(new FileInputStream(file));
        sheet = book.getSheetAt(0);
        rows = sheet.rowIterator();
        people = new ArrayList<>();
        switch (choice){
            case 1:
                doEverythingBonus();
                break;
            case 2:
                doEverythingAvg();
                break;
            case 3:
                doEverythingtot();
                break;
        }
    }



    public void doEverythingtot() throws Exception{
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = book.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            List<Person> people = new ArrayList<>();
            //Row row = rows.next();
            Person person = new Person("1234");
            person.setDate(LocalDate.now());
            Row row = null;
            boolean preRun = false;
            while (rows.hasNext()) {
                //try{

                    row = rows.next();
                    if (row.getFirstCellNum() == 0) {
                        if(row.getCell(0).getCellTypeEnum() == CellType.BLANK)
                            System.out.println(row.getRowNum());
                        if (row.getCell(0).getCellTypeEnum() != CellType.BLANK &&
                                (row.getCell(0).getStringCellValue().equals(person.getId()))) {
                            person.addHours(row.getCell(5).getNumericCellValue());
                            if (row.getCell(5).getNumericCellValue() == 0 && row.getCell(4).getNumericCellValue() > 0)
                                person.addHours(40);
                            row = rows.next();
                            person.addHours(row.getCell(5).getNumericCellValue());
                            if (row.getLastCellNum() >= 8 && row.getCell(8).getCellTypeEnum() == CellType.NUMERIC && !row.getCell(6).getStringCellValue().contains("1F"))
                                person.addPto(row.getCell(8).getNumericCellValue());
                            //person.addHours(row.getCell(5).getNumericCellValue());
                        } else {
                            people.add(person);
                            //System.out.println(row.getCell(0).getStringCellValue());
                            person = new Person(row.getCell(0).getStringCellValue());
                            person.setDate(LocalDate.parse(row.getCell(2).getStringCellValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                            person.setName(row.getCell(1).getStringCellValue());
                            if (row.getCell(5).getNumericCellValue() == 0 && row.getCell(4).getNumericCellValue() > 0)
                                person.addHours(40);
                            person.addHours(row.getCell(5).getNumericCellValue());
                            row = rows.next();
                            person.addHours(row.getCell(5).getNumericCellValue());
                            if (row.getLastCellNum() >= 8 && row.getCell(8).getCellTypeEnum() == CellType.NUMERIC && !row.getCell(6).getStringCellValue().contains("1F"))
                                person.addPto(row.getCell(8).getNumericCellValue());
                            //person.addHours(row.getCell(5).getNumericCellValue());
                        }
                    } else {
                        System.out.println(row.getRowNum());
                        //preRun = true;
                        //break;
                    }


                /*}catch (Exception e){
                    if(row.getRowNum() % 2 != 0)
                        System.out.println(row.getCell(0).getStringCellValue());
                }*/
            }
            if(!preRun)
                doEverythingElse(people);
    }

    public void doEverythingElse(List<Person> peopple) throws Exception {
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = book.createSheet("new");
        String id = "";
        Row row = sheet.createRow(0);
        int colIndex = 4;
        int y = 0;
        for (int z = 0; z < peopple.size(); z++) {
            Person person = peopple.get(z);
            if(id.equals(peopple.get(z).getId())){
                row.createCell(++colIndex).setCellValue(person.getHours());
            }else {
                id = person.getId();
                colIndex = 4;
                row = sheet.createRow(++y);
                row.createCell(0).setCellValue(person.getDate().toString());
                row.createCell(1).setCellValue(person.getId());
                row.createCell(2).setCellValue(person.getfName());
                row.createCell(3).setCellValue(person.getfName() + " " + person.getlName());
                row.createCell(colIndex).setCellValue(person.getHours());///person.getHoursCount());
                row.createCell(5).setCellValue(person.getPto());
            }// > 40 ? 40 : person.getHours());
            //row.createCell(5).setCellValue(person.getHours() > 40 ? person.getHours() - 40 : 0);
        }
        book.write(new FileOutputStream(file));
    }


    public void doEverythingAvg() throws Exception {
        //Row row = rows.next();
        Person person = new Person("1234");
        person.setDate(LocalDate.now());
        double hours = 0;
        boolean add = true;
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getFirstCellNum() == 0) {
                if (row.getCell(0).getCellTypeEnum() != CellType.BLANK &&
                        (row.getCell(0).getStringCellValue().equals(person.getId()))) {
                    if(row.getCell(5).getNumericCellValue() == 0)
                        add = false;
                    person.addHours2(row.getCell(5).getNumericCellValue(),add);
                    row = rows.next();
                    person.addHours2(row.getCell(5).getNumericCellValue(),add);
                    add = true;
                } else {
                    people.add(person);
                    System.out.println(row.getCell(0).getStringCellValue());
                    person = new Person(row.getCell(0).getStringCellValue());
                    person.setDate(LocalDate.parse(row.getCell(2).getStringCellValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    person.setName(row.getCell(1).getStringCellValue());
                    if(row.getCell(5).getNumericCellValue() == 0)
                        add = false;
                    person.addHours2(row.getCell(5).getNumericCellValue(),add);
                    row = rows.next();
                    person.addHours2(row.getCell(5).getNumericCellValue(),add);
                    add = true;
                    //person.addHours(row.getCell(5).getNumericCellValue());
                }
            } else {
                break;
            }


        }
        people.add(person);
        doEverythingElse(people);
    }



    /*** BONUS***/

    public void doEverythingBonus() throws Exception {

        //Row row = rows.next();
        Person person = new Person("1234");
        person.setDate(LocalDate.now());
        double hours = 0;
        while (rows.hasNext()) {
            Row row = rows.next();
            if (row.getFirstCellNum() == 0) {
                if (row.getCell(0).getCellTypeEnum() != CellType.BLANK &&
                        (row.getCell(0).getStringCellValue().equals(person.getId()) &&
                                LocalDate.parse(row.getCell(2).getStringCellValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy")).equals(person.getDate()))) {
                    person.addHours(row.getCell(5).getNumericCellValue());
                    row = rows.next();
                    person.addHours(row.getCell(5).getNumericCellValue());
                    //person.addHours(row.getCell(5).getNumericCellValue());
                } else {
                    people.add(person);
                    System.out.println(row.getCell(0).getStringCellValue());
                    person = new Person(row.getCell(0).getStringCellValue());
                    person.setDate(LocalDate.parse(row.getCell(2).getStringCellValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    person.setName(row.getCell(1).getStringCellValue());
                    person.addHours(row.getCell(5).getNumericCellValue());
                    row = rows.next();
                    person.addHours(row.getCell(5).getNumericCellValue());
                    //person.addHours(row.getCell(5).getNumericCellValue());
                }
            } else {
                break;
            }


        }
        doEverythingElse(people);
    }

}
