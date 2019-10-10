import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class War {
    static RingBuffer player1 = new RingBuffer(52);
    static RingBuffer player2 = new RingBuffer(52);
    static RingBuffer handQueue = new RingBuffer(52);

    public static void main(String[] args) throws Exception {
        String values = "23456789TJQKA";
        Scanner sc = new Scanner(new File("war.dat"));
        while (sc.hasNextLine()) {
            String p1 = sc.nextLine();
            String p2 = sc.nextLine();
            String[][] data = new String[2][26];
            data[0] = p1.split(" ");
            data[1] = p2.split(" ");
            for (int i = 0; i < 26; i++) {
                player1.enqueue(values.indexOf(data[0][i].substring(0,1))+2);
                player2.enqueue(values.indexOf(data[1][i].substring(0,1))+2);
            }
            int hands;
            for (hands=0; hands<100000;hands++)
            {
                if (hasWon()) break;
                hand();
            }
            System.out.println(hands);
            System.out.println(hands >100000 ? "Tie" : player1.isEmpty() ? "Player 2" : "Player 1");
            player2.clear();
            player1.clear();
            handQueue.clear();

        }
            }
    public static void hand() throws Exception {
        if (!hasWon())
        {
             double p1 = player1.dequeue();
             double p2 = player2.dequeue();
             handQueue.enqueue(p1);
             handQueue.enqueue(p2);
             if (p1>p2)
             {
                 addAll(true);
                 return;
             }
             if (p2>p1){
                 addAll(false);
                 return;
             }
             boolean b = war();
             while(b)
             {
                 b= war();
             }
        }
    }

    private static boolean war() throws Exception {
        double p1=0;
        double p2=0;
        if (!hasWon())
        {
             p1 = player1.dequeue();
             p2 = player2.dequeue();
            handQueue.enqueue(p1);
            handQueue.enqueue(p2);
        }
        else{return false;}
        if (!hasWon())
        {
            p1 = player1.dequeue();
            p2 = player2.dequeue();
            handQueue.enqueue(p1);
            handQueue.enqueue(p2);
        }
        else{return false;}
        if (p1>p2)
        {
            addAll(true);
            return false;
        }
        if (p2>p1){
            addAll(false);
            return false;
        }
        return true;

    }

    public static void addAll(boolean b) throws Exception {

        int var = handQueue.size();
        for (int i=0; i< var; i++)
        {
            if (b)
            {
                player1.enqueue(handQueue.dequeue());
            }
            else{
                player2.enqueue(handQueue.dequeue());
            }
        }
    }
    public static boolean hasWon(){
        return player1.isEmpty() || player2.isEmpty();
    }

}



