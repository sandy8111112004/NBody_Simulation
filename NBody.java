public class NBody{
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int numPlanets =in.readInt();
        double radius = in.readDouble();
        
        return radius;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        Body[] bArr = new Body[numPlanets];

        for (int i = 0;i < numPlanets;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            Body b = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            bArr[i] = b;
        }

        return bArr;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bArr = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);
        double currentTime = 0;
        double[] xForces= new double[bArr.length];
        double[] yForces=new double[bArr.length];

        StdAudio.close();
        StdAudio.loop("audio/2001.mid");
        StdDraw.enableDoubleBuffering();
        //draw background image
        StdDraw.setScale((-1*radius),radius);
        StdDraw.clear();
        while (currentTime <= T){
            StdDraw.picture(0,0,"./images/starfield.jpg");
            for(int i=0; i<bArr.length ;i++){
                yForces[i]=bArr[i].calcNetForceExertedByY(bArr);
                xForces[i]=bArr[i].calcNetForceExertedByX(bArr);
            }
            for (int i =0;i<bArr.length;i++){
                bArr[i].update(dt,xForces[i], yForces[i]);
                bArr[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            currentTime += dt;
        }

        StdOut.printf("%d\n", bArr.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bArr.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bArr[i].xxPos, bArr[i].yyPos, bArr[i].xxVel,
                        bArr[i].yyVel, bArr[i].mass, bArr[i].imgFileName);   
        }    
       
        StdAudio.close();
    }

}