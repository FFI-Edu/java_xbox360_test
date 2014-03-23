// RumbleDemo.java

import driver.xboxdriver.*;
import javax.swing.JOptionPane;
import java.net.*;

public class RumbleDemo extends Thread
{
  private XboxController xc;
  private int leftVibrate = 0; 
  private int rightVibrate = 0; 
  
  private double magnitude=0;
  private double direction=0;
  private double leftT=0;
  private double rightT=0;
  private boolean A=false;
  private boolean B=false;
  private boolean X=false;
  private boolean Y=false;
  private boolean Start=false;
  private boolean dead=true;
  
  

  public RumbleDemo()
  { 
  }
  
  public void run(){
    xc = new XboxController();
    
    if (!xc.isConnected())
    {
      JOptionPane.showMessageDialog(null, 
        "Xbox controller not connected.",
        "Fatal error", 
        JOptionPane.ERROR_MESSAGE);
      xc.release();
      return;
    }
    dead=false;
    xc.addXboxControllerListener(new XboxControllerAdapter()
    {
      public void leftTrigger(double value)
      {
          leftVibrate = (int)(65535 * value / 2);
          rightVibrate=leftVibrate;
          xc.vibrate(leftVibrate, rightVibrate);
          leftT=value;
          setStatus();
      }
      public void rightTrigger(double value)
      {
          rightVibrate = (int)(65535 * value / 2);
          leftVibrate=rightVibrate;
          xc.vibrate(leftVibrate, rightVibrate);
          rightT=value;
          setStatus();
          //System.out.println("R: "+value);
      }
      public void buttonA(boolean pressed)
      {
          A=pressed;
          setStatus();
      }
      public void buttonB(boolean pressed)
      {
          B=pressed;
          setStatus();
      }
      public void buttonX(boolean pressed)
      {
          X=pressed;
          setStatus();
      }
      public void buttonY(boolean pressed)
      {
          Y=pressed;
          setStatus();
      }
      public void leftThumbDirection(double dir)
      {
          //System.out.println("Left stick dir: "+dir);
          direction=dir;
          setStatus();
      }
      public void leftThumbMagnitude(double mag)
      {
          //System.out.println("Left stick mag: "+mag);
          magnitude=mag;
          if(mag<0.18)
            magnitude=0;
          setStatus();
      }
    });
    
    while(xc.isConnected()){
        try{
            ServerSocket serverSocket = new ServerSocket(15989);
            Socket clientSocket = serverSocket.accept();
        }catch(Exception e){;}
    }
    
    
    JOptionPane.showMessageDialog(null, "dont close this.", "RumbleDemo V1.0 (www.aplu.ch)",JOptionPane.PLAIN_MESSAGE);
    
    dead=true;
    xc.release();
    //System.exit(0);
  }
    
  public void setStatus(){
      boolean ok=( xc.isConnected() && !dead );
      ControllerState.getInstance().setControllerState(
                            ok,
                            magnitude,
                            direction,
                            leftT,
                            rightT,
                            A,
                            B,
                            X,
                            Y,
                            Start
                            );
  }
  
  static boolean is64bit()
  {
     return System.getProperty("sun.arch.data.model").equals("64");
  }
  public static void main(String[] args)
  {
    new RumbleDemo();
  }
}