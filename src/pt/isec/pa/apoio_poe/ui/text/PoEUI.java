package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.utils.PAInput;

import java.io.FileNotFoundException;

public class PoEUI {
    private PoEContext fsm;
    private boolean exit = false;

    public PoEUI(PoEContext fsm) {
        this.fsm = fsm;
    }

    public void start() throws FileNotFoundException {

        System.out.println(mostrarASCII());

        while(!exit){
            System.out.println("STATE: " + fsm.getState());
            System.out.println("CLOSED: " + fsm.isClosed());
            String comando = PAInput.readString("> ", false);
            switch(fsm.getState()){
                case CONFIG:

                    int option = PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Sair");
                    switch (comando){
                        case "next" -> fsm.nextPhase();
                        case "close" -> {fsm.closePhase(); fsm.nextPhase();}
                        case "add" -> {
                            if(option == 1) fsm.addAlunosCSV();
                            else if(option == 2) fsm.addDocentesCSV();
                            else if(option == 3) fsm.addPropostasCSV();
                            else if(option == 4) exit = true;
                        }
                        case "exit" -> exit = true;
                    }
                    break;
                case APPLICATION_OPT:
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

    public String mostrarASCII(){
        return( "  ____       _____   ____  _____ ___ ____  \n" +
                " |  _ \\ ___ | ____| |  _ \\| ____|_ _/ ___|\n" +
                " | |_) / _ \\|  _|   | | | |  _|  | |\\___ \\ \n" +
                " |  __/ (_) | |___  | |_| | |___ | | ___) |\n" +
                " |_|   \\___/|_____| |____/|_____|___|____/ \n");
    }

}
