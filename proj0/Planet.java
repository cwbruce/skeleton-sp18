public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos =yP;
        xxVel = xV;
        yyVel =yV;
        mass = m;
        imgFileName =img;
    }
    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet target) {
        return Math.sqrt(Math.pow(xxPos - target.xxPos, 2) + Math.pow(yyPos - target.yyPos, 2));
    }
    public double calcForceExertedBy(Planet target) {
        return Planet.G * mass * target.mass / Math.pow(this.calcDistance(target), 2);
    }
    public double calcForceExertedByX(Planet target) {
        return this.calcForceExertedBy(target) * (target.xxPos - xxPos) / this.calcDistance(target);
    }
    public double calcForceExertedByY(Planet target) {
        return this.calcForceExertedBy(target) * (target.yyPos - yyPos) / this.calcDistance(target);
    }
    public double calcNetForceExertedByX(Planet[] targets) {
        double net = 0;
        for (Planet i : targets) {
            if (!this.equals(i)) {
                net += this.calcForceExertedByX(i);
            }
        }
        return net;
    }
    public double calcNetForceExertedByY(Planet[] targets) {
        double net = 0;
        for (Planet i : targets) {
            if (!this.equals(i)) {
                net += this.calcForceExertedByY(i);
            }
        }
        return net;
    }
    public void update(double dt, double fX, double fY) {
        xxVel += fX / mass * dt;
        yyVel += fY / mass * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
