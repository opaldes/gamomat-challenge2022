package gamomat.interfaces;

/**
 * Interface for the slot machine factory
 */
public interface ISlotMachineFactory {
    /**
     * creates a SlotMachine
     * does the logic for creating constructor parameters out of our configs
     *
     * @return ISlotMachine
     */
    ISlotMachine createSlotMachine();
}
