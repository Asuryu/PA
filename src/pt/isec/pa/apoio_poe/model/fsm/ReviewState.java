package pt.isec.pa.apoio_poe.model.fsm;

import java.io.*;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEData;

class ReviewState extends PoEStateAdapter implements Serializable{
    static final long serialVersionUID = 113L;
    ReviewState(PoEContext context, PoEData data) {
        super(context, data);
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
    public PoEState getState(){
        return PoEState.REVIEW;
    }
}
