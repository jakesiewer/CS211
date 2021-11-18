import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.lang.Object.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Stub{    
    
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
            Brain mybrain = new Brain(Arrays.copyOf(dictionary, dictionary.length), blackout);
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
    public String hiddenWord="_____";
    public Stack<Character> chosenletters = new Stack<>();
    
    public Brain(String[] wordlist, String target){
        dictionary = wordlist;
        hiddenWord = target;
    }
      
    public char guessLetter(){
        
        //fill this in so as to guess the hiddenWord with the least number of guesses
        //keep track of your used letters so you're using a new letter
        //check the hiddenWord so you can see what letters are known and which ones are unknown 
        //unknown characters are marked with an underscore ("_")
        //this method should return a character that is a good guess
        Random r = new Random();
        
        boolean chosen = true;
        char letter = '_';
        chosenletters.push('x');
        chosenletters.push('z');
        
        while(chosen)
        {
            chosen = false;
            letter = (char)(r.nextInt(26)+'a');
            for(int i = 0; i < chosenletters.size(); i++)
            {
                if(chosenletters.elementAt(i) == letter)
                {
                    chosen = true;
                    break;
                }
            }
            if(chosen == false)
                chosenletters.push(letter);
        }
 
        for(int i = 0; i < dictionary.length; i++)
        {
            for(int j = 0; j < hiddenWord.length(); j++)
            {
                if(dictionary[i].length() != hiddenWord.length())
                {
                    dictionary[i] = "";
                    break;
                }
                else if((dictionary[i].charAt(j) != (hiddenWord.charAt(j))) && (hiddenWord.charAt(j) != ('_')))
                {
                    dictionary[i] = "";
                    break;
                }
            }
        }
       
        var count = 0;
        for(int i = 0; i < dictionary.length; i++)
        {
            if(dictionary[i] == "")
                count++;
        }
        
        count = dictionary.length - count;
        if(count == 1)
        {
            for(int i = 0; i < dictionary.length; i++)
            {
                if(!dictionary[i].equals(""))
                {
                    for(int j = 0; j < hiddenWord.length(); j++)
                    {
                        if(hiddenWord.charAt(j) == '_')
                        {
                            //System.out.println(hiddenWord);
                            //System.out.println(dictionary[i].charAt(j));
                            return dictionary[i].charAt(j);
                            
                        }
                    }
                }
            }
        }

        return letter;
    }        
}