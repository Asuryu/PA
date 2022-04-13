package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;

public class PoEUI {
    PoEContext fsm;

    public PoEUI() {
        this.fsm = fsm;
    }

    private boolean exit = false;

    public void start() {
        while(!exit){
            if(fsm.getState() == null)
                System.exit(-1);
            switch(fsm.getState()){
                case CONFIG:
                    System.out.println("Por implementar!\n");
                    break;
                case APPLICATION_OPT:
                    System.out.println("Por implementar!\n");
                    break;
                case PROP_ATTRIBUITON:
                    System.out.println("Por implementar!\n");
                    break;
                case ORI_ATTRIBUITON:
                    System.out.println("Por implementar!\n");
                    break;
                case REVIEW:
                    System.out.println("Por implementar!\n");
                    break;
            }
        }
    }
}
