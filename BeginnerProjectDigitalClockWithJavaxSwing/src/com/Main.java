package com;

//Libraries needed
import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame frame = new JFrame();

    public static void main(String[] args){
        //Set up the frame options.
        frame.setTitle("Digital Clock");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);
        frame.setResizable(false);

        JLabel timeLabel = new JLabel();
        //Set up the time label with font "Rosemary" and font size 100
        timeLabel.setFont(new Font("Rosemary", 0, 100));
        timeLabel.setText("00:00:00");
        frame.add(timeLabel);
        frame.pack();

        int seconds = 0;
        int minutes = 0;
        int hours   = 0;

        int speed = 1;

        while(true){
            //Interrupt the current thread if 1000 / speed milliseconds haven't passed. When speed is one it waits 1 second(1000ms), when speed is 1000 it waits 1ms, etc.
            try{Thread.sleep(1000 / speed);}
            catch(InterruptedException ex){Thread.currentThread().interrupt();}

            //Check when the max number possible is hit to not wrap around.
            if (hours >= 99 && minutes >= 59 && seconds >= 59) break;

            seconds++;

            //Increment the next digit when it hits 60
            while(seconds >= 60) {seconds -= 60; minutes++;}
            while(minutes >= 60) {minutes -= 60; hours  ++;}

            String hoursS, minutesS, secondsS;

            //Format the strings so they have a width of two digits.
            secondsS = seconds == 0 ? "00" : (seconds < 10 ? "0" : "") + Integer.toString(seconds);
            minutesS = minutes == 0 ? "00" : (minutes < 10 ? "0" : "") + Integer.toString(minutes);
            hoursS   = hours   == 0 ? "00" : (hours   < 10 ? "0" : "") + Integer.toString(hours);

            //Set the text to the timeLabel
            timeLabel.setText("%s:%s:%s".formatted(hoursS, minutesS, secondsS));
        }
    }
}
