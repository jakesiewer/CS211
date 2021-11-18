public class quicksort {
   public static void main(String[] args) {
     int[] intArray = { 1, 9, 2, 8, 3, 7, 4, 6, 5 };
     
     medianOf3(intArray, 0, intArray.length - 1);
     for (int i : intArray) {
       System.out.print(i + " ");
     }
   }
 
   public static void quickSort(int[] intArray) {
     recQuickSort(intArray, 0, intArray.length - 1);
   }
 
   public static void recQuickSort(int[] intArray, int left, int right) {
     int size = right - left + 1;
     if (size <= 3)
       manualSort(intArray, left, right);
     else {
       double median = medianOf3(intArray, left, right);
       int partition = partitionIt(intArray, left, right, median);
       recQuickSort(intArray, left, partition - 1);
       recQuickSort(intArray, partition + 1, right);
     }
   }
 
   public static int medianOf3(int[] intArray, int left, int right) {
     int center = (left + right) / 2;
 
     if (intArray[left] > intArray[center])
       swap(intArray, left, center);
 
     if (intArray[left] > intArray[right])
       swap(intArray, left, right);
 
     if (intArray[center] > intArray[right])
       swap(intArray, center, right);
 
     swap(intArray, center, right - 1);
     return intArray[right - 1];
   }
 
   public static void swap(int[] intArray, int dex1, int dex2) {
     int temp = intArray[dex1];
     intArray[dex1] = intArray[dex2];
     intArray[dex2] = temp;
   }
 
   public static int partitionIt(int[] intArray, int left, int right, double pivot) {
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
     }
     swap(intArray, leftPtr, right - 1);
     return leftPtr;
   }
 
   public static void manualSort(int[] intArray, int left, int right) {
     int size = right - left + 1;
     if (size <= 1)
       return;
     if (size == 2) {
       if (intArray[left] > intArray[right])
         swap(intArray, left, right);
       return;
     } else {
       if (intArray[left] > intArray[right - 1])
         swap(intArray, left, right - 1);
       if (intArray[left] > intArray[right])
         swap(intArray, left, right);
       if (intArray[right - 1] > intArray[right])
         swap(intArray, right - 1, right);
     }
   }
 
 }