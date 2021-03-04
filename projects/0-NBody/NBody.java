public class NBody {
    public static double readRadius(String file){
        In in = new In(file);

        int planets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file){
        In in = new In(file);
        int planets = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[planets];

        for (int i = 0; i < bodies.length; i = i + 1){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            Body b = new Body(xPos, yPos, xVel, yVel, mass, image);
            bodies[i] = b;
        }
        return bodies;
    }

    public static void main(String args[]){
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        
        for (Body b: bodies){
            b.draw();
        }

        StdDraw.enableDoubleBuffering();
        
        for (double t = 0; t <= T; t = t + dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            
            for (int i = 0; i < bodies.length; i = i + 1){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            
            for (int i = 0; i < bodies.length; i = i + 1){
                bodies[i].update(t, xForces[i], yForces[i]);
            }
            
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b: bodies){
                b.draw();
            }
            
            StdDraw.show();
            StdDraw.pause(50);
        }
        
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
    
}