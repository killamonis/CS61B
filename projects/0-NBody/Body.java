public class Body {
    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        return Math.sqrt(((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos)) + ((this.yyPos - b.yyPos) * (this.yyPos - b.yyPos)));
    }

    public double calcForceExertedBy(Body b){
        if (this.equals(b)){
            return 0.0;
        }
        return ((6.67e-11) * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b){
        return (this.calcForceExertedBy(b) * (b.xxPos - this.xxPos)) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        return (this.calcForceExertedBy(b) * (b.yyPos - this.yyPos)) / this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allB){
        double sum = 0.0;
        for(Body b: allB){
            if (this.equals(b)){
                continue;
            }
            sum = sum + this.calcForceExertedByX(b);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Body[] allB){
        double sum = 0.0;
        for(Body b: allB){
            if (this.equals(b)){
                continue;
            }
            sum = sum + this.calcForceExertedByY(b);
        }
        return sum;
    }

    public void update(double time, double fx, double fy){
        double accelx = fx / this.mass;
        double accely = fy / this.mass;
        this.xxVel = this.xxVel + (time * accelx);
        this.yyVel = this.yyVel + (time * accely);
        this.xxPos = this.xxPos + (time * this.xxVel);
        this.yyPos = this.yyPos + (time * this.yyVel);  
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}