package gamomat.classes;

import gamomat.interfaces.ISlotMachine;

import gamomat.interfaces.ISlotMachineAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * slot machine implementation
 * Controller for the /spin endpoint
 */
@RestController
public class SlotMachineAPI implements ISlotMachineAPI {
    ISlotMachine slotMachine;

    public SlotMachineAPI() {
         this.slotMachine = new SlotMachineFactory().createSlotMachine();
    }

    @GetMapping("/spin")
    public String spin() {
        return this.slotMachine.spin().toString();
    }

}
