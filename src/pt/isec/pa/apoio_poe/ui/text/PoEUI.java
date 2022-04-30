package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.utils.Menu;
import pt.isec.pa.apoio_poe.utils.PAInput;
import pt.isec.pa.apoio_poe.utils.Utils;

public class PoEUI {
    private PoEContext fsm;
    private boolean exit = false;

    public PoEUI(PoEContext fsm) {
        this.fsm = fsm;
    }

    public void start() {
        Utils.mostrarASCII();
        int load = PAInput.chooseOption("Deseja carregar um ficheiro de estado?", "Sim", "Não");
        if(load == 1) {
            String fsmFile = PAInput.readString("Insira o nome do ficheiro de estado: ", true);
            PoEContext ctx = fsm.loadSave(fsmFile);
            if(ctx != null){
                this.fsm = ctx;
            } else {
                System.out.println("[!] Ocorreu um erro ao ler o ficheiro com o estado guardado");
                Utils.pressToContinue();
            }
        }

        Utils.mostrarASCII();
        boolean changeMode = true;
        int option = 0;

        while(!exit){
            System.out.println("STATE: " + fsm.getState());
            System.out.println("CLOSED: " + fsm.isClosed());
            switch (fsm.getState()) {
                case CONFIG -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Próxima fase", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    switch (option) {
                        case 1 -> changeMode = Menu.menuAlunos(fsm);
                        case 2 -> changeMode = Menu.menuDocentes(fsm);
                        case 3 -> changeMode = Menu.menuPropostas(fsm);
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
                }
                case APPLICATION_OPT -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Gestão de Candidaturas", "Listas de Alunos", "Listas de Propostas", "Fase anterior", "Próxima fase", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    switch (option) {
                        case 1 -> changeMode = Menu.menuCandidaturas(fsm);
                        case 2 -> changeMode = Menu.menuListasAlunos(fsm);
                        case 3 -> changeMode = Menu.menuListasPropostas(fsm);
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
                }
                case PROP_ATTRIBUTION -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Atribuição automática das propostas com aluno associado", "Atribuição automática de uma proposta (alunos sem atribuições)", "Atribuição manual de propostas (alunos sem atribuição)", "Remoção manual de uma atribuição", "Listas de Alunos", "Listas de Propostas", "Fase anterior", "Fase seguinte", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6) {
                        changeMode = Menu.menuAtribuicaoPropostas(fsm, option);
                    } else if (option == 7) {
                        fsm.previousPhase();
                        changeMode = true;
                    } else if (option == 8) {
                        fsm.nextPhase();
                        changeMode = true;
                    } else if (option == 9) {
                        fsm.closePhase();
                        Utils.pressToContinue();
                        changeMode = true;
                    } else if (option == 10) {
                        exit = true;
                    }
                }
                case ORI_ATTRIBUTION -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Atribuição automática de orientadores", "Gestão de Orientadores", "Atribuição manual de orientadores", "Listagem de Dados", "Fase anterior", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    if (option == 1 || option == 2 || option == 3 || option == 4) {
                        changeMode = Menu.menuAtribuicaoOrientador(fsm, option);
                    } else if (option == 5) {
                        fsm.previousPhase();
                        changeMode = true;
                    } else if (option == 6) {
                        fsm.closePhase();
                        Utils.pressToContinue();
                        changeMode = true;
                    } else if (option == 7) {
                        exit = true;
                    }
                }
                case REVIEW -> System.out.println("Por implementar REVIEW!\n");
                default -> System.exit(-1);
            }
        }

        int save = PAInput.chooseOption("Guardar estado atual?", "Sim", "Não");
        if(save == 1) {
            String filename = PAInput.readString("[>] Nome do ficheiro para guardar o estado atual: ", false);
            if(fsm.exitAndSave(filename)){
                System.out.println("[·] Estado atual do programa gravado com sucesso!");
            } else {
                System.out.println("[!] Ocorreu um erro ao gravar o estado do programa");
            }
        }
    }
}
