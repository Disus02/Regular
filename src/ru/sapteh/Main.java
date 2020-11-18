package ru.sapteh;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите данные о студенте: ");
        ArrayList<Person> arrayList=new ArrayList<>();
        arrayList.add(getPerson(buffer.readLine()));

        //create file, write in file
        File file=new File("C:/test/Student.txt");
        FileWriter fW=new FileWriter(file);
        for (Person person:arrayList) {
            fW.write(person.toString());
            fW.write("\n");
        }
        fW.close();


    }
    // Создание объекта
    public static Person getPerson(String strStudent) throws ParseException {
        String[] strings=strStudent.split(" ");
        String firstName=strings[0];
        String lastName=strings[1];
        String patronymic=strings[2];
        Date date= new SimpleDateFormat("dd.MM.yyyy").parse(strings[3].replaceAll("\\p{Punct}","."));
        int age=((int)((new Date().getTime()-date.getTime())/1000/60/60/24/365));

       //quantity days to Birthday
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(dateFormat.parse(strings[3].replaceAll("\\p{Punct}",".")));
        Calendar calendarNow=new GregorianCalendar();
        calendar.set(Calendar.YEAR,(int)(calendarNow.getTimeInMillis()/1000/60/60/24/365+1970));
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendarNow.set(Calendar.HOUR_OF_DAY,0);
        calendarNow.set(Calendar.MINUTE,0);
        calendarNow.set(Calendar.SECOND,0);
        calendarNow.set(Calendar.MILLISECOND,0);
        int quantityDayBirth=Math.abs((int)((calendar.getTimeInMillis()-calendarNow.getTimeInMillis())/1000/60/60/24));

        String day=String.format("%tA",date);


        return new Person(firstName,lastName,patronymic,date,age,quantityDayBirth,day);
    }
}
