import java.util.Date;
import java.lang.Math;

public class ControllerState
{
    private static final ControllerState INSTANCE = new ControllerState();
    
    public boolean ok=true;
    public double magnitude=0;
    public double direction=0;
    public double leftT=0;
    public double rightT=0;
    public boolean A=false;
    public boolean B=false;
    public boolean X=false;
    public boolean Y=false;
    public boolean Start=false;
    public long lastupdate=0;
    
    public double padX=0;
    public double padY=0;
    
    public ControllerState(){};
    
    public void setControllerState(
            boolean ok,         
            double magnitude,
            double direction,
            double leftT,
            double rightT,
            boolean A,
            boolean B,
            boolean X,
            boolean Y,
            boolean Start
            )
    {
        this.ok=ok;
        this.magnitude=magnitude;
        this.direction=direction;
        this.leftT=leftT;
        this.rightT=rightT;
        this.A=A;
        this.B=B;
        this.X=X;
        this.Y=Y;
        this.Start=Start;
        
        calculateXY();
        
        lastupdate = new Date().getTime();
    }

    public void calculateXY(){
        double dir = direction* Math.PI/180.0;
        padX = (int) (100.0 * Math.cos( dir ) * magnitude);
        padY = (int) (100.0 * Math.sin( dir ) * magnitude);
        //System.out.println("X: "+padX+"    Y: "+padY);
    }
    
    public static ControllerState getInstance() {
        return INSTANCE;
    }
}
