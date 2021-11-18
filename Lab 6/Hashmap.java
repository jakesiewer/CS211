
import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.lang.Object.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashmap{    
    
    public static void main (String[] args){
        Scanner myscanner = new Scanner(System.in);
        int items = myscanner.nextInt()-1;
        myscanner.nextLine();
        String[] contents = new String[items];
        for(int i=0;i<items;i++){
            contents[i]=myscanner.nextLine();
        }
        String hash = myscanner.nextLine();
        int size=99991;
        Solution mysolution = new Solution();
        String[] hashtable=mysolution.fill(size, contents);
        HashTable mytable = new HashTable(hashtable);

        Solution mysolution2 = new Solution(); //prevents cheating by using memory
        for(int i=0;i<items;i++){
            int rand = (int)(Math.random()*items);
            String temp = contents[i];
            contents[i]=contents[rand];
            contents[rand]=temp;
        }
        int total=0;
        for(int i=0;i<items;i++){
            int slot = mysolution2.find(size, mytable, contents[i]);
            if(!hashtable[slot].equals(contents[i])){
                System.out.println("error!");
            }
        }      
        System.out.println("Collisions: " + mytable.gettotal());
        try{
            System.out.println("Here is your receipt: "+sha256(hash+mytable.gettotal()));
        }catch(NoSuchAlgorithmException e){};
    }
    
    public static String sha256(String input) throws NoSuchAlgorithmException {
        byte[] in = hexStringToByteArray(input);
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(in);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if(len%2==1){
            s=s+"@";
            len++;
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
    
    
class HashTable{
    
    private String[] hashTable;
    private long total=0;

    public HashTable(String[] input){
        hashTable = input;
    }

    public boolean check(int slot, String check){
        if(hashTable[slot].equals(check)){
            return true;
        }else{
            total++;
            return false;
        }
    }
    
    public long gettotal(){
        return total;
    }
} 

      
class Solution{
      
    public int find(int size, HashTable mytable, String word){
        
        //fill this in so as to minimize collisions
        //takes in the HashTable object and the word to be found
        //the only thing you can do with the HashTable object is call "check"
        //this method should return the slot in the hashtable where the word is 
        int k = 0;
        
        for(long i = 0; i < word.length(); i++)
        {
            int num = (int)i;
            char c = word.charAt(num);
            int asc = (int) c;
                
            k = (599 * (k + size) + asc) % size;
        }
        
        boolean b = true;
        
        while(b)
        {
            if(mytable.check(k, word))
            {
                b = false;
            }
            else
            {
                k += size;
                k = (k * 19) % size;
            }
        }

        return k;
    }
    
    public String[] fill(int size, String[] array)
    {
        
        //takes in the size of the hashtable, and the array of Strings to be placed in the hashtable
        //this should add all the words into the hashtable using some system
        //then it should return the hashtable array
        String[] hashtable = new String[size];
        for(int i=0;i<size;i++)
        {
            hashtable[i]="";
        }
        
        for(String s: array)
        {
            long k = 0;
            for(long i = 0; i < s.length(); i++)
            {
                int num = (int) i;
                char c = s.charAt(num);
                int asc = (int) c;
                
                long bigascii = (long)asc;
                long bigsize = (long)size;
                
                k = (599 * (k + bigsize) + bigascii) % bigsize;
            }
            boolean b = true;
            
            while(b)
            {
                long bigsize = (long)size;
                
                if(hashtable[(int)k] == "")
                {
                    b = false;
                }
                else
                {
                    k += bigsize;
                    k = (k * 19) % bigsize;
                }
            }
            int k1 = (int)k;
            hashtable[k1] = s;
        }
        return hashtable;
    }  
}