package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.utils.Menu;
import pt.isec.pa.apoio_poe.utils.PAInput;
import pt.isec.pa.apoio_poe.utils.Utils;

public class PoEUI {
    private final PoEContext fsm;
    private boolean exit = false;

    public PoEUI(PoEContext fsm) {
        this.fsm = fsm;
    }

    public void start() {

        Utils.mostrarASCII();
        boolean changeMode = true;
        int option = 0;

        while(!exit){
            System.out.println("STATE: " + fsm.getState());
            System.out.println("CLOSED: " + fsm.isClosed());
            switch(fsm.getState()){
                case CONFIG:
                    if(changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Próxima fase", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    switch (option){
                        case 1 -> {

                            changeMode = Menu.menuAlunos(fsm);
                        }
                        case 2 -> {
                            changeMode = Menu.menuDocentes(fsm);
                        }
                        case 3 -> {
                            changeMode = Menu.menuPropostas(fsm);
                        }
                        case 4 -> {
                            fsm.nextPhase();
                            changeMode = true;
                        }
                        case 5 -> {
                            fsm.closePhase();
                            Utils.pressToContinue();
                            changeMode = true;
                        }
                        case 6 -> exit = true;
                    }
                    break;
                case APPLICATION_OPT:
                    if(changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Gestão de Candidaturas", "Listas de Alunos", "Listas de Propostas", "Fase Anterior", "Próxima fase", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    switch (option){
                        case 1 -> {
                            changeMode = Menu.menuCandidaturas(fsm);
                        }
                        case 2 -> {
                            changeMode = Menu.menuListasAlunos(fsm);
                        }
                        case 3 -> {
                            changeMode = Menu.menuListasPropostas(fsm);
                        }
                        case 4 -> {
                            fsm.previousPhase();
                            changeMode = true;
                        }
                        case 5 -> {
                            fsm.nextPhase();
                            changeMode = true;
                        }
                        case 6 -> {
                            fsm.closePhase();
                            Utils.pressToContinue();
                            changeMode = true;
                        }
                        case 7 -> exit = true;
                    }
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
