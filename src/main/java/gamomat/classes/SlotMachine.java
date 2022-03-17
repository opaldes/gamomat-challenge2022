package gamomat.classes;

import gamomat.exceptions.InvalidIndexException;
import gamomat.interfaces.ISlotMachine;
import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * the slot machine
 * operates the reels and does winning calculations
 */
public class SlotMachine implements ISlotMachine {
    private final Reel[] reelArray;
    private final int reelRows;
    private final ArrayList<ArrayList<Integer>> winLinesArray;
    private final Map<String, Double> winConMap;
    private ArrayList<String> winSubset;
    private Map<ArrayList<Integer>, Double> wonLinesMap;
    private Double amount;

    /**
     * Creates our slot machine
     * @param ReelArray sorted array of reels
     * @param ReelRows rows used
     * @param winLinesArray mult array with winning lines
     * @param winConMap mapped win conditions to price
     */
    public SlotMachine(
            Reel[] ReelArray,
            int ReelRows,
            ArrayList<ArrayList<Integer>> winLinesArray,
            Map<String, Double> winConMap){

        if (ReelArray.length < 1) {
            throw new InvalidParameterException("We need at least 1 reel to spin");
        }

        if (ReelRows < 1) {
            throw new InvalidParameterException("Reels need at least 1 row");
        }

        if (winLinesArray.size() < 1) {
            throw new InvalidParameterException("A slot machine needs at least 1 winning line");
        }

        if (winConMap.size() < 1) {
            throw new InvalidParameterException("A slot machine needs at least 1 win condition");
        }

        this.reelArray = ReelArray;
        this.reelRows = ReelRows;
        this.winLinesArray = winLinesArray;
        this.winConMap = winConMap;
    }

    @Override
    public JSONObject spin() {
        this.spinReels();
        this.createWinningSubset();
        this.calculateWinningsMap();
        this.calculateWinningsAmount();
        return this.createOutputJSON();
    }

    /**
     * "spins" the reels
     */
    private void spinReels(){
        for (Reel reel : this.reelArray) {
            /*
            we spin it by creating a random index based on
            the actual length of the reel and jump there
            */
            int index = (int)(Math.random() * reel.getLength());
            try {
                reel.jumpTo(index);
            } catch (InvalidIndexException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * we create a map where winning lines are
     * mapped to the win conditions
     */
    private void calculateWinningsMap() {
        this.wonLinesMap = new HashMap<>();

        for (ArrayList<Integer> ints : winLinesArray) {
            StringBuilder str = new StringBuilder();

            for (int i: ints) {
                try {
                    str.append(winSubset.get(i));
                }catch (IndexOutOfBoundsException e) {
                    //misconfigured winlines could run out of bound
                    e.printStackTrace();
                }
            }

            if(winConMap.get(str.toString()) != null){
                 //we got a winner and save it
                wonLinesMap.put(ints,winConMap.get(str.toString()));
            }
        }
    }

    /**
     * calculates winning Amounts by iterating through
     * our won lines
     */
    private void calculateWinningsAmount() {
        final Double[] amount = {0.0};
        this.wonLinesMap.forEach((ArrayList<Integer> arr, Double winAmount)-> amount[0] += winAmount);
        this.amount = amount[0];
    }

    /**
     * aggregates all the wanted information for the endpoint
     * @return JSONObject contains the required informations
     */
    private JSONObject createOutputJSON() {
        JSONObject json = new JSONObject();
        json.put("winAmount", this.amount);
        json.put("wonLines", this.wonLinesMap);
        json.put("winSubset", this.winSubset);
        return json;
    }

    /**
     * builds the subset where we collect our winning lines
     * they are written each for each row in a single dimensional list
     * so we can easily access them by index
     */
    private void createWinningSubset() {
        ArrayList<String> subset = new ArrayList<>();
        for (int i = 0; i < this.reelRows; i++) {
            for (Reel reel : this.reelArray) {
                subset.add(reel.getCurrent().value());
                reel.next();
            }
        }
        this.winSubset = subset;
    }
}
