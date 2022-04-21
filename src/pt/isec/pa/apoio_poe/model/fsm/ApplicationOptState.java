package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoECandidatura;
import pt.isec.pa.apoio_poe.model.data.PoEData;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class ApplicationOptState extends PoEStateAdapter{
    ApplicationOptState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean previousPhase(){
        changeState(new ConfigState(context, data));
        return true;
    }

    @Override
    public boolean nextPhase(){
        changeState(new PropAttributionState(context, data));
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
    public boolean closePhase(){
        if(data.isPhaseClosed(PoEState.CONFIG)){
            data.closePhase(getState());
            nextPhase();
            return true;
        } else {
            System.out.println("[!] A fase de configuração ainda não foi fechada.");
            return false;
        }
    }

    @Override
    public boolean isClosed() {
        return data.isPhaseClosed(getState());
    }

    /**
     * Adiciona candidaturas ao sistema a partir de um ficheiro CSV
     * @param filename Caminho do ficheiro CSV
     * @return True se não ocorreu nenhum erro
     */
    @Override
    public boolean addCandidaturasCSV(String filename){
        try{
            Scanner sc = new Scanner(new File(filename));
            sc.useDelimiter(",");
            int line = 0, sucessos = 0;
            while(sc.hasNextLine()){
                line++;
                String[] values = sc.nextLine().split(",");
                ArrayList<String> propostasValidas = new ArrayList<>();
                if(values.length < 2){
                    System.out.println("[!] Linha " + line + " do ficheiro CSV (candidaturas) tem um número de colunas inválido.");
                    continue;
                }
                if(data.getAlunoById(Long.parseLong(values[0])) == null){
                    System.out.println("[!] Não existe nenhum aluno com o número de aluno " + values[0] + " (linha " + line + ")");
                    continue;
                }
                for(int i = 1; i < values.length; i++){
                    if(data.getPropostaById(values[i]) == null){
                        System.out.println("[!] Não existe nenhuma proposta com o ID " + values[i] + " (linha " + line + ")");
                        continue;
                    }
                    if(data.getPropostaById(values[i]).getNrAlunoAtribuido() != null){
                        System.out.println("[!] Já existe um aluno atribuído à proposta com o ID " + values[i] + " (linha " + line + ")");
                        continue;
                    }
                    propostasValidas.add(values[i]);
                }
                if(propostasValidas.size() == 0){
                    System.out.println("[!] Não existe nenhuma proposta válida para o aluno com o número " + values[0] + " (linha " + line + ")");
                    continue;
                }
                PoECandidatura candidatura = new PoECandidatura(Long.parseLong(values[0]), propostasValidas);
                data.addCandidatura(candidatura);
                data.getAlunoById(Long.parseLong(values[0])).setCandidatura(candidatura);
                for(String proposta : propostasValidas){
                    data.getPropostaById(proposta).addCandidatura(candidatura);
                }
                sucessos++;
            }
            System.out.println("\n[·] Foram adicionadas " + sucessos + " candidaturas.");
            sc.close();
            return true;
        } catch (FileNotFoundException e){
            System.out.println("[!] Não foi possível abrir o ficheiro.");
            return false;
        }
    }

    /**
     * Guarda os alunos registados no sistema num ficheiro CSV
     * @param filename Caminho do ficheiro CSV
     * @return True se não ocorreu nenhum erro
     * False se ocorreu um erro ou não haviam alunos para exportar
     */
    @Override
    public boolean saveCandidaturasCSV(String filename){
        ArrayList<PoECandidatura> candidaturas = data.getCandidaturas();
        if(candidaturas.size() == 0){
            System.out.println("[!] Não existem candidaturas para exportar.");
            return false;
        }
        try {
            if(filename.endsWith(".csv")){
                filename = filename.substring(0, filename.length() - 4);
            }
            File f = new File(filename + ".csv");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            int success = 0;
            for(PoECandidatura candidatura : candidaturas){
                String csv = String.join(",", candidatura.toStringArray());
                pw.println(csv);
                pw.flush();
                success++;
            }
            pw.close();
            System.out.println("[·] Foram exportadas " + success + " candidaturas para o ficheiro CSV");
            return true;
        } catch (IOException ignored){
            System.out.println("[!] Ocorreu um erro ao abrir o ficheiro para escrita");
            return false;
        }
    }

    @Override
    public PoEState getState(){
        return PoEState.APPLICATION_OPT;
    }
}
