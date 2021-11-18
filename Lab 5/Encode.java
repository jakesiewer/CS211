import java.util.Scanner;
public class Encode
{

    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);
        int input1 = scan.nextInt();
        int input2 = scan.nextInt();
        System.out.println(swap(input1));
    }

    public static int swap (int value)
    {
        int b1 = (value >> 0) & 0xff;
        int b2 = (value >> 8) & 0xff;
        int b3 = (value >> 16) & 0xff;
        int b4 = (value >> 24) & 0xff;

        return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
    }
}