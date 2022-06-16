package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.model.fsm.ReturnValue;
import pt.isec.pa.apoio_poe.utils.PAInput;
import pt.isec.pa.apoio_poe.utils.Utils;

/**
 * A classe PoEUI representa a interface de usuário do sistema.
 */
public class PoEUI {
    private PoEContext fsm;
    private boolean exit = false;

    /**
     * Construtor da classe PoEUI.
     * @param fsm O contexto da máquina de estados.
     */
    public PoEUI(PoEContext fsm) {
        this.fsm = fsm;
    }

    /**
     * Método que inicia a execução do sistema.
     */
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

        boolean changeMode = true;
        int option = 0;

        while(!exit){
            Utils.mostrarASCII();
            System.out.println("· FASE " + (fsm.getState().ordinal() + 1) + ": " + fsm.getState() + " ·");
            if (fsm.isClosed()) {
                System.out.println("ESTADO: Fechada (Read-Only) ");
            } else {
                System.out.println("ESTADO: Aberta");
            }
            switch (fsm.getState()) {
                case CONFIG -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Próxima fase", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    switch (option) {
                        case 1 -> changeMode = PoEMenu.menuAlunos(fsm);
                        case 2 -> changeMode = PoEMenu.menuDocentes(fsm);
                        case 3 -> changeMode = PoEMenu.menuPropostas(fsm);
                        case 4 -> {
                            fsm.nextPhase();
                            changeMode = true;
                        }
                        case 5 -> {
                            switch(fsm.closePhase()){
                                case ALREADY_CLOSED -> System.out.println("[!] A fase de configuração já se encontra fechada!");
                                case NO_STUDENTS -> System.out.println("[!] Não existem alunos inscritos");
                                case INSF_PROPS_DA_STUDENTS -> System.out.println("[!] Não existem propostas suficientes para os alunos do ramo DA");
                                case INSF_PROPS_SI_STUDENTS -> System.out.println("[!] Não existem propostas suficientes para os alunos do ramo SI");
                                case INSF_PROPS_RAS_STUDENTS -> System.out.println("[!] Não existem propostas suficientes para os alunos do ramo RAS");
                                case CLOSED_SUCESSFULLY -> System.out.println("[·] Fase de CONFIGURAÇÃO fechada com sucesso!");
                            }
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
                        case 1 -> changeMode = PoEMenu.menuCandidaturas(fsm);
                        case 2 -> changeMode = PoEMenu.menuListasAlunos(fsm);
                        case 3 -> changeMode = PoEMenu.menuListasPropostas(fsm);
                        case 4 -> {
                            fsm.previousPhase();
                            changeMode = true;
                        }
                        case 5 -> {
                            fsm.nextPhase();
                            changeMode = true;
                        }
                        case 6 -> {
                            switch(fsm.closePhase()){
                                case ALREADY_CLOSED -> System.out.println("[!] A fase de OPÇÕES DE CANDIDATURA já se encontra fechada.");
                                case PREVIOUS_PHASE_NOT_CLOSED -> System.out.println("[!] A fase de CONFIGURAÇÃO ainda não foi fechada.");
                                case CLOSED_SUCESSFULLY -> System.out.println("[·] Fase de OPÇÕES DE CANDIDATURA fechada com sucesso!");
                            }
                            Utils.pressToContinue();
                            changeMode = true;
                        }
                        case 7 -> exit = true;
                    }
                }
                case PROP_ATTRIBUTION -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Atribuição automática das propostas com aluno associado", "Atribuição automática de uma proposta (alunos sem atribuições)", "Atribuição manual de propostas (alunos sem atribuição)", "Remoção manual de uma atribuição", "Listas de Alunos", "Listas de Propostas", "Exportar alunos para um ficheiro CSV", "Fase anterior", "Fase seguinte", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6) {
                        changeMode = PoEMenu.menuAtribuicaoPropostas(fsm, option);
                    } else if(option == 7){
                        String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
                        fsm.saveAlunosCSV(filePath);
                        Utils.pressToContinue();
                        changeMode = true;
                    } else if (option == 8) {
                        fsm.previousPhase();
                        changeMode = true;
                    } else if (option == 9) {
                        fsm.nextPhase();
                        changeMode = true;
                    } else if (option == 10) {
                        switch(fsm.closePhase()){
                            case ALREADY_CLOSED -> System.out.println("[!] A fase de ATRIBUIÇÃO DE PROPOSTAS já se encontra fechada.");
                            case STUDENT_WITHOUT_PROPS -> System.out.println("[!] Existem alunos que ainda não têm proposta atribuída.");
                            case CLOSED_SUCESSFULLY -> System.out.println("[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada com sucesso!");
                        }
                        Utils.pressToContinue();
                        changeMode = true;
                    } else if (option == 11) {
                        exit = true;
                    }
                }
                case ORI_ATTRIBUTION -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Atribuição automática de orientadores", "Gestão de Orientadores", "Atribuição manual de orientadores", "Listagem de Dados", "Exportar alunos para um ficheiro CSV", "Fase anterior", "Fechar a fase", "Sair");
                        changeMode = false;
                    }
                    if (option == 1 || option == 2 || option == 3 || option == 4) {
                        changeMode = PoEMenu.menuAtribuicaoOrientador(fsm, option);
                    } else if(option == 5){
                        String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
                        fsm.saveAlunosCSV(filePath);
                        Utils.pressToContinue();
                        changeMode = true;
                    } else if (option == 6) {
                        fsm.previousPhase();
                        changeMode = true;
                    } else if (option == 7) {
                        System.out.println("[!] Após fechar esta fase não será possível voltar atrás.");
                        if (PAInput.readString("Deseja fechar a fase? (S/N): ", true).equalsIgnoreCase("s")) {
                            switch(fsm.closePhase()){
                                case ALREADY_CLOSED -> System.out.println("[!] A fase de ATRIBUIÇÃO DE ORIENTADORES já se encontra fechada.");
                                case CLOSED_SUCESSFULLY -> System.out.println("[·] Fase de ATRIBUIÇÃO DE ORIENTADORES fechada com sucesso!");
                            }
                            Utils.pressToContinue();
                            changeMode = true;
                        }
                    } else if (option == 8) {
                        exit = true;
                    }
                }
                case REVIEW -> {
                    if (changeMode) {
                        option = PAInput.chooseOption("Escolha uma opção", "Lista de estudantes com propostas atribuídas", "Lista de estudantes sem propostas atribuídas e com opções de candidatura", "Exportar alunos para um ficheiro CSV", "Propostas disponíveis", "Propostas atribuídas", "Estatísticas", "Concluir processo", "Sair");
                        changeMode = false;
                    }
                    if(option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6){
                        PoEMenu.menuConsultaDados(fsm, option);
                        changeMode = true;
                    }
                    else if(option == 7){
                        Utils.mostrarASCII();
                        System.out.println("[·] Processo concluído com sucesso!");
                        Utils.pressToContinue();
                        exit = true;
                    }
                    else if(option == 8){
                        exit = true;
                    }
                }
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
