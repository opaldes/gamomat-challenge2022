package classes;

import gamomat.classes.SlotMachineFactory;
import gamomat.interfaces.ISlotMachine;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

class SlotMachineTest {

    private static JSONObject resultCache;
    private static ISlotMachine slotMachine;

    @BeforeAll
    static void setUp() {
        slotMachine = new SlotMachineFactory().createSlotMachine();
    }

    @Test
    @DisplayName("the machine should return winning lines")
    void slotMachineSpinForLines() {
        Assertions.assertNotNull(slotMachine.spin().get("wonLines"));
    }

    @Test
    @DisplayName("the machine should return the amount won")
    void slotMachineSpinForAmount() {
        Assertions.assertNotNull(slotMachine.spin().get("winAmount"));
    }

    @Test
    @DisplayName("the machine should return the winningSubset of the reel")
    void slotMachineSpinForSubset() {
        Assertions.assertNotNull(slotMachine.spin().get("winSubset"));
    }


    @RepeatedTest(40)
    @DisplayName("the machines results should not match")
    void slotMachineDifference() {
        //we fill the cache with a result runs only at the first test iteration
        if (SlotMachineTest.resultCache == null) {
            SlotMachineTest.resultCache = slotMachine.spin();
        }
        //It's possible to spin the slot machine and get the same result
        //for this challenge I will treat it as not "happening"
        JSONObject result = slotMachine.spin();


        Assertions.assertNotEquals(result, SlotMachineTest.resultCache);

        //results are used for further testing comparison
        SlotMachineTest.resultCache = result;
    }

}