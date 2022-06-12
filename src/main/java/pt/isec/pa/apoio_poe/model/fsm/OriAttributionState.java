package pt.isec.pa.apoio_poe.model.fsm;

import java.io.*;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEData;

/**
 * A classe OriAttributionState implementa o estado de atribuição de orientadores.
 */
class OriAttributionState extends PoEStateAdapter implements Serializable{
    static final long serialVersionUID = 109L;
    OriAttributionState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean previousPhase(){
        changeState(new PropAttributionState(context, data));
        return true;
    }

    @Override
    public boolean closePhase(){
        if(isClosed()){
            System.out.println("[!] A fase de ATRIBUIÇÃO DE ORIENTADORES já se encontra fechada.");
            return false;
        }
        data.closePhase(getState());
        changeState(new ReviewState(context, data));
        System.out.println("[·] Fase de ATRIBUIÇÃO DE ORIENTADORES fechada com sucesso!");
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
                if(aluno.getPropostaAtribuida() != null){
                    if(aluno.getPropostaAtribuida().getOrientador() != null){
                        csv += "," + aluno.getPropostaAtribuida().getOrientador().getDocente().getEmail();
                    }
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
        return PoEState.ORI_ATTRIBUTION;
    }
}
