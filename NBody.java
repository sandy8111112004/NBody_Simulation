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
        double radius = NBody.readRadius(fileName);

    }

}