package pt.isec.pa.apoio_poe.utils;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEDocente;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.utils.PAInput;
import pt.isec.pa.apoio_poe.utils.Utils;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Classe que implementa menus para várias ocasiões
 */
public class Menu {

    private Menu(){}

    /**
     * Menu para o utilizador escolher opções de gestão de alunos
     * @param fsm Referência para a máquina de estados
     * @return True se o utilizador quiser voltar para o menu inicial
     */
    public static boolean menuAlunos(PoEContext fsm){
        int escolhaAluno = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Alunos]\nEscolha uma opção", "Importar alunos a partir de um ficheiro CSV", "Exportar alunos para um ficheiro CSV", "Consultar alunos", "Editar aluno", "Remover aluno", "Voltar");
        switch(escolhaAluno){
            case 1 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
                fsm.addAlunosCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
                fsm.saveAlunosCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption("[CONFIGURAÇÃO - Consultar Alunos]\nEscolha o tipo de pesquisa", "Todos", "Nome", "Número de Aluno", "Curso", "Ramo", "Voltar");
                switch (chooseSearchParam){
                    case 1 -> {
                        ArrayList<PoEAluno> alunos = fsm.getAlunos();
                        for(PoEAluno aluno : alunos){
                            System.out.println(aluno.toString());
                        }
                        if(alunos.size() == 0){
                            System.out.println("[!] Não existem alunos registados");
                        }
                    }
                    case 2 -> {
                        String nome = PAInput.readString("Nome do aluno: ", false);
                        ArrayList<PoEAluno> alunos = fsm.getAlunosByName(nome);
                        for(PoEAluno aluno : alunos){
                            System.out.println(aluno);
                        }
                        if(alunos.isEmpty()){
                            System.out.println("[·] Não foram encontrados alunos com o nome " + nome);
                        }
                    }
                    case 3 -> {
                        long numero = PAInput.readLong("Número de aluno: ");
                        PoEAluno aluno = fsm.getAlunoById(numero);
                        if(aluno != null){
                            System.out.println(aluno);
                        } else {
                            System.out.println("Não foi encontrado nenhum aluno com o número " + numero);
                        }
                    }
                    case 4 -> {
                        String curso = PAInput.readString("Curso: ", true);
                        ArrayList<PoEAluno> alunos = fsm.getAlunosByCurso(curso);
                        for(PoEAluno aluno : alunos){
                            System.out.println(aluno);
                        }
                        if(alunos.isEmpty()){
                            System.out.println("[·] Não foram encontrados alunos no curso " + curso.toUpperCase());
                        }
                    }
                    case 5 -> {
                        String ramo = PAInput.readString("Ramo: ", true);
                        ArrayList<PoEAluno> alunos = fsm.getAlunosByRamo(ramo);
                        for(PoEAluno aluno : alunos){
                            System.out.println(aluno);
                        }
                        if(alunos.isEmpty()){
                            System.out.println("[·] Não foram encontrados alunos no ramo " + ramo.toUpperCase());
                        }
                    }
                    case 6 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                System.out.println("Editar Aluno");
            }
            case 5 -> {
                Long nrAluno = PAInput.readLong("Número do Aluno: ");
                PoEAluno aluno = fsm.getAlunoById(nrAluno);
                if(aluno != null){
                    fsm.removeAluno(aluno);
                    System.out.println("[·] Aluno " + aluno.getNome() + " removido com sucesso");
                } else {
                    System.out.println("[·] Não foi encontrado nenhum aluno com o número " + nrAluno);
                }
            }
            case 6 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }

    /**
     * Menu para o utilizador escolher opções de gestão de docentes
     * @param fsm Referência para a máquina de estados
     * @return True se o utilizador quiser voltar para o menu inicial
     */
    public static boolean menuDocentes(PoEContext fsm){
        int escolhaDocente = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Docentes]\nEscolha uma opção", "Importar docentes a partir de um ficheiro CSV", "Exportar docentes para um ficheiro CSV", "Consultar docentes", "Editar docente", "Remover docente", "Voltar");
        switch(escolhaDocente){
            case 1 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (docentes): ", false);
                fsm.addDocentesCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (docentes): ", false);
                fsm.saveDocentesCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption("[CONFIGURAÇÃO - Consultar Docentes]\nEscolha o tipo de pesquisa", "Todos", "Nome", "Email", "Voltar");
                switch (chooseSearchParam){
                    case 1 -> {
                        ArrayList<PoEDocente> docentes = fsm.getDocentes();
                        for(PoEDocente docente : docentes){
                            System.out.println(docente.toString());
                        }
                        if(docentes.size() == 0){
                            System.out.println("[!] Não existem docentes registados");
                        }
                    }
                    case 2 -> {
                        String nome = PAInput.readString("Nome do docente: ", false);
                        PoEDocente docente = fsm.getDocenteByName(nome);
                        if(docente != null){
                            System.out.println(docente);
                        } else {
                            System.out.println("[!] Não foi encontrado nenhum docente com o nome " + nome);
                        }
                    }
                    case 3 -> {
                        String email = PAInput.readString("Email do docente: ", true);
                        PoEDocente docente = fsm.getDocenteByEmail(email);
                        if(docente != null){
                            System.out.println(docente);
                        } else {
                            System.out.println("Não foi encontrado nenhum docente com o email " + email);
                        }
                    }
                    case 4 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                System.out.println("Editar Docente");
            }
            case 5 -> {
                String emailDocente = PAInput.readString("Email do Docente: ", true);
                PoEDocente docente = fsm.getDocenteByEmail(emailDocente);
                if(docente != null){
                    fsm.removeDocente(docente);
                    System.out.println("[·] Docente " + docente.getNome() + " removido com sucesso");
                } else {
                    System.out.println("[·] Não existe nenhum docente com o email " + emailDocente);
                }
            }
            case 6 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }
    /**
     * Menu para o utilizador escolher opções de gestão de propostas
     * @param fsm Referência para a máquina de estados
     * @return True se o utilizador quiser voltar para o menu inicial
     */
    public static boolean menuPropostas(PoEContext fsm){
        int escolhaProposta = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Propostas]\nEscolha uma opção", "Importar propostas a partir de um ficheiro CSV", "Exportar propostas para um ficheiro CSV", "Consultar propostas", "Editar proposta", "Remover proposta", "Voltar");
        switch(escolhaProposta){
            case 1 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (propostas): ", false);
                fsm.addPropostasCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (propostas): ", false);
                fsm.savePropostasCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption("[CONFIGURAÇÃO - Consultar Propostas]\nEscolha o tipo de pesquisa", "Todas", "ID", "Título", "Tipo", "Voltar");
                switch (chooseSearchParam){
                    case 1 -> {
                        ArrayList<PoEProposta> propostas = fsm.getPropostas();
                        for(PoEProposta proposta : propostas){
                            System.out.println(proposta.toString());
                        }
                        if(propostas.size() == 0){
                            System.out.println("[!] Não existem propostas registadas");
                        }
                    }
                    case 2 -> {
                        String idProposta = PAInput.readString("ID da proposta: ", true);
                        PoEProposta proposta = fsm.getPropostaById(idProposta);
                        if(proposta != null){
                            System.out.println(proposta);
                        } else {
                            System.out.println("[!] Não foi encontrada nenhuma proposta com o ID " + idProposta);
                        }
                    }
                    case 3 -> {
                        String nome = PAInput.readString("Título da proposta: ", false);
                        ArrayList<PoEProposta> propostas = fsm.getPropostasByTitle(nome);
                        for(PoEProposta proposta : propostas){
                            System.out.println(proposta.toString());
                        }
                        if(propostas.size() == 0){
                            System.out.println("[!] Não existem propostas com esse título registadas");
                        }
                    }
                    case 4 -> {
                        int escolhaTipo = PAInput.chooseOption("Tipo da Proposta", "Estágio", "Projeto", "Estágio/projeto autoproposto");
                        String tipo = null;
                        if(escolhaTipo == 1) tipo = "T1";
                        else if(escolhaTipo == 2) tipo = "T2";
                        else if(escolhaTipo == 3) tipo = "T3";
                        ArrayList<PoEProposta> propostas = fsm.getPropostasByType(tipo);
                        for(PoEProposta proposta : propostas){
                            System.out.println(proposta.toString());
                        }
                        if(propostas.size() == 0){
                            System.out.println("[!] Não existem propostas desse tipo registadas");
                        }
                    }
                    case 5 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                System.out.println("Editar Proposta");
            }
            case 5 -> {
                String idProposta = PAInput.readString("ID da Proposta: ", true);
                PoEProposta proposta = fsm.getPropostaById(idProposta);
                if(proposta != null){
                    fsm.removeProposta(proposta);
                    System.out.println("[·] Proposta " + proposta.getId() + " removida com sucesso");
                } else {
                    System.out.println("[·] Não existe nenhuma proposta com o ID " + idProposta);
                }
            }
            case 6 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }
}
