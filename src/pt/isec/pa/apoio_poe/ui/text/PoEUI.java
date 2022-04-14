package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.utils.PAInput;

public class PoEUI {
    private PoEContext fsm;
    private boolean exit = false;

    public PoEUI(PoEContext fsm) {
        this.fsm = fsm;
    }

    public void start() {
        while(!exit){
            System.out.println("STATE: " + fsm.getState());
            String comando = PAInput.readString("> ", false);
            switch(fsm.getState()){
                case CONFIG:
                    System.out.println("Por implementar CONFIG!\n");
                    //int option = PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Sair");
                    if(comando.equalsIgnoreCase("next")) {
                        fsm.nextPhase();
                    }
                    else if(comando.equalsIgnoreCase("exit")) exit = true;
                    break;
                case APPLICATION_OPT:
                    System.out.println("Por implementar APPLICATION_OPT!\n");
                    if(comando.equalsIgnoreCase("next")) fsm.nextPhase();
                    else if(comando.equalsIgnoreCase("previous")) fsm.previousPhase();
                    else if(comando.equalsIgnoreCase("exit")) exit = true;
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
                default:
                    System.exit(-1);
                    break;
            }
        }
    }
}
