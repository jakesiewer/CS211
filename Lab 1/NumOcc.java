import java.util.*;
public class NumOcc
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String st = scan.nextLine();
        scan.close();
        int[] count = new int[256];
        LinkedList<String> l1 = new LinkedList<>();

        for(int i = 0; i < st.length(); i++)
        {
            count[(int)st.charAt(i)]++;
        }

        for(int i = 0; i < 256; i++)
        {
            if(count[i] != 0)
            {
                String s = Integer.toString(count[i]);
                l1.add((char)i + " " + s);
            }
        }
        String[] arr = l1.toArray(new String[l1.size()]);

        int n = arr.length; 
	    
        for (int i = 0; i < n-1; i++)
        {
            for (int j = 0; j < n-i-1; j++) 
            {
                if (arr[j].charAt(2) < arr[j+1].charAt(2) && arr[j].length() == 3) 
                { 
                    String temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                }
                //check if there is a letter that has occurred in double digits and swap if so
                if(arr[j].length() == 4)
                {
                    String str1 = arr[j].substring(arr[j].length()-2);
                    int num2 = Integer.parseInt(str1);
                    if(num2 > arr[j+1].charAt(2))
                    {
                        String temp = arr[j]; 
                        arr[j] = arr[j+1]; 
                        arr[j+1] = temp; 
                    }
                }

                else if(arr[j].charAt(2) == arr[j+1].charAt(2))
                {
                    if(arr[j].charAt(0) > arr[j+1].charAt(0))
                    {
                        String temp = arr[j]; 
                        arr[j] = arr[j+1]; 
                        arr[j+1] = temp; 
                    }
                }
            }
        }
        
        for(int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }
}