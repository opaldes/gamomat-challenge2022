package gamomat.interfaces;

/**
 * slot machine api interface lists the endpoint methods
 */
public interface ISlotMachineAPI {
    /**
     *  our endpoint function for spinning the machine
     * @return String in json
     */
    String spin();
}
