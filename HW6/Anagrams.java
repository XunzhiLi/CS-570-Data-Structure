package hw6;

/*Name:Xunzhi Li
* ID:10457500
* Homework 6 Hash
* */
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Anagrams {
    final Integer[] primes={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
            41, 43, 47, 53, 59, 61,67, 71, 73, 79, 83, 89, 97, 101};
    final char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l',
                            'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    Map<Character,Integer> letterTable;
    Map<Long,ArrayList<String>> anagramTable;

    //the constructor for the class Anagrams
    public Anagrams (){
        buildLetterTable();
    }

    //should build the hash table letterTable
    private void buildLetterTable(){
        letterTable = new HashMap<>();
        for (int i =0;i<primes.length;i++) {
            letterTable.put(letters[i],primes[i]);
        }
    }

    //add  word with its hashcode to the anagramTable(hashtable)
    private void addWord(String s){
        //get string's hashcode
        long hashcode = myHashCode(s);

        //if anagramTable is still null, then create it and add the first key-value pair.
        if(anagramTable == null){
            anagramTable = new HashMap<>();
            ArrayList<String> Anagrams_list = new ArrayList<>();
            Anagrams_list.add(s);
            anagramTable.put(hashcode,Anagrams_list);
        }else if (anagramTable.get(hashcode)==null){
            ArrayList<String> Anagrams_list = new ArrayList<>();
            Anagrams_list.add(s);
            anagramTable.put(hashcode,Anagrams_list);
        }else{
            //there is a existing key as same as s's hashcode
            ArrayList<String> present_Anagrams_list = anagramTable.get(hashcode);
            present_Anagrams_list.add(s);
            anagramTable.put(hashcode,present_Anagrams_list);
        }
    }

    //method for getting string's hashcode
    private Long myHashCode(String s){
        if(s == null){
            throw new NumberFormatException("string is null");
        }else{
            long hashcode=1;
            for (int i=0;i<s.length();i++){
                char s_lower = s.toLowerCase().charAt(i);
                if (s_lower >= 'a' && s_lower <= 'z') {
                    hashcode *= letterTable.get(s.charAt(i));
                }else{
                    throw new NumberFormatException("there is non-english-letter in txt file");
                }
            }
            return hashcode;}
    }

    //find the anagramTable with largest number of anagrams.
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long, ArrayList<String>>> max_anagram = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
        long max_size = 0;
        if(anagramTable ==null){
            throw new NumberFormatException("txt file is null");
        }else{
            Set<Map.Entry<Long, ArrayList<String>>> anagram_set = anagramTable.entrySet();
            for (Map.Entry<Long, ArrayList<String>> entry : anagram_set) {
                ArrayList<String> anagram_arraylist = entry.getValue();
                long size = anagram_arraylist.size();
                if (size > max_size) {
                    max_anagram.clear();
                    max_anagram.add(entry);
                    max_size = size;
                } else if (size == max_size) {
                    max_anagram.add(entry);
                }
            }
            return max_anagram;}
    }

    //read each string from provided 'txt' file
    public void processFile(String s) throws IOException{
        FileInputStream fstream = new FileInputStream( s );
        BufferedReader br = new BufferedReader ( new InputStreamReader( fstream ));
        String strLine ;
        while (( strLine = br. readLine ()) != null ) {
            System.out.println();
            this.addWord ( strLine );
        }
        br.close ();
    }

    public static void main(String[] args) {
        Anagrams a = new Anagrams();
        final long startTime = System.nanoTime ();
        try {
            a.processFile("words_alpha.txt");
        }catch (IOException e1){
            e1.printStackTrace ();
        }
        ArrayList < Map.Entry < Long , ArrayList < String >>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime () - startTime;
        final double seconds = (( double ) estimatedTime / 1000000000 );
        System.out.println ( " Time : "+ seconds );
        System.out.println ( " Key of max anagrams : "+ maxEntries.get(0).getKey());
        System.out.println ( " List of max anagrams : "+ maxEntries.get(0).getValue());
        System.out.println ( " Length of list of max anagrams : "+ maxEntries.get(0).getValue().size());

}
}
