public class NBody {
    public static double readRadius(String name) {
        In input = new In(name);
        input.readInt();
        return input.readDouble();
    }
    public static Planet[] readPlanets(String name) {
        In input = new In(name);
        int num = input.readInt();
        input.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            planets[i] = new Planet(input.readDouble(), input.readDouble(), 
            input.readDouble(), input.readDouble(), input.readDouble(), input.readString());
        }
        return planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(- radius, radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        double time = 0;
        while (time < T) {
            int len = planets.length;
            double[] xForces = new double[len];
            double[] yForces = new double[len];
            for (int i = 0; i < len; i++ ) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < len; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}