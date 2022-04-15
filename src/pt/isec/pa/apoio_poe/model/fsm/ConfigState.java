package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.utils.PAInput;

import java.io.*;
import java.util.Scanner;

class ConfigState extends PoEStateAdapter {

    ConfigState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean closePhase(){
        // FALTA IMPLEMENTAR COISAS AQUI (tem de ser para cada ramo)
        if(data.getPropostas().size() >= data.getAlunos().size()){
            data.closePhase(getState());
            return true;
        } else {
            System.out.println("[!] Não existem propostas suficientes para fechar a fase de configuração.");
            return false;
        }
    }

    @Override
    public boolean isClosed() {
        return data.isPhaseClosed(getState());
    }

    @Override
    public boolean nextPhase(){
        changeState(new ApplicationOptState(context, data));
        return true;
    }

    @Override
    public boolean exitAndSave(){
        //rever
        return true;
    }

    @Override
    public boolean loadSave(String filename){
        //rever
        return true;
    }

    @Override
    public boolean addAlunosCSV(String filePath){
        try {
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            int line = 0, sucessos = 0;
            while(sc.hasNextLine()) {
                line++;
                String[] values = sc.nextLine().split(",");
                if(values.length != 7){
                    System.out.println("[!] Linha " + line + " do ficheiro CSV (alunos) tem um número de colunas inválido.");
                    continue;
                }
                long nrEstudante = Long.parseLong(values[0]);
                if (data.getAlunoById(nrEstudante) != null) {
                    System.out.println("[!] Aluno com o número " + nrEstudante + " já existe!");
                    continue;
                }
                String nome = values[1];
                String email = values[2];
                String curso = values[3];
                if (!curso.matches("LEI|LEI-PL|LEI-CE")) {
                    System.out.println("[!] Curso inválido! Por favor corrija a linha " + line + " do ficheiro.");
                    continue;
                }
                String ramo = values[4];
                if (!ramo.matches("DA|RAS|SI")) {
                    System.out.println("[!] Ramo inválido! Por favor corrija a linha " + line + " do ficheiro.");
                    continue;
                }
                double classificacao = Double.parseDouble(values[5]);
                if (classificacao < 0 || classificacao > 1) {
                    System.out.println("[!] Classificação inválida! Por favor corrija a linha " + line + " do ficheiro.");
                    continue;
                }

                boolean estagios;
                if (values[6].equalsIgnoreCase("true") || values[6].equalsIgnoreCase("false")) {
                    estagios = Boolean.parseBoolean(values[6]);
                } else {
                    System.out.println("[!] Valor booleano inválido! Por favor corrija a linha " + line + " do ficheiro.");
                    continue;
                }
                PoEAluno aluno = new PoEAluno(nome, nrEstudante, email, curso, ramo, classificacao, estagios);
                data.addAluno(aluno);
                sucessos++;
            }
            System.out.println("\n[·] Foram adicionados " + sucessos + " alunos.");
            sc.close();
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("[!] Não foi possível abrir o ficheiro.");
            return false;
        }
    }

    @Override
    public boolean addDocentesCSV(String filePath){
        try {
            //String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (docentes): ", false);
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            int line = 0, sucessos = 0;
            while(sc.hasNextLine()) {
                line++;
                String[] values = sc.nextLine().split(",");
                if(values.length != 2){
                    System.out.println("[!] Linha " + line + " do ficheiro CSV (docentes) tem um número de colunas inválido.");
                    continue;
                }
                String nome = values[0];
                if(data.getDocenteByName(nome) != null){
                    System.out.println("[!] Docente com o nome " + nome + " já existe!");
                    continue;
                }
                String email = values[1];
                if(data.getDocenteByEmail(email) != null){
                    System.out.println("[!] Docente com o email " + email + " já existe!");
                    continue;
                }
                PoEDocente docente = new PoEDocente(nome, email);
                data.addDocente(docente);
                sucessos++;
            }
            System.out.println("\n[·] Foram adicionados " + sucessos + " docentes.");
            sc.close();
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("[!] Não foi possível abrir o ficheiro.");
            return false;
        }
    }

