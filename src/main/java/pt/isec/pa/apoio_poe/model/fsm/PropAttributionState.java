package pt.isec.pa.apoio_poe.model.fsm;

import java.io.*;
import java.util.ArrayList;

import pt.isec.pa.apoio_poe.model.data.*;

/**
 * A classe PropAttributionState implementa o estado de atribuição de propostas.
 */
class PropAttributionState extends PoEStateAdapter implements Serializable{
    static final long serialVersionUID = 112L;
    PropAttributionState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean previousPhase(){
        changeState(new ApplicationOptState(context, data));
        return true;
    }

    @Override
    public boolean nextPhase(){
        changeState(new OriAttributionState(context, data));
        return true;
    }

    @Override
    public boolean closePhase(){
        if(isClosed()){
            System.out.println("[!] A fase de ATRIBUIÇÃO DE PROPOSTAS já se encontra fechada.");
            return false;
        }
        ArrayList<PoEAluno> alunos = data.getAlunos();
        boolean flag = false;
        for(PoEAluno aluno : alunos){
            if(aluno.getCandidatura() != null){
                if(aluno.getPropostaAtribuida() == null){
                    flag = true;
                    System.out.println("[!] O aluno com o número " + aluno.getNrEstudante() + " ainda não tem proposta atribuída.");
                }
            }
        }
        if(flag || alunos.size() == 0){
            // Utils.pressToContinue();
            return false;
        }
        System.out.println("[!] Fase de ATRIBUIÇÃO DE PROPOSTAS fechada com sucesso!");
        data.closePhase(getState());
        nextPhase();
        return true;
    }

    @Override
    public boolean saveAlunosCSV(String filename){
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
                if(aluno.getPropostaAtribuida() != null){
                    csv += "," + aluno.getPropostaAtribuida().getId();
                } else {
                    csv += ",null";
                }
                if(aluno.getCandidatura() != null && aluno.getCandidatura().getPreferencias().size() > 0){
                    csv += "," + String.join(",", aluno.getCandidatura().getPreferencias());
                }
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
    public boolean isClosed() {
        return data.isPhaseClosed(getState());
    }

    @Override
    public PoEState getState(){
        return PoEState.PROP_ATTRIBUTION;
    }
}
