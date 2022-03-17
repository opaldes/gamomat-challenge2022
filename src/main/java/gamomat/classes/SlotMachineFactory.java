package gamomat.classes;

import gamomat.exceptions.NoReelElementsException;
import gamomat.interfaces.ISlotMachine;
import gamomat.interfaces.ISlotMachineFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlotMachineFactory implements ISlotMachineFactory {
    ConfigurationReader config;
    @Override
    public ISlotMachine createSlotMachine() {
       this.config = new ConfigurationReader(new File("slotMachine.properties"));

        return new SlotMachine(
                this.prepareReels(config.getCsvStringArray("reels")),
                config.getInt("reelRows"),
                config.getCsvIntMultArray("winLines"),
                this.prepareWinConditionMap(config.getCsvStringMultArray("winConditions"))
        );
    }

    private Reel[] prepareReels(ArrayList<String> reelReferences) {
        ArrayList<Reel> reels = new ArrayList<>();

        for (String reelReference : reelReferences) {
            try {
                reels.add(new Reel(this.config.getString(reelReference)));
            } catch (NoReelElementsException e) {
                e.printStackTrace();
            }
        }

        return reels.toArray(Reel[]::new);
    }

    private Map<String, Double> prepareWinConditionMap(ArrayList<ArrayList<String>> winningConditions) {
        Map<String, Double> map = new HashMap<>();
        for (ArrayList<String> winningCondition : winningConditions) {
            //we should have at index 0 our string for matching and at 1 our double
            map.put(winningCondition.get(0),  Double.parseDouble(winningCondition.get(1)));
        }
        return map;
    }
}
