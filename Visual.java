import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;
import java.io.*;
import java.util.Scanner;

class Visual extends JPanel
{
    public Visual()
    {   
        //initWindow();
    }
    
    public void initWindow(){
        JFrame F=new JFrame();
        F.setTitle("Visual");
        F.setSize(500+6,500+28); //inner: 500x500
        F.setLocation(100,100);
        F.setResizable(false);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        F.add(new Visual());
        F.setVisible(true);
    }
    
    public synchronized void paint (Graphics g)
    {
        removeAll();//or remove(JComponent)
        revalidate();
        repaint();
        
        g.setColor(new Color(230, 250, 250, 100) ); 
        g.fillOval(-1500, -1500, 6000, 6000);
        g.setColor(new Color(20, 0, 0, 100) ); 
        
        Dimension d=getSize();
        int w = 500;
        int h = 500;
        //System.out.println("reneder...");
        g.drawLine(250,0,250,500);
        g.drawLine(0,250,500,250);
        //g.drawLine(0,0,w-1,h-1);
        //g.drawLine(0,h-1,w-1,0);
        g.drawRect(0,0,w-1,h-1);
        g.drawOval(0, 0, w, h);
        g.drawOval((int) Math.round(w*0.66*0.25), (int) Math.round(w*0.66*0.25), (int) Math.round(w*0.66), (int) Math.round(h*0.66));
        g.drawOval((int) Math.round(w*0.33), (int) Math.round(w*0.33), (int) Math.round(w*0.33), (int) Math.round(h*0.33));
        
        self (g);
        
        // pad direction
        friend (g,(int) (ControllerState.getInstance().padX*2.5),(int) (ControllerState.getInstance().padY*2.5));
        
        //gas and break
        double rt = ControllerState.getInstance().rightT;
        double lt = ControllerState.getInstance().leftT;
        
        g.setColor(new Color(0, 250, 0, 100) ); 
        g.fillRect( 501, (int) (250*(1-rt)), 98, (int) (250*rt) );
        g.setColor(new Color(250, 0, 0, 100) ); 
        g.fillRect( 501, (int) (250+250*(1-lt)), 98, (int) (250*lt) );
        
        //enemy (g,(int) Math.round(Math.random()*5)-250,(int) Math.round(Math.random()*500)-250);
        
        try{
            //wait(50);
        }catch(Exception e){
            
        }
        updateUI();
        repaint();
    }
    
    public void self (Graphics g)
    {
        Dimension d = getSize();
        int w = 500;
        int h = 500;
        g.setColor(new Color(0, 0, 0, 100) ); 
        g.fillOval(w/2-10/2, h/2-10/2, 10, 10) ;
    }
    
    public void friend (Graphics g,int x, int y)
    {
        g.setColor(new Color(0, 250, 0, 100) ); 
        g.fillOval(250+y-5, 250+(x*-1)-5, 10, 10) ;
    }
    
    public void enemy (Graphics g,int x, int y)
    {
        g.setColor(new Color(200, 0, 0, 100) ); 
        g.fillOval(250+x-5, 250+y-5, 10, 10) ;
    }
    
    
    
}

