package pt.isec.pa.apoio_poe.utils;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe que implementa menus para várias ocasiões
 */
public class Menu {

    private Menu() {
    }

    /**
     * Menu para o utilizador escolher opções de gestão de alunos
     * 
     * @param fsm Referência para a máquina de estados
     * @return True se o utilizador quiser voltar para o menu inicial
     */
    public static boolean menuAlunos(PoEContext fsm) {
        int escolhaAluno = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Alunos]\nEscolha uma opção",
                "Importar alunos a partir de um ficheiro CSV", "Exportar alunos para um ficheiro CSV",
                "Consultar alunos", "Editar aluno", "Remover aluno", "Voltar");
        switch (escolhaAluno) {
            case 1 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
                fsm.addAlunosCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
                fsm.saveAlunosCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption(
                        "[CONFIGURAÇÃO - Consultar Alunos]\nEscolha o tipo de pesquisa", "Todos", "Nome",
                        "Número de Aluno", "Curso", "Ramo", "Voltar");
                switch (chooseSearchParam) {
                    case 1 -> {
                        ArrayList<PoEAluno> alunos = fsm.getAlunos();
                        for (PoEAluno aluno : alunos) {
                            System.out.println(aluno.toString());
                        }
                        if (alunos.size() == 0) {
                            System.out.println("[!] Não existem alunos registados");
                        }
                    }
                    case 2 -> {
                        String nome = PAInput.readString("Nome do aluno: ", false);
                        ArrayList<PoEAluno> alunos = fsm.getAlunosByName(nome);
                        for (PoEAluno aluno : alunos) {
                            System.out.println(aluno);
                        }
                        if (alunos.isEmpty()) {
                            System.out.println("[·] Não foram encontrados alunos com o nome " + nome);
                        }
                    }
                    case 3 -> {
                        long numero = PAInput.readLong("Número de aluno: ");
                        PoEAluno aluno = fsm.getAlunoById(numero);
                        if (aluno != null) {
                            System.out.println(aluno);
                        } else {
                            System.out.println("Não foi encontrado nenhum aluno com o número " + numero);
                        }
                    }
                    case 4 -> {
                        String curso = PAInput.readString("Curso: ", true);
                        ArrayList<PoEAluno> alunos = fsm.getAlunosByCurso(curso);
                        for (PoEAluno aluno : alunos) {
                            System.out.println(aluno);
                        }
                        if (alunos.isEmpty()) {
                            System.out.println("[·] Não foram encontrados alunos no curso " + curso.toUpperCase());
                        }
                    }
                    case 5 -> {
                        String ramo = PAInput.readString("Ramo: ", true);
                        ArrayList<PoEAluno> alunos = fsm.getAlunosByRamo(ramo);
                        for (PoEAluno aluno : alunos) {
                            System.out.println(aluno);
                        }
                        if (alunos.isEmpty()) {
                            System.out.println("[·] Não foram encontrados alunos no ramo " + ramo.toUpperCase());
                        }
                    }
                    case 6 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                System.out.println("Editar Aluno");
            }
            case 5 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                Long nrAluno = PAInput.readLong("Número do Aluno: ");
                PoEAluno aluno = fsm.getAlunoById(nrAluno);
                if (aluno != null) {
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
     * 
     * @param fsm Referência para a máquina de estados
     * @return True se o utilizador quiser voltar para o menu inicial
     */
    public static boolean menuDocentes(PoEContext fsm) {
        int escolhaDocente = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Docentes]\nEscolha uma opção",
                "Importar docentes a partir de um ficheiro CSV", "Exportar docentes para um ficheiro CSV",
                "Consultar docentes", "Editar docente", "Remover docente", "Voltar");
        switch (escolhaDocente) {
            case 1 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (docentes): ", false);
                fsm.addDocentesCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (docentes): ", false);
                fsm.saveDocentesCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption(
                        "[CONFIGURAÇÃO - Consultar Docentes]\nEscolha o tipo de pesquisa", "Todos", "Nome", "Email",
                        "Voltar");
                switch (chooseSearchParam) {
                    case 1 -> {
                        ArrayList<PoEDocente> docentes = fsm.getDocentes();
                        for (PoEDocente docente : docentes) {
                            System.out.println(docente.toString());
                        }
                        if (docentes.size() == 0) {
                            System.out.println("[!] Não existem docentes registados");
                        }
                    }
                    case 2 -> {
                        String nome = PAInput.readString("Nome do docente: ", false);
                        PoEDocente docente = fsm.getDocenteByName(nome);
                        if (docente != null) {
                            System.out.println(docente);
                        } else {
                            System.out.println("[!] Não foi encontrado nenhum docente com o nome " + nome);
                        }
                    }
                    case 3 -> {
                        String email = PAInput.readString("Email do docente: ", true);
                        PoEDocente docente = fsm.getDocenteByEmail(email);
                        if (docente != null) {
                            System.out.println(docente);
                        } else {
                            System.out.println("[!] Não foi encontrado nenhum docente com o email " + email);
                        }
                    }
                    case 4 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                System.out.println("Editar Docente");
            }
            case 5 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                String emailDocente = PAInput.readString("Email do Docente: ", true);
                PoEDocente docente = fsm.getDocenteByEmail(emailDocente);
                if (docente != null) {
                    fsm.removeDocente(docente);
                    System.out.println("[·] Docente " + docente.getNome() + " removido com sucesso");
                } else {
                    System.out.println("[!] Não existe nenhum docente com o email " + emailDocente);
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
     * 
     * @param fsm Referência para a máquina de estados
     * @return True se o utilizador quiser voltar para o menu inicial
     */
    public static boolean menuPropostas(PoEContext fsm) {
        int escolhaProposta = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Propostas]\nEscolha uma opção",
                "Importar propostas a partir de um ficheiro CSV", "Exportar propostas para um ficheiro CSV",
                "Consultar propostas", "Editar proposta", "Remover proposta", "Voltar");
        switch (escolhaProposta) {
            case 1 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (propostas): ", false);
                fsm.addPropostasCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (propostas): ", false);
                fsm.savePropostasCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption(
                        "[CONFIGURAÇÃO - Consultar Propostas]\nEscolha o tipo de pesquisa", "Todas", "ID", "Título",
                        "Tipo", "Voltar");
                switch (chooseSearchParam) {
                    case 1 -> {
                        ArrayList<PoEProposta> propostas = fsm.getPropostas();
                        for (PoEProposta proposta : propostas) {
                            System.out.println(proposta.toString());
                        }
                        if (propostas.size() == 0) {
                            System.out.println("[!] Não existem propostas registadas");
                        }
                    }
                    case 2 -> {
                        String idProposta = PAInput.readString("ID da proposta: ", true);
                        PoEProposta proposta = fsm.getPropostaById(idProposta);
                        if (proposta != null) {
                            System.out.println(proposta);
                        } else {
                            System.out.println("[!] Não foi encontrada nenhuma proposta com o ID " + idProposta);
                        }
                    }
                    case 3 -> {
                        String nome = PAInput.readString("Título da proposta: ", false);
                        ArrayList<PoEProposta> propostas = fsm.getPropostasByTitle(nome);
                        for (PoEProposta proposta : propostas) {
                            System.out.println(proposta.toString());
                        }
                        if (propostas.size() == 0) {
                            System.out.println("[!] Não existem propostas com esse título registadas");
                        }
                    }
                    case 4 -> {
                        int escolhaTipo = PAInput.chooseOption("Tipo da Proposta", "Estágio", "Projeto",
                                "Estágio/projeto autoproposto");
                        String tipo = null;
                        if (escolhaTipo == 1)
                            tipo = "T1";
                        else if (escolhaTipo == 2)
                            tipo = "T2";
                        else if (escolhaTipo == 3)
                            tipo = "T3";
                        ArrayList<PoEProposta> propostas = fsm.getPropostasByType(tipo);
                        for (PoEProposta proposta : propostas) {
                            System.out.println(proposta.toString());
                        }
                        if (propostas.size() == 0) {
                            System.out.println("[!] Não existem propostas desse tipo registadas");
                        }
                    }
                    case 5 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                System.out.println("Editar Proposta");
            }
            case 5 -> {
                if (fsm.isClosed()) {
                    System.out.println("[!] Fase de CONFIGURAÇÃO fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                String idProposta = PAInput.readString("ID da Proposta: ", true);
                PoEProposta proposta = fsm.getPropostaById(idProposta);
                if (proposta != null) {
                    fsm.removeProposta(proposta);
                    System.out.println("[·] Proposta " + proposta.getId() + " removida com sucesso");
                } else {
                    System.out.println("[!] Não existe nenhuma proposta com o ID " + idProposta);
                }
            }
            case 6 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }

    public static boolean menuCandidaturas(PoEContext fsm) {
        int escolhaProposta = PAInput.chooseOption("[OPÇÕES DE CANDIDATURA] - Gerir Candidaturas]\nEscolha uma opção",
                "Importar candidaturas a partir de um ficheiro CSV", "Exportar candidaturas para um ficheiro CSV",
                "Consultar candidaturas", "Editar candidatura", "Remover candidatura", "Voltar");
        switch (escolhaProposta) {
            case 1 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de OPÇÕES DE CANDIDATURA fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (candidaturas): ", false);
                fsm.addCandidaturasCSV(filePath);
            }
            case 2 -> {
                String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (candidaturas): ", false);
                fsm.saveCandidaturasCSV(filePath);
            }
            case 3 -> {
                int chooseSearchParam = PAInput.chooseOption(
                        "[CONFIGURAÇÃO - Consultar Candidaturas]\nEscolha o tipo de pesquisa", "Todas", "Aluno",
                        "Proposta", "Voltar");
                switch (chooseSearchParam) {
                    case 1 -> {
                        ArrayList<PoECandidatura> candidaturas = fsm.getCandidaturas();
                        for (PoECandidatura candidatura : candidaturas) {
                            System.out.println(candidatura.toString());
                        }
                        if (candidaturas.size() == 0) {
                            System.out.println("[!] Não existem candidaturas registadas");
                        }
                    }
                    case 2 -> {
                        Long numeroAluno = PAInput.readLong("Número de aluno: ");
                        PoECandidatura candidatura = fsm.getCandidaturaByAluno(numeroAluno);
                        if (candidatura != null) {
                            System.out.println(candidatura);
                        } else {
                            System.out.println("[!] Não existe nenhuma candidatura para o aluno " + numeroAluno);
                        }
                    }
                    case 3 -> {
                        String idProposta = PAInput.readString("ID da Proposta: ", true);
                        ArrayList<PoECandidatura> candidaturas = fsm.getCandidaturasByProposta(idProposta);
                        for (PoECandidatura candidatura : candidaturas) {
                            System.out.println(candidatura);
                        }
                        if (candidaturas.size() == 0) {
                            System.out.println(
                                    "[!] Não existem candidaturas registadas para a proposta com ID " + idProposta);
                        }
                    }
                    case 4 -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de OPÇÕES DE CANDIDATURA fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                System.out.println("Editar Candidatura");
            }
            case 5 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de OPÇÕES DE CANDIDATURA fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                Long nrAluno = PAInput.readLong("Número de aluno: ");
                PoECandidatura candidatura = fsm.getCandidaturaByAluno(nrAluno);
                if (candidatura != null) {
                    fsm.removeCandidatura(candidatura);
                } else {
                    System.out.println("[!] Não existe nenhuma candidatura para o aluno " + nrAluno);
                }
            }
            case 6 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }

    public static boolean menuListasAlunos(PoEContext fsm) {
        int escolhaOpcao = PAInput.chooseOption("[OPÇÕES DE CANDIDATURA - Listas de Alunos]\nEscolha uma opção",
                "Com autoproposta", "Com candidatura já registada", "Sem candidatura", "Voltar");
        switch (escolhaOpcao) {
            case 1 -> {
                // ISTO ESTÁ MAL, É PARA MUDAR
                ArrayList<PoEProposta> propostas = fsm.getPropostasByType("T3");
                System.out.println("[·] Alunos com autoproposta (" + propostas.size() + ")");
                for (PoEProposta proposta : propostas) {
                    System.out.println(proposta.getAluno());
                }
            }
            case 2 -> {
                ArrayList<PoECandidatura> candidaturas = fsm.getCandidaturas();
                System.out.println("[·] Alunos com candidatura registada (" + candidaturas.size() + ")");
                for (PoECandidatura candidatura : candidaturas) {
                    System.out.println(fsm.getAlunoById(candidatura.getNrEstudante()));
                }
            }
            case 3 -> {
                ArrayList<PoEAluno> alunos = fsm.getAlunos();
                ArrayList<PoEAluno> alunosSemCandidatura = new ArrayList<>();
                for (PoEAluno aluno : alunos) {
                    if (fsm.getCandidaturaByAluno(aluno.getNrEstudante()) == null) {
                        alunosSemCandidatura.add(aluno);
                    }
                }
                System.out.println("[·] Alunos sem candidatura (" + alunosSemCandidatura.size() + ")");
                for (PoEAluno aluno : alunosSemCandidatura) {
                    System.out.println(aluno);
                }
            }
            case 4 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }

    public static boolean menuListasPropostas(PoEContext fsm) {
        int escolhaOpcao = PAInput.chooseOption("[OPÇÕES DE CANDIDATURA - Listas de Propostas]\nEscolha uma opção",
                "Autopropostas de alunos", "Propostas de docentes", "Propostas com candidaturas",
                "Propostas sem candidatura", "Voltar");
        switch (escolhaOpcao) {
            case 1 -> {
                ArrayList<PoEProposta> proposta = fsm.getPropostasByType("T3");
                System.out.println("[·] Autopropostas de alunos (" + proposta.size() + ")");
                for (PoEProposta prop : proposta) {
                    System.out.println(prop);
                }
            }
            case 2 -> {
                ArrayList<PoEProposta> proposta = fsm.getPropostasByType("T2");
                System.out.println("[·] Propostas de docentes (" + proposta.size() + ")");
                for (PoEProposta prop : proposta) {
                    System.out.println(prop);
                }
            }
            case 3 -> {
                ArrayList<PoEProposta> propostas = fsm.getPropostas();
                ArrayList<PoEProposta> propostasComCandidatura = new ArrayList<>();
                for (PoEProposta proposta : propostas) {
                    if (proposta.getCandidaturas().size() > 0)
                        propostasComCandidatura.add(proposta);
                }
                System.out.println("[·] Propostas com candidaturas (" + propostasComCandidatura.size() + ")");
                for (PoEProposta prop : propostasComCandidatura) {
                    System.out.println(prop);
                }
            }
            case 4 -> {
                ArrayList<PoEProposta> propostas = fsm.getPropostas();
                ArrayList<PoEProposta> propostasSemCandidatura = new ArrayList<>();
                for (PoEProposta proposta : propostas) {
                    if (proposta.getCandidaturas().size() == 0)
                        propostasSemCandidatura.add(proposta);
                }
                System.out.println("[·] Propostas sem candidaturas (" + propostasSemCandidatura.size() + ")");
                for (PoEProposta prop : propostasSemCandidatura) {
                    System.out.println(prop);
                }
            }
            case 5 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }

    public static boolean menuAtribuicaoPropostas(PoEContext fsm, int option) {
        switch (option) {
            case 1 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                ArrayList<PoEProposta> propostas = fsm.getPropostas();
                for (PoEProposta proposta : propostas) {
                    Long nrAlunoAtribuido = proposta.getNrAlunoAtribuido();
                    if (nrAlunoAtribuido != null) {
                        PoEAluno aluno = fsm.getAlunoById(nrAlunoAtribuido);
                        aluno.setPropostaAtribuida(proposta);
                        System.out.println("[·] Proposta com o ID " + proposta.getId()
                                + " atribuída ao aluno com o número " + aluno.getNrEstudante());
                    }
                }
                Utils.pressToContinue();
                return true;
            }
            case 2 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                if (!fsm.isPhaseClosed(PoEState.APPLICATION_OPT)) {
                    System.out.println(
                            "[!] A fase de OPÇÕES DE CANDIDATURA ainda se encontra aberta.\n[!] Para selecionar esta opção necessita de fechar a fase anterior.");
                    Utils.pressToContinue();
                    return false;
                }
                ArrayList<PoEProposta> propostas = fsm.getPropostas();
                ArrayList<PoEAluno> alunos = fsm.getAlunos();
                for (PoEProposta proposta : propostas) {
                    ArrayList<PoEAluno> alunosSemProposta = new ArrayList<>();
                    for (PoEAluno aluno : alunos) {
                        if (aluno.getPropostaAtribuida() == null)
                            alunosSemProposta.add(aluno);
                    }
                    if (alunosSemProposta.size() == 0) {
                        System.out.println("[·] Todos os alunos têm proposta atribuída");
                        Utils.pressToContinue();
                        return true;
                    }
                    if (proposta.getNrAlunoAtribuido() != null)
                        continue;
                    ArrayList<PoECandidatura> candidaturas = proposta.getCandidaturas();
                    if (candidaturas.size() == 0) {
                        // Atribuir a aluno aleatorio dos alunos sem proposta
                        Random r = new Random();
                        int index = r.nextInt(alunosSemProposta.size());
                        PoEAluno aluno = alunosSemProposta.get(index);
                        aluno.setPropostaAtribuida(proposta);
                        proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                        System.out.println("[·] Proposta com o ID " + proposta.getId()
                                + " atribuída ao aluno com o número " + aluno.getNrEstudante());
                    } else if (candidaturas.size() == 1) {
                        // Atribuir a aluno da candidatura
                        PoECandidatura candidatura = candidaturas.get(0);
                        PoEAluno aluno = fsm.getAlunoById(candidatura.getNrEstudante());
                        aluno.setPropostaAtribuida(proposta);
                        proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                        System.out.println("[·] Proposta com o ID " + proposta.getId()
                                + " atribuída ao aluno com o número " + aluno.getNrEstudante());
                    } else {
                        // Obter alunos envolvidos
                        ArrayList<PoEAluno> alunosEnvolvidos = new ArrayList<>();
                        System.out.println(proposta);
                        for (PoECandidatura candidatura : candidaturas) {
                            PoEAluno aluno = fsm.getAlunoById(candidatura.getNrEstudante());
                            alunosEnvolvidos.add(aluno);
                            System.out.println(aluno);
                        }
                        String opcao = PAInput
                                .readString("[?] Selecione o aluno que pretende atribuir a esta proposta: ", false);
                        if (opcao.equals("")) {
                            System.out.println("[!] Não foi selecionado nenhum aluno.");
                            Utils.pressToContinue();
                        } else {
                            PoEAluno aluno = fsm.getAlunoById(Long.parseLong(opcao));
                            if (aluno == null) {
                                System.out.println("[!] Não existe aluno com o número " + opcao + ".");
                                Utils.pressToContinue();
                            } else {
                                if (alunosEnvolvidos.contains(aluno)) {
                                    aluno.setPropostaAtribuida(proposta);
                                    proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                                    System.out.println("[·] Proposta com o ID " + proposta.getId()
                                            + " atribuída ao aluno com o número " + aluno.getNrEstudante());
                                } else {
                                    System.out.println(
                                            "[!] O aluno com o número " + opcao + " não está envolvido na proposta.");
                                    Utils.pressToContinue();
                                }
                            }
                        }
                    }
                }
                Utils.pressToContinue();
                return true;
            }
            case 3 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return true;
                }
                if (!fsm.isPhaseClosed(PoEState.APPLICATION_OPT)) {
                    System.out.println(
                            "[!] A fase de OPÇÕES DE CANDIDATURA ainda se encontra aberta.\n[!] Para selecionar esta opção necessita de fechar a fase anterior.");
                    Utils.pressToContinue();
                    return true;
                }
                ArrayList<PoEAluno> alunosSemProposta = new ArrayList<>();
                for (PoEAluno aluno : fsm.getAlunos()) {
                    if (aluno.getPropostaAtribuida() == null)
                        alunosSemProposta.add(aluno);
                }
                Collections.shuffle(alunosSemProposta);
                for (PoEAluno aluno : alunosSemProposta) {
                    ArrayList<PoEProposta> propostasDisponiveis = new ArrayList<>();
                    for (PoEProposta proposta : fsm.getPropostas()) {
                        if (proposta.getNrAlunoAtribuido() == null)
                            propostasDisponiveis.add(proposta);
                    }
                    System.out.println("\n[·] Propostas disponíveis (" + propostasDisponiveis.size() + ")");
                    StringBuilder idsPropostasDisponiveis = new StringBuilder();
                    for (PoEProposta prop : propostasDisponiveis) {
                        idsPropostasDisponiveis.append(prop.getId()).append(" ");
                    }
                    idsPropostasDisponiveis.append("\n");
                    System.out.println(idsPropostasDisponiveis);
                    System.out.println(aluno);
                    Scanner sc = new Scanner(System.in);
                    System.out.print("[?] Selecione a proposta que pretende atribuir a este aluno: ");
                    String input = sc.nextLine().toUpperCase();
                    if (input.equals("")) {
                        System.out.println("[!] Não foi selecionada nenhuma proposta.");
                    } else {
                        if (propostasDisponiveis.contains(fsm.getPropostaById(input))) {
                            PoEProposta proposta = fsm.getPropostaById(input);
                            proposta.setNrAlunoAtribuido(aluno.getNrEstudante());
                            aluno.setPropostaAtribuida(proposta);
                            System.out.println("[·] Proposta com o ID " + proposta.getId()
                                    + " atribuída ao aluno com o número " + aluno.getNrEstudante());
                        } else {
                            System.out.println(
                                    "[!] A proposta selecionada não existe ou já foi atribuída a outro aluno.");
                        }
                    }
                }
                Utils.pressToContinue();
                return true;
            }
            case 4 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return false;
                }
                int opcao = PAInput.chooseOption("Escolha uma opção:", "Todos", "Nrº Aluno", "Voltar");
                switch (opcao) {
                    case 1 -> {
                        ArrayList<PoEAluno> alunos = fsm.getAlunos();
                        for (PoEAluno aluno : alunos) {
                            PoEProposta proposta = aluno.getPropostaAtribuida();
                            if (proposta != null) {
                                if (proposta.getNrAlunoAtribuido() != null) {
                                    aluno.setPropostaAtribuida(null);
                                    proposta.setNrAlunoAtribuido(null);
                                    System.out.println("[·] Proposta com ID " + proposta.getId()
                                            + " desassociada do aluno com número " + aluno.getNrEstudante());
                                }
                            }
                        }
                    }
                    case 2 -> {
                        Long nrAluno = PAInput.readLong("Número de aluno: ");
                        PoEAluno aluno = fsm.getAlunoById(nrAluno);
                        if (aluno == null) {
                            System.out.println("[!] Não foi encontrado nenhum aluno com o número " + nrAluno);
                            return false;
                        }
                        PoEProposta proposta = aluno.getPropostaAtribuida();
                        if (proposta != null) {
                            if (proposta.getNrAlunoAtribuido() != null) {
                                aluno.setPropostaAtribuida(null);
                                proposta.setNrAlunoAtribuido(null);
                                System.out.println("[·] Proposta com ID " + proposta.getId()
                                        + " desassociada do aluno com número " + aluno.getNrEstudante());
                            }
                        }
                    }
                    case 3 -> {
                        return true;
                    }
                }
            }
            case 5 -> {
                int opcao = PAInput.chooseOption("Escolha uma opção:", "Têm autoproposta associada",
                        "Têm candidatura já registada", "Têm proposta atribuída", "Não têm qualquer proposta atribuída",
                        "Voltar");
                switch (opcao) {
                    case 1 -> {
                        System.out.println("[–] Funcionalidade por implementar...");
                        Utils.pressToContinue();
                        return true;
                    }
                    case 2 -> {
                        ArrayList<PoEAluno> alunos = fsm.getAlunos();
                        for (PoEAluno aluno : alunos) {
                            if (aluno.getCandidatura() != null) {
                                System.out.println(aluno);
                            }
                        }
                        Utils.pressToContinue();
                        return true;
                    }
                    case 3 -> {
                        ArrayList<PoEAluno> alunos = fsm.getAlunos();
                        for (PoEAluno aluno : alunos) {
                            if (aluno.getPropostaAtribuida() != null) {
                                int ordemPreferencia = aluno.getCandidatura().getPreferencias()
                                        .indexOf(aluno.getPropostaAtribuida());
                                System.out.println(aluno);
                                if (ordemPreferencia != -1)
                                    System.out.println("Ordem de Preferência: " + ordemPreferencia);
                            }
                        }
                    }
                    case 4 -> {
                        ArrayList<PoEAluno> alunos = fsm.getAlunos();
                        for (PoEAluno aluno : alunos) {
                            if (aluno.getPropostaAtribuida() == null) {
                                System.out.println(aluno);
                            }
                        }
                        Utils.pressToContinue();
                        return true;
                    }
                }
            }
            case 6 -> {
                int opcao = PAInput.chooseOption("Escolha uma opção: ", "Autopropostas de alunos",
                        "Propostas de docentes", "Propostas disponíveis", "Propostas atribuídas");
                switch (opcao) {
                    case 1 -> {
                        System.out.println("[–] Funcionalidade por implementar...");
                    }
                    case 2 -> {
                        ArrayList<PoEProposta> projetos = fsm.getPropostasByType("T2");
                        for (PoEProposta proposta : projetos) {
                            System.out.println(proposta);
                        }
                        Utils.pressToContinue();
                        return true;
                    }
                    case 3 -> {
                        ArrayList<PoEProposta> projetos = fsm.getPropostas();
                        for (PoEProposta proposta : projetos) {
                            if (proposta.getNrAlunoAtribuido() == null) {
                                System.out.println(proposta);
                            }
                        }
                        Utils.pressToContinue();
                        return true;
                    }
                    case 4 -> {
                        ArrayList<PoEProposta> projetos = fsm.getPropostas();
                        for (PoEProposta proposta : projetos) {
                            if (proposta.getNrAlunoAtribuido() != null) {
                                System.out.println(proposta);
                            }
                        }
                        Utils.pressToContinue();
                        return true;
                    }
                }
            }
        }
        Utils.pressToContinue();
        return false;
    }

    public static boolean menuAtribuicaoOrientador(PoEContext fsm, int option) {
        switch (option) {
            case 1 -> {
                if (fsm.isClosed()) {
                    System.out.println(
                            "[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada!\n[!] Apenas é possível consultar os dados.");
                    Utils.pressToContinue();
                    return true;
                }
                ArrayList<PoEProposta> projetos = fsm.getPropostasByType("T2");
                for (PoEProposta proposta : projetos) {
                    PoEOrientador orientador = new PoEOrientador(proposta.getDocente(), proposta);
                    proposta.setOrientador(orientador);
                    System.out.println("[·] Proposta com ID " + proposta.getId()
                            + " associada ao orientador com o email " + orientador.getDocente().getEmail());
                }
            }
            case 2 -> {
                int chooseSearchParam = PAInput.chooseOption("Escolha uma opção:", "Atribuir orientadores", "Consultar orientadores",
                        "Alterar orientadores", "Eliminar orientadores", "Voltar");
                switch (chooseSearchParam) {
                    case 1 -> {
                        String email = PAInput.readString("Email do orientador: ", true);
                        PoEDocente docente = fsm.getDocenteByEmail(email);
                        if (docente == null) {
                            System.out.println("[!] Não foi encontrado nenhum orientador com o email " + email);
                            return false;
                        }
                        String idProposta = PAInput.readString("ID da proposta: ", true);
                        PoEProposta proposta = fsm.getPropostaById(idProposta);
                        if (proposta == null) {
                            System.out.println("[!] Não foi encontrado nenhuma proposta com o ID " + idProposta);
                            return false;
                        }
                        PoEOrientador orientador = new PoEOrientador(docente, proposta);
                        proposta.setOrientador(orientador);
                    }
                    case 2 -> {
                        String email = PAInput.readString("Email do orientador: ", true);
                        PoEDocente docente = fsm.getDocenteByEmail(email);
                        if (docente != null) {
                            System.out.println(docente);
                        } else {
                            System.out.println("[!] Não foi encontrado nenhum orientador com o email " + email);
                            return false;
                        }
                    }
                    case 3 -> {
                        String idProposta = PAInput.readString("ID da proposta a alterar: ", true);
                        PoEProposta proposta = fsm.getPropostaById(idProposta);
                        if (proposta == null) {
                            System.out.println("[!] Não foi encontrado nenhuma proposta com o ID " + idProposta);
                            return false;
                        }
                        String email = PAInput.readString("Email do orientador a alterar: ", true);
                        PoEDocente docente = fsm.getDocenteByEmail(email);
                        if (docente == null) {
                            System.out.println("[!] Não foi encontrado nenhum orientador com o email " + email);
                            return false;
                        }
                        PoEOrientador orientador = new PoEOrientador(docente, proposta);
                        proposta.setOrientador(orientador);
                    }
                    case 4 -> {
                        String idProposta = PAInput.readString("ID da proposta a alterar: ", true);
                        PoEProposta proposta = fsm.getPropostaById(idProposta);
                        if (proposta == null) {
                            System.out.println("[!] Não foi encontrado nenhuma proposta com o ID " + idProposta);
                            return false;
                        }else{
                            System.out.println("[·] Orientador " + proposta.getDocente().getNome() + " removido com sucesso");
                            proposta.setOrientador(null);
                        }
                    }
                    case 5 -> {
                        return false;
                    }
                }
            }
            case 3 -> {

            }
            case 4 -> {

            }
        }
        Utils.pressToContinue();
        return true;
    }

}