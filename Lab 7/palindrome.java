import java.util.Scanner;
public class palindrome 
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int total = 0;
        
        for(int i = 2; i < 11; i++)
        {
           if(isPalindrome(decimalToBase(input, i)) == true)
           {
               total++;
           }
        }

        System.out.println(total);
    }

    public static boolean isPalindrome(int num)
    {
        int n = num;
        int rev = 0;
        int rem;
        boolean isPalin = false;

        while(num != 0)
        {
            rem = num % 10;
            rev = (rev * 10) + rem;
            num /= 10;
        }
        if(n == rev)
        {
            isPalin = true;
        }
        else
        {
            isPalin = false;
        }
        return isPalin;
    }
    
    public static int decimalToBase(int num, int base)
    {
        int n;
        String result = "";

        while(num > 0)
        {
            n = num % base;
            result = n + result;
            num = num / base;
        }
        int output = Integer.parseInt(result);
        return output;
    }
}

