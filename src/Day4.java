import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay4.txt");
        Scanner in = new Scanner(file);
        ArrayList<CardD4> allCards = new ArrayList<>();
        int sum = 0;
        int totalCards = 0;
        while (in.hasNextLine()) {
            String cardDeck = in.nextLine();
            String[] cardInfo = cardDeck.strip().split(": ");
            String[] cardNums = cardInfo[1].strip().split(" \\| ");
            String[] cardID = cardInfo[0].strip().split(" ");
            int id = Integer.parseInt(cardID[cardID.length - 1]);
            CardD4 card = new CardD4(id, cardNums[0], cardNums[1]);
            card.addCopy();
            allCards.add(card);
            sum += (int) card.getWorth();
        }
        for (int i = 0; i < allCards.size(); i++) {
            int id = allCards.get(i).getID();
            System.out.println("ID: " + id);
            int copies = allCards.get(i).getCopies();
            System.out.println("Copies: " + copies);
            int counter = allCards.get(i).getCount();
            System.out.println("Matches: " + counter);
            for (int k = 1; k < copies + 1; k++) {
                for (int j = 1; j < counter + 1; j++) {
                    allCards.get(i + j).addCopy();
                }
            }
        }
        for (CardD4 allCard : allCards) {
            totalCards += allCard.getCopies();
        }
        System.out.println(totalCards);
        System.out.println(sum);
    }

}
