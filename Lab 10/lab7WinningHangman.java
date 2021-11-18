/* Calculated the best guesses to win a hangman game based on words randomly picked from a dictionary.*/

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.lang.Object.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class lab7WinningHangman{
    public static void main (String[] args){
        Scanner myscanner = new Scanner(System.in);
        int num = Integer.parseInt(myscanner.nextLine());
        String[] dictionary = new String[num];
        for(int i=0;i<num;i++){
            dictionary[i]=myscanner.nextLine();
        }
        String hash = dictionary[num-1]+(int)(Math.random()*100);
        int games = 100;
        int score = 0;
        for(int x=0;x<games;x++){

            Random r = new Random();
            String target = dictionary[r.nextInt(num)];

            String blackout="";
            for(int i=0;i<target.length();i++){
                blackout=blackout+"_";
            }

            Brain mybrain = new Brain(dictionary, blackout);
            int lives=8;

            boolean running = true;

            while(running){
                char guess = mybrain.guessLetter();
                String original = mybrain.hiddenWord;
                char[] arrayform = original.toCharArray();
                for(int i=0;i<target.length();i++){
                    if(target.charAt(i)==guess){
                        arrayform[i]=guess;
                    }
                }
                String newform = "";
                for(int i=0;i<target.length();i++){
                    newform=newform+arrayform[i];
                }
                mybrain.hiddenWord=newform;
                if(newform.equals(original)){
                    lives=lives-1;
                }
                if(lives==0){
                    running=false;
                }
                if(mybrain.hiddenWord.equals(target)){
                    running=false;
                    score=score+1;
                }
            }
        }
        System.out.println("You got "+score+" correct out of 100");
        try{
            System.out.println("Your Receipt: "+sha256(hash+score));
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
class Brain{
    
    public String[] dictionary;
    public String hiddenWord="____";
    public String alpha="abcdefghijklmnopqrstuvwxyz";
    public int []freq=new int[26];
    public String guessed="";
    public List<String> newDict = new ArrayList<String>();
    
    public Brain(String[] wordlist, String target){
        dictionary = wordlist;
        hiddenWord = target;
        
        for( int i=0; i<dictionary.length; i++ )
        {
            if(dictionary[i].length()==hiddenWord.length())
            {
                newDict.add(dictionary[i]);
            }
        }
    }
      
    public char guessLetter(){
        
        //fill this in so as to guess the hiddenWord with the least number of guesses
        //keep track of your used letters so you're using a new letter
        //check the hiddenWord so you can see what letters are known and which ones are unknown 
        //unknown characters are marked with an underscore ("_")
        //this method should return a character that is a good guess

        for(int i=0; i<26; i++)
        {
            freq[i]=0;
        }
        String nhiddenWord=hiddenWord.replace('_', '.');
        //System.out.println(hiddenWord);


            int max=0;
            char m='e';
        int x=1;
        while(x<newDict.size())
        {
                    
            String word=newDict.get(x);
           // System.out.println(word);
                if(word.matches(nhiddenWord))
                {
                    for(int j=0; j<word.length(); j++)
                    {
                        char b=word.charAt(j);
                        if(guessed.indexOf(b)==-1)
                        {
                            int c=alpha.indexOf(b);
                            freq[c]++;
                                
                                if(freq[c]>max)
                                {
                                    max=freq[c];
                                    m=alpha.charAt(c);
                                }
                            
                        }
                    }
                    x++;
                }
                else
                {
                    newDict.remove(x);
                }
        }
            guessed+=m;
         
            return m;
        }
}