    @Override
    public boolean addPropostasCSV(String filePath){
        try {
            //String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (propostas): ", false);
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            int line = 0, sucessos = 0;
            while(sc.hasNextLine()) {
                line++;
                String[] values = sc.nextLine().split(",");
                String tipo = values[0];
                if(!tipo.matches("T1|T2|T3")){
                    System.out.println("[!] Tipo de proposta inválido! Por favor corrija a linha " + line + " do ficheiro.");
                    continue;
                }
                String id = values[1];
                if(data.getPropostaById(id) != null){
                    System.out.println("[!] Proposta com o id " + id + " já existe!");
                    continue;
                }

                if(tipo.equalsIgnoreCase("T1")){
                    String[] ramos = values[2].split("\\|");
                    boolean flag = false;
                    for(String ramo : ramos){
                        if(!ramo.matches("DA|RAS|SI")) {
                            System.out.println("[!] Ramo inválido! Por favor corrija a linha " + line + " do ficheiro.");
                            flag = true;
                            break;
                        }
                    }
                    if(flag) continue;
                    String titulo = values[3];
                    String entidade = values[4];
                    Long nrAlunoAtribuido = null;
                    if(values.length == 6){
                        nrAlunoAtribuido = Long.parseLong(values[5]);
                    }
                    PoEEstagio estagio = new PoEEstagio(id, titulo, nrAlunoAtribuido, ramos, entidade);
                    data.addProposta(estagio);
                    sucessos++;
                }
                else if(tipo.equalsIgnoreCase("T2")){
                    String[] ramos = values[2].split("\\|");
                    boolean flag = false;
                    for(String ramo : ramos){
                        if(!ramo.matches("DA|RAS|SI")) {
                            System.out.println("[!] Ramo inválido! Por favor corrija a linha " + line + " do ficheiro.");
                            flag = true;
                            break;
                        }
                    }
                    if(flag) continue;
                    String titulo = values[3];
                    String docente = values[4];
                    if(data.getDocenteByEmail(docente) == null){
                        System.out.println("[!] Docente com o email " + docente + " não existe!");
                        continue;
                    }
                    PoEDocente doc = data.getDocenteByEmail(docente);
                    Long nrAlunoAtribuido = null;
                    if(values.length == 6){
                        nrAlunoAtribuido = Long.parseLong(values[5]);
                    }
                    PoEProjeto projeto = new PoEProjeto(id, titulo, nrAlunoAtribuido, ramos, doc);
                    data.addProposta(projeto);
                    sucessos++;
                }
                else if(tipo.equalsIgnoreCase("T3")){
                    String titulo = values[2];
                    String emailEstudante = values[3];
                    if(data.getAlunoByEmail(emailEstudante) == null){
                        System.out.println("[!] Aluno com o email " + emailEstudante + " não existe!");
                        continue;
                    }
                    PoEAluno aluno = data.getAlunoByEmail(emailEstudante);
                    PoEAutoproposto autoproposto = new PoEAutoproposto(id, titulo, aluno);
                    data.addProposta(autoproposto);
                    sucessos++;
                }
                else{
                    System.out.println("[!] Tipo de proposta inválido! Por favor corrija a linha " + line + " do ficheiro.");
                }
            }
            System.out.println("\n[·] Foram adicionadas " + sucessos + " propostas.");
            sc.close();
            return true;
        } catch (FileNotFoundException e){
            System.out.println("[!] Não foi possível abrir o ficheiro.");
            return false;
        }
    }

    @Override
    public boolean saveAlunosCSV(String filename) {
        try {
            if(filename.endsWith(".csv")){
                filename = filename.substring(0, filename.length() - 4);
            }
            File f = new File(filename + ".csv");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int success = 0;
            for(PoEAluno aluno : data.getAlunos()){
                String csv = String.join(",", aluno.toStringArray());
                pw.println(csv);
                pw.flush();
                success++;
            }
            pw.close();
            System.out.println("[·] Foram exportados " + success + " alunos para o ficheiro CSV");
            return true;
        } catch (IOException ignored){
            System.out.println("[!] Ocorreu um erro ao abrir o ficheiro para escrita");
            return false;
        }
    }

    @Override
    public PoEState getState(){
        return PoEState.CONFIG;
    }
}
