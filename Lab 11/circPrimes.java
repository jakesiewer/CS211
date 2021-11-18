import java.util.*;

public class circPrimes {
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);
        int n = 11;
        int total = 4;      //primes below 10
        int input = scan.nextInt();
        
        while(n < input)
        {
            if (isCirc(n))
            {
                total++;
            }
            n = getNextPrime(n);
        }
        System.out.println(total);
    }
    public static boolean isCirc(int n)
    {
        boolean circ = true;
        int length = (int)Math.log10((long)n) + 1;
        
        int i = 0;
        while(i < length - 1 && circ)
        {
            n = rotateNum(n, length);
            circ = isPrime(n);
            i++;
        }
        return circ;
    }
    
    public static int rotateNum(int n, int length)
    {
        int right = n % 10;
        int leftofright = (n - right) / 10;
        return (right * (int)(Math.pow(10, length - 1))) + leftofright;
    }
    
    public static int getNextPrime(int n)
    {
        boolean prime = false;
        
        while(!prime)
        {
            n += 2;
            prime = isPrime(n);
        }
        return n;
    }
    
    public static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0)
            return false;
            
        int sqrtn = (int)Math.sqrt(n) + 1;
        
        for(int i = 6; i <= sqrtn; i += 6)
        {
            if (n % (i - 1) == 0 || n % (i + 1) == 0)
                return false;
        }
        return true;
    }
    
    
    
}