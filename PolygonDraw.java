import java.awt.Graphics;
import java.util.ArrayList;

public class PolygonDraw {
	private int x[];
	private int y[];
	private int v;
	private double L[];
	ArrayList<Double> angList = new ArrayList<Double>();
	ArrayList<Double> lenList = new ArrayList<Double>();
	public double[] getL() {
		return L;
	}

	public void setL(double[] l) {
		this.L = l;
	}

	public PolygonDraw(int x[], int y[], int v) {
		super();
		this.x=x;
		this.y=y;
		this.v=v;
	}
	
	public int[] getX() {
		return x;
	}

	public void setX(int[] x) {
		this.x = x;
	}

	public int[] getY() {
		return y;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}
	
	public void drawComponent(Graphics g) {
		g.drawPolygon(this.getX(),this.getY(),this.getV());
		
	}
	public double getArea() {
		double area=0;
		int N = this.getV();
		for(int i=0;i<N;i++) {
			if(i==N-1) {
				area+=(this.getX()[i]*this.getY()[0])-(this.getX()[0]*this.getY()[i]);
			}
			else {
				area+=(this.getX()[i]*this.getY()[i+1])-(this.getX()[i+1]*this.getY()[i]);
			}
		}
		area/=2;
		if(area<0) {
			area*=-1;
		}
		return area;
	}
	public void getLength() {
		int N = this.getV();
		this.L = new double[N];
		for(int i=0;i<N;i++) {
			int j=(i+1)%N;
			double a,b;
			a=((this.getX()[i]-this.getX()[j])*(this.getX()[i]-this.getX()[j]));
			b=((this.getY()[i]-this.getY()[j])*(this.getY()[i]-this.getY()[j]));
			this.L[i]=Math.sqrt(a+b);
			lenList.add(Math.sqrt(a+b));
		}
	}
	
	
	public void getAngle() {
		int size=this.getV();
        int x[]=new int[size];
        int y[]=new int[size];
        x = this.getX();
        y=this.getY();
        
        for(int n=0;n<size;n++) {
            int p=0,q=0;
            p=n-1;
            q=n+1;				
            if(n==0){
            	p=size-1;
            	
            }
            else if(n==size-1){
            	q=0;
            }
            double aa,bb;
            aa= x[p]-x[n];
            bb=x[q]-x[n];
            if(aa==0)
            	aa=0.001;
            if(bb==0)
            	bb=0.001;
            double m1=(y[p]-y[n])/aa;
            double m2=(y[q]-y[n])/bb;
            double a1,b1,c1,a2,b2,c2;
            a1=-m1;
            a2=-m2;
            b1=1;
            b2=1;
            c1=m1*x[n]-y[n];
            c2=m2*x[n]-y[n];
            int x1=0,x2=0;
            for(int i=0;i<size;i++){
                if(i!=n&&i!=p){       //check for line 1 to get a vertex which is not a point of line
                    x1=i;
                    break;
                }}
            for(int i=0;i<size;i++){
                if(i!=n&&i!=q){       //check for line 2 to get a vertex which is not a point of line
                    x2=i;
                    break;
                }
            }
            double f1,f2;
            f1=a1*x[x1]+b1*y[x1]+c1;
            f2=a2*x[x2]+b2*y[x1]+c2;
            int flag1=0,flag2=0;
            for(int i=0;i<size;i++){
                if(i!=n&&i!=p&&i!=x1){       //check for line 1 check for opposite points
                    double z1;
                    z1=a1*x[i]+b1*y[i]+c1;
                    if(f1*z1<0){
                        flag1=1;
                        break;
                    }
                    //break;
                }
            }
            for(int i=0;i<size;i++){
                if(i!=n&&i!=q&&i!=x2){       //check for line 2
                    double z2;

                    z2=a2*x[i]+b2*y[i]+c2;
                    if(f2*z2<0){
                        flag2=1;
                        break;
                    }
                }
            }
            
			double answ;
			double ang1 = Math.toDegrees(Math.atan2(this.getY()[n]-this.getY()[q], this.getX()[n]-this.getX()[q]));
			double ang2 = Math.toDegrees(Math.atan2(this.getY()[n]-this.getY()[p], this.getX()[n]-this.getX()[p]));
			if(ang2>ang1)
				answ=ang2-ang1;
			else
				answ=ang1-ang2;
            
            if(flag1==1&&flag2==1){
            	if(answ<180)
            		answ=360-answ;
            }
            else{
            	if(answ>180.0)
            		answ=360.0-answ;
            }
            angList.add(answ);
        }
	}
	
	public int contains(int a, int b) {
		for(int i=0;i<this.getV();i++) {
			int temp = ((this.getX()[i]-a)*(this.getX()[i]-a)) + ((this.getY()[i]-b)*(this.getY()[i]-b)) -25;
			if(temp<=0) {
				return i;
			}
		}
		return -1;
	}
}
