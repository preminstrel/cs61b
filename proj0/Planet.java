public class Planet{
	public double xxPos;  //x position
	public double yyPos;  //y position
	public double xxVel;  //velocity in the x direction
	public double yyVel;  //velocity in the y direction
	public double mass;   //mass
	public String imgFileName;  //e.g. jupiter.gif
	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	//calculate the distance beteween two planet
	public double calcDistance(Planet p){
		double distance;
		double dx=Math.abs(xxPos-p.xxPos);
		double dy=Math.abs(yyPos-p.yyPos);
		distance=Math.sqrt(dx*dx+dy*dy);
		return distance;
	}
	public double calcForceExertedBy(Planet p){
		double force;
		double gravaitynum = 6.67e-11;
		force=gravaitynum*mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
		return force;
	}
	public double calcForceExertedByX(Planet p){
		double force=this.calcForceExertedBy(p);
		double xforce=force*(p.xxPos-xxPos)/this.calcDistance(p);
		return xforce;
	}
	public double calcForceExertedByY(Planet p){
		double force=this.calcForceExertedBy(p);
		double yforce=force*(p.yyPos-yyPos)/this.calcDistance(p);
		return yforce;
	}
	
    public double calcNetForceExertedByX(Planet[] others) {
        double totalForce = 0;
        for (Planet other : others) {
            if (this.equals(other))
                continue;
            totalForce += calcForceExertedByX(other);
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double totalForce = 0;
        for (Planet other : others) {
            if (this.equals(other))
                continue;
            totalForce += calcForceExertedByY(other);
        }
        return totalForce;
    }
    
    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        double newxxVel = this.xxVel + dt * aX;
        double newyyVel = this.yyVel + dt * aY;
        this.xxVel = newxxVel;
        this.yyVel = newyyVel;
        this.xxPos = this.xxPos + dt * newxxVel;
        this.yyPos = this.yyPos + dt * newyyVel;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}