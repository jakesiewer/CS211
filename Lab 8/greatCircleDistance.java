import java.util.*;
public class greatCircleDistance
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        double lat_1 = scan.nextDouble();
        double long_1 = scan.nextDouble();
        double lat_2 = scan.nextDouble();
        double long_2 = scan.nextDouble();

        System.out.println(greatCircleDist(lat_1, long_1, lat_2, long_2));
    }

    public static int greatCircleDist(double lat_1, double long_1, double lat_2, double long_2)
    {
        // double result = 0;

        // lat_1 = Math.toRadians(lat_1); 
        // lat_2 = Math.toRadians(lat_2); 
        // long_1 = Math.toRadians(long_1); 
        // long_2 = Math.toRadians(long_2); 
        // result = 2 * Math.asin(Math.sqrt(Math.sqrt(Math.sin((lat_1 - lat_2) / 2)) + Math.cos(lat_1) * Math.cos(lat_2) * Math.sqrt(Math.sin((long_1 - long_2) / 2))));
        // result = Math.toDegrees(result);
        // return result;

        double lat = Math.toRadians(lat_2 - lat_1);
        double lon = Math.toRadians(long_2 - long_1);
        lat_1 = Math.toRadians(lat_1);
        lat_2 = Math.toRadians(lat_2);

        double result = 6371 * (2 * Math.asin(Math.sqrt(Math.pow(Math.sin(lat / 2), 2) + Math.cos(lat_1) * Math.cos(lat_2) * Math.pow(Math.sin(lon / 2), 2))));

        result = Math.round(result / 100) * 100;
        int intresult = (int)result;
        
        return intresult;
    }
}