import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;
import java.io.*;
import java.util.Scanner;

public class WindowThread implements Runnable
{
    public WindowThread()
    { 
    }
    
    public void run(){
        JFrame F=new JFrame();
        F.setTitle("Visual");
        F.setSize(500+150+6,500+28); //inner: 500x500
        F.setLocation(100,100);
        //F.setResizable(false);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        F.add(new Visual());
        //F.setContentPane(new Visual());
        F.setVisible(true);
        
        //F.toFront();
        System.out.println("window running");
    }
}
