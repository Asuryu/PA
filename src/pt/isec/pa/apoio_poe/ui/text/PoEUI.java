package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;

public class PoEUI {
    private PoEContext fsm;

    public PoEUI(PoEContext fsm) {
        this.fsm = fsm;
    }

    public void start() {
        if(fsm.getState() == null) System.exit(-1);
        switch(fsm.getState()){
            case CONFIG:
                System.out.println("Por implementar CONFIG!\n");
                fsm.nextPhase();
                break;
            case APPLICATION_OPT:
                System.out.println("Por implementar APPLICATION_OPT!\n");
                break;
            case PROP_ATTRIBUTION:
                System.out.println("Por implementar PROP_ATTRIBUTION!\n");
                break;
            case ORI_ATTRIBUTION:
                System.out.println("Por implementar ORI_ATTRIBUTION!\n");
                break;
            case REVIEW:
                System.out.println("Por implementar REVIEW!\n");
                break;
        }
    }
}
