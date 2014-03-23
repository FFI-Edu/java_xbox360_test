
public class MAIN
{
    
    public MAIN()
    {
        //360 Driver
        new Thread( new RumbleDemo() ).start();
        //Draw Window
        new Thread( new WindowThread() ).start();
        //
        
    }

    public void sampleMethod()
    {
        return;
    }
}
