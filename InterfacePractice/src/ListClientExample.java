import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Descrption up here.
 *
 * @author Brett Cooper
 * @version 0.0.0.0.1
 *
 */

public class ListClientExample {

    public static void main(String[] args) {


        List<String> wordList = new ArrayList<>();

        wordList.add("sun");
        wordList.add("tree");
        wordList.add("bird");
        wordList.add("ocean");
        for(String word : wordList) {
            System.out.println(word);
        }
    }


    public static void prioritizeMaximumValue(List<Integer> numberList){
        if(!numberList.isEmpty()) {
            int maxValue = numberList.get(0);
            int maxPosition = 0;
            for(int i =1; i<numberList.size();i++ ){
                int currentValue = numberList.get(i);
                if(currentValue>maxValue){
                    maxValue = currentValue;
                    maxPosition = i;
                }
            }
            numberList.remove(maxPosition);
            numberList.set(0,maxValue);
        } else {
            System.out.println("The list is empty.");
        }
    }

    public static int countNegatives(ListInterface<Integer> numberList){
        int count = 0;
        for (int i = 1; i <= numberList.getLength(); i++) {
                if(numberList.getEntry(i)<0){
                    count++;
                }
        }
        return count;
    }

    public static int countNegatives(List<Integer> numberList){
        int count = 0;
        for (int i = 0; i < numberList.size(); i++) {
            if(numberList.get(i)<0){
                count++;
            }
        }
        return count;
    }

    public static void removeLowestScore(ListInterface<Integer> scoreList){
        if(scoreList.isEmpty()){

            int lowestScore = scoreList.getEntry(1);
            int lowestScorePosition = 1;

            for(int i=1; i<=scoreList.getLength();i++){
                int currentScore = scoreList.getEntry(i);
                if(currentScore<lowestScore){
                    lowestScore = currentScore;
                    lowestScorePosition = i;
                }
            }

            scoreList.remove(lowestScorePosition);
        } else {
            System.out.println("Cannot remove score from an empty list");
        }
    }


    public static void removeLowestScore(List<Integer> scoreList) {
        if(!scoreList.isEmpty()){
            int lowestScore = scoreList.get(0);

            for(int i=1; i<scoreList.size();i++){
                int currentScore = scoreList.get(i);
                if(currentScore<lowestScore){
                    lowestScore = currentScore;
                }
                scoreList.remove(Integer.valueOf(lowestScore));

            }

        } else{
            System.out.println("This empty dude");
        }
    }


}
