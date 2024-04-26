package wordle;
import java.io.*;
import java.util.*;

public class Huffman{
    HashMap<Character, String> encodingValues = new HashMap<Character, String>();

    public HashMap<Character, Integer> frequencyGetter(String filename){
        HashMap<Character, Integer> wockySlosh = new HashMap<Character, Integer>();

        try{
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            int podge;

            while((podge = reader.read())!=-1){
                if(wockySlosh.get((char)podge) !=null){
                    wockySlosh.put((char)podge, wockySlosh.get((char)podge) + 1);
                }else{
                    wockySlosh.put((char)podge, 0);
                }
            }
        }catch(Exception e){
            System.out.println("Error");
        }

        return wockySlosh;
    }

    public String readingFile(String filename){
        StringBuilder output = new StringBuilder();
        try{
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(reader.read()!=-1){
                output.append(reader.readLine());
                output.append('\n');
            }
        }catch(Exception e){
            System.out.println("error");
        }
        return output.toString();
    }

    public void printCode(HuffmanNode root, String s){
        if(root.left==null && root.right==null){
            System.out.println(root.c + ":" + s);
            encodingValues.put(root.c, s);
            return;
        }
        
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static String encode(String text, HashMap<Character, String> huffmanCodes){
        StringBuilder encodedText = new StringBuilder();
        System.out.println("my name jef " + huffmanCodes);
        for(char c : text.toCharArray()){
            //System.out.println("encoding - " + huffmanCodes.containsKey('\n'));
            encodedText.append(huffmanCodes.get(c));
        }
        return encodedText.toString();
    }

    public static void writeEncodedData(String encodedText, String outputPath){
        try (OutputStream outputStream = new FileOutputStream(outputPath)) {
            for (int i = 0; i < encodedText.length(); i += 8) {
                String byteString = encodedText.substring(i, Math.min(i + 8, encodedText.length()));
                int byteValue = Integer.parseInt(byteString, 2);
                outputStream.write(byteValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Huffman huff = new Huffman();
        String filename="wordle/resources/dictionary.txt";
        HashMap<Character, Integer> wockySlosh = huff.frequencyGetter(filename);
        String alphabet = "\nabcdefghijklmnopqrstuvwxyz";
        int length=wockySlosh.size();
        int[] charFrequency = new int[length];
        char[] charArray = new char[length];
        System.out.println(wockySlosh);

        for(int b=0;b<length;b++){
            int highest=0;
            int position=0;
            for(int a=0;a<length;a++){
                if(wockySlosh.get(alphabet.charAt(a)) > highest){
                    highest=wockySlosh.get(alphabet.charAt(a));
                    position=a;
                }
            }
            charFrequency[b]=highest;
            charArray[b]=alphabet.charAt(position);
            wockySlosh.put(alphabet.charAt(position), 0);
        }

        System.out.print("test ");
        System.out.println(charArray);



        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(length, new myComparator());

        for(int a=0;a<length;a++){
            HuffmanNode hn = new HuffmanNode();
            hn.c=charArray[a];
            hn.data=charFrequency[a];
            hn.left=null;
            hn.right=null;
            q.add(hn);
        }
        HuffmanNode root = null;
        //System.out.println("peek: " + q.peek().c);
        while(q.size()>1){
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            //System.out.println("first - " + x.c + " second - " + y.c);
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left=x;
            f.right=y;
            root=f;
            q.add(f);
        }

        huff.printCode(root, "");
        //System.out.println(huff.encodingValues);

        //String test = "i\nlike\neating\napples\nand\ngrapes";
        String encoded = encode(huff.readingFile(filename), huff.encodingValues);
        //writeEncodedData(encoded,"../wordle/resources/output.bin");
        System.out.println(encoded);
        try{
            FileWriter myWriter = new FileWriter("filename.bin");
            myWriter.write(encoded);
            myWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}

class HuffmanNode{
    int data;
    char c;

    HuffmanNode left;
    HuffmanNode right;
}

class myComparator implements Comparator<HuffmanNode>{
    public int compare(HuffmanNode x, HuffmanNode y){
        return x.data - y.data;
    }
}
