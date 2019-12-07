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

        int i = 0;
        while(!in.isEmpty()){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            Body b = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            bArr[i] = b;
            i++;
        }

        return bArr;
    }

}