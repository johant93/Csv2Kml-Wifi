package Tools;

public class Point3D {

	//////////////    fields     ///////////////

	private double _x,_y,_z;

	///////////////////     Constructor     /////////////////////////   

	public Point3D(double x,double y,double z) 
	{
		_x=x;
		_y=y;
		_z=z;
	}

	public Point3D(Point3D p) 
	{
		_x=p.x();
		_y=p.y();
		_z=p.z();
	}
	public Point3D(double x,double y) 
	{this(x,y,0);}
	public Point3D(String s) {
		String[] a = s.split(" ");
		_x = new Double(a[0]);
		_y = new Double(a[1]);
		_z = new Double(a[2]);
	}
	////////////////////////////       methods        /////////////////////////

	public double x() {return _x;}
	public double y() {return _y;}
	public double z() {return _z;}
	public int ix() {return (int)_x;}
	public int iy() {return (int)_y;}
	public int iz() {return (int)_z;}
	
	 /** dirty walk a round the lock!! */
		public void set(Point3D p) {
			_x=p._x;_y=p._y;_z=p._z;
		}
		public void offset(Point3D p) { offset(p._x,p._y,p._z);}
		public void set(double x, double y, double z) {
			_x=x;_y=y;_z=z;
		}
		public void offset(double dx, double dy, double dz) {
			_x+=dx;_y+=dy;_z+=dz;
		}
	
	public void add(double x, double y)
	{
		_x+=x;
		_y+=y;
	}

	
	public void add(double x, double y,double z)
	{
		_x+=x;
		_y+=y;
		_z+=z;
	}

	public void setX(double x)
	{
		_x=x;
	}

	public void setY(double y)
	{
		_y=y;
	}

	public void setZ(double z)
	{
		_z=z;
	}
	
	
	public void setXY(double x, double y)
	{
		_x=x;
		_y=y;
	}

	public void setXYZ(double x, double y,double z)
	{
		_x=x;
		_y=y;
		_z=z;
	}
	

	public String toString() 
	{
		return "( "+_x+" ; "+_y+" ; "+_z+" )";
	}


	public double distance2D(Point3D p2)
	{
		double t = (_x-p2.x())*(_x-p2.x()) + (_y-p2.y())*(_y-p2.y());
		return Math.sqrt(t);
	}

	
	public double distance3D(Point3D p2)
	{
		double t = (_x-p2.x())*(_x-p2.x()) + (_y-p2.y())*(_y-p2.y()) + (_z-p2.z())*(_z-p2.z());
		return Math.sqrt(t);
	}
	

	public boolean equals(Point3D p2)
	{
		return ( (_x==p2._x) && (_y==p2._y) && (_z==p2._z) );
	}
	
	  public boolean equalsXY (Point3D p)
	    {return p._x == _x && p._y == _y;}
	    
  //  public String toString() {return "[" + (int)_x + "," + (int)_y+","+_z+"]";}
    public String toString(boolean all) {
	if(all) return "[" + _x + "," +_y+","+_z+"]";
	else return "[" + (int)_x + "," + (int)_y+","+(int)_z+"]";
    } 
    public String toFile()  {return _x+","+_y+","+_z;}
    
    public String toFile1()  {return "Point3D "+_x+" "+_y+" "+_z;}
    /**
     * return true iff this.x < p.x && this.y<p.y
     * @param p
     * @return
     */
    public boolean smallerThan2D(Point3D p) {
    	boolean ans = false;
    	if(p==null) {throw new RuntimeException("Error: smallerThan(Point3D p) - got a null value for p --> error!");}
    	
    	if(this.x()<p.x() && this.y()<p.y()) {ans = true;}
    	return ans;
    }
    /**
     * return true iff this.smallerThan3D(p) && this.z() < p.z();
     * @param p
     * @return
     */
    public boolean smallerThan3D(Point3D p) {
    	boolean ans = smallerThan2D(p);
    	ans = ans  & (this.z() < p.z());
    	return ans;
    }
    /**
     * return p.smallerThan2D(this);
     * @param p
     * @return
     */
    public boolean biggerThan2D(Point3D p) {
    	return p.smallerThan2D(this);
    }
    /**
     * return p.smallerThan3D(this)
     * @param p
     * @return
     */
    public boolean biggerThan3D(Point3D p) {
    	return p.smallerThan3D(this);
    }
}