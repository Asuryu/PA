package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;
import java.util.ArrayList;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEData;
import pt.isec.pa.apoio_poe.utils.Utils;

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
        // Fase só pode ser fechada se todos os alunos com candidaturas tiverem projeto atribuído
        ArrayList<PoEAluno> alunos = data.getAlunos();
        boolean flag = false;
        for(PoEAluno aluno : alunos){
            if(aluno.getCandidatura() != null){
                if(aluno.getPropostaAtribuida() == null){
                    flag = true;
                    System.out.println("[!] O aluno com o número " + aluno.getNrEstudante() + " ainda não tem projeto atribuído.");
                }
            }
        }
        if(flag){
            Utils.pressToContinue();
            return false;
        }
        System.out.println("[!] Fase de ATRIBUIÇÃO DE ORIENTADORES fechada com sucesso!");
        data.closePhase(getState());
        nextPhase();
        return true;
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
