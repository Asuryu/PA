package pt.isec.pa.apoio_poe.utils;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.utils.PAInput;
import pt.isec.pa.apoio_poe.utils.Utils;

import java.util.ArrayList;

public class Menu {

    private Menu(){}

    // Returns true if the user wants to change modes
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
                    }
                    case 3 -> {
                        long numero = PAInput.readLong("Número de aluno: ");
                        PoEAluno aluno = fsm.getAlunoById(numero);
                        if(aluno != null){
                            System.out.println(aluno);
                        } else {
                            System.out.println("Não existe nenhum aluno com o número " + numero);
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
                    System.out.println("[·] Não existe nenhum aluno com o número " + nrAluno);
                }
            }
            case 6 -> {
                return true;
            }
        }
        Utils.pressToContinue();
        return false;
    }

    // Returns true if the user wants to change modes
    public static boolean menuDocentes(PoEContext fsm){
        int escolhaDocentes = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Docentes]\nEscolha uma opção", "Importar docentes a partir de um ficheiro CSV", "Exportar docentes para um ficheiro CSV", "Consultar docentes", "Editar docente", "Remover docente", "Voltar");
        Utils.pressToContinue();
        return false;
    }
    // Returns true if the user wants to change modes
    public static boolean menuPropostas(PoEContext fsm){
        int escolhaPropostas = PAInput.chooseOption("[CONFIGURAÇÃO - Gerir Propostas]\nEscolha uma opção", "Importar propostas a partir de um ficheiro CSV", "Exportar propostas para um ficheiro CSV", "Consultar propostas", "Editar proposta", "Remover proposta", "Voltar");
        Utils.pressToContinue();
        return false;
    }
}
