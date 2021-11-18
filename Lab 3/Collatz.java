import java.util.*;
public class Collatz
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        int n3 = scan.nextInt();
        int[] a1 = new int[n2 - n1 + 1];
        int[] a2 = new int[n2 - n1 + 1];
        int count = n1;
        
        for(int i = 0; i < a1.length; i++)
        {
            a1[i] = count;
            a2[i] = count;
            count++;
        }

        for(int i = 0; i < a1.length; i++)
        {
            int n = a1[i];
            a1[i] = 0;                                         
            while (n != 1) 
            {
                if (n % 2 == 0) 
                {
                    n = (n / 2);
                } 
                else 
                {
                    n = (3 * n + 1);
                }
                a1[i]++;
            }
        }

        CollatzSort(a1, a2);
        // quickSort(a1, a2);
        System.out.println(a2[n3 - 1]);
        // System.out.println(a1.length-1);
    }
    
    public static void CollatzSort(int[] theArray1, int[] theArray2)
    {
        int temp = 0;
        for(int i = 1; i < theArray1.length; i++)
        {
            for(int j = 0; j < (theArray1.length) - i; j++)
            {
                if(theArray1[j] > theArray1[j + 1])
                {
                    temp = theArray1[j];
                    theArray1[j] = theArray1[j + 1];
                    theArray1[j + 1] = temp;
                    temp = theArray2[j];
                    theArray2[j] = theArray2[j + 1];
                    theArray2[j + 1] = temp;
                }
            }
        }
    }
    public static void quickSort(int[] intArray, int[] a2) {
        recQuickSort(intArray, a2, 0, intArray.length - 1);
      }
    
      public static void recQuickSort(int[] intArray, int[] a2, int left, int right) {
        int size = right - left + 1;
        if (size <= 3)
          manualSort(intArray, a2, left, right);
        else {
          double median = medianOf3(intArray, a2, left, right);
          int partition = partitionIt(intArray, a2, left, right, median);
          recQuickSort(intArray, a2, left, partition - 1);
          recQuickSort(intArray, a2, partition + 1, right);
        }
      }
    
      public static int medianOf3(int[] intArray, int[] a2, int left, int right) {
        int center = (left + right) / 2;
    
        if (intArray[left] > intArray[center])
          swap(intArray, left, center);
          swap(a2, left, center);
    
        if (intArray[left] > intArray[right])
          swap(intArray, left, right);
          swap(a2, left, right);
    
        if (intArray[center] > intArray[right])
          swap(intArray, center, right);
          swap(a2, center, right);
    
        swap(intArray, center, right - 1);
        swap(a2, center, right - 1);
        
        return intArray[right - 1];
      }
    
      public static void swap(int[] intArray, int dex1, int dex2) {
        int temp = intArray[dex1];
        intArray[dex1] = intArray[dex2];
        intArray[dex2] = temp;
      }
    
      public static int partitionIt(int[] intArray, int[] a2, int left, int right, double pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;
    
        while (true) {
          while (intArray[++leftPtr] < pivot)
            ;
          while (intArray[--rightPtr] > pivot)
            ;
          if (leftPtr >= rightPtr)
            break;
          else
            swap(intArray, leftPtr, rightPtr);
            swap(a2, leftPtr, rightPtr);
        }
        swap(intArray, leftPtr, right - 1);
        swap(a2, leftPtr, right - 1);
        return leftPtr;
      }
    
      public static void manualSort(int[] intArray, int[] a2, int left, int right) {
        int size = right - left + 1;
        if (size <= 1)
          return;
        if (size == 2) {
          if (intArray[left] > intArray[right])
            swap(intArray, left, right);
            swap(a2, left, right);
          return;
        } else {
          if (intArray[left] > intArray[right - 1])
            swap(intArray, left, right - 1);
            swap(a2, left, right - 1);
          if (intArray[left] > intArray[right])
            swap(intArray, left, right);
            swap(a2, left, right);
          if (intArray[right - 1] > intArray[right])
            swap(intArray, right - 1, right);
            swap(a2, right - 1, right);
        }
    }
}