public class NBody{
	/*NBody is a class that will actually run your simulation. 
	This class will have NO constructor. 
	The goal of this class is to simulate a universe specified in one of the data files. */
	public static double readRadius(String planetsTxtPath){
		In in = new In(planetsTxtPath);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
    public static Planet[] readPlanets(String str){
        In in = new In(str);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        in.readDouble();
        for (int i = 0; i < num ; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = planet;
        }
        return planets;
    }

    public static String imageToDraw = "./images/starfield.jpg";
    public static void main(String[] args) {
    	double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename=args[2];
        //filename = "./data/planets.txt";
        double r = NBody.readRadius(filename);
        StdDraw.setScale(-r, r);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0,0,imageToDraw);
        Planet[] all=NBody.readPlanets(filename);
        for(Planet one:all){
            one.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time=0;
        for(;time<T;time+=dt){
            double[] xForces=new double[5];
            double[] yForces=new double[5];
            for(int i=0;i<5;i++){
                xForces[i]=all[i].calcNetForceExertedByX(all);
                yForces[i]=all[i].calcNetForceExertedByY(all);
            }
            for(int i=0;i<5;i++){
                all[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,imageToDraw);
            for(Planet one:all){
            one.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", all.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < all.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", all[i].xxPos, all[i].yyPos,
                    all[i].xxVel, all[i].yyVel, all[i].mass, all[i].imgFileName);
        }
    }
}