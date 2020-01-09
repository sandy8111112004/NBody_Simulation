public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static double G = 6.67*Math.pow(10,-11);

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
        double dis=0;
        dis = Math.pow((Math.pow((this.xxPos - b.xxPos),2)+Math.pow((this.yyPos-b.yyPos), 2)),0.5);

        return dis; 
    }
   
    public double calcForceExertedBy(Body b){
        double totalF; 
        totalF = G * this.mass * b.mass / Math.pow(this.calcDistance(b),2);

        return totalF;
    }

    public double calcForceExertedByX(Body b){
        double Fx;
        Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);

        return Fx;
    }

    public double calcForceExertedByY(Body b){
        double Fy;
        Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);

        return Fy;
    }

    public double calcNetForceExertedByX(Body[] bArr){
        double netFx = 0;

        for (int i =0;i<bArr.length;i++){
            if(!this.equals(bArr[i])){
                netFx += this.calcForceExertedByX(bArr[i]);
            }
        }

        return netFx;
    }

    public double calcNetForceExertedByY(Body[] bArr){
        double netFy = 0;

        for (int i =0;i<bArr.length;i++){
            if (!this.equals(bArr[i])){
                netFy += this.calcForceExertedByY(bArr[i]);
            }
        }

        return netFy;
    }

    public void update(double dt, double fX,double fY){
        double aX = 0;
        double aY = 0;
        
        aX = fX/this.mass;
        aY = fY/this.mass;
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }

}