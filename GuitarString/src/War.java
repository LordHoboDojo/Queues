import java.io.*;
import java.util.*;
public class War
{
    public static void main (String args[]) throws Exception
    {
        Scanner input = new Scanner(new File("war.dat"));
        RingBuffer  playerOne = new RingBuffer(52);
        RingBuffer  playerTwo = new RingBuffer(52);
        RingBuffer  currentCards = new RingBuffer(52);
        String line;
        String[] cards;
        int hands = 0;
        while(input.hasNextLine())
        {
            String vals = "23456789TJQKA";
            line = input.nextLine();
            cards = line.split(" ");
            for (String s: cards)
            {
                playerOne.enqueue(vals.indexOf(s.substring(0,1))+2);
            }
            line = input.nextLine();
            cards = line.split(" ");
            for (String s: cards)
            {
                playerTwo.enqueue(vals.indexOf(s.substring(0,1))+2);
            }

                while(!playerOne.isEmpty() && !playerTwo.isEmpty() && hands < 100000)
                {
                    if(playerOne.peek() > playerTwo.peek())
                    {
                        while(!currentCards.isEmpty())
                        {
                            playerOne.enqueue(currentCards.dequeue());
                        }
                        playerOne.enqueue(playerOne.dequeue());
                        playerOne.enqueue(playerTwo.dequeue());
                    }
                    else if(playerTwo.peek() > playerOne.peek())
                    {
                        while(!currentCards.isEmpty())
                        {
                            playerTwo.enqueue(currentCards.dequeue());
                        }
                        playerTwo.enqueue(playerOne.dequeue());
                        playerTwo.enqueue(playerTwo.dequeue());
                    }
                    else if(playerTwo.peek() == playerOne.peek())
                    {
                        if(playerOne.size() > 2 && playerTwo.size() > 2)
                        {
                            currentCards.enqueue(playerOne.dequeue());
                            currentCards.enqueue(playerTwo.dequeue());
                            currentCards.enqueue(playerOne.dequeue());
                            currentCards.enqueue(playerTwo.dequeue());
                        }
                        else if(playerOne.size() > playerTwo.size())
                        {
                            while(!playerTwo.isEmpty())
                            {
                                playerOne.enqueue(playerTwo.dequeue());
                            }
                        }
                        else if(playerTwo.size() > playerOne.size())
                        {
                            while(!playerOne.isEmpty())
                            {
                                playerTwo.enqueue(playerOne.dequeue());
                            }
                        }
                        else
                        {
                            System.out.println("Tie game");
                        }
                    }
                    hands++;
                }

            System.out.println(hands == 100000 ? "Tie game stopped at 100000 plays." : playerOne.isFull() ? "Player 1 wins!" : "Player 2 wins!");
                playerOne.clear();
                playerTwo.clear();
                currentCards.clear();
                hands = 0;
            }
        }
    }

