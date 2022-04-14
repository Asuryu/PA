package pt.isec.pa.apoio_poe.model.fsm;

import com.sun.tools.jconsole.JConsoleContext;
import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEData;
import pt.isec.pa.apoio_poe.model.data.PoEDocente;
import pt.isec.pa.apoio_poe.utils.PAInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PoEContext {
    IPoEState state;
    PoEData data;

    public PoEContext(){
        data = new PoEData();
        state = new ConfigState(this, data);
    }

    void changeState(IPoEState newState){
        state = newState;
    }

    public PoEState getState(){
        if(state == null)
            return null;
        return state.getState();
    }

    public boolean closePhase(){
        return state.closePhase();
    }

    public boolean isClosed(){ return state.isClosed(); }

    public boolean nextPhase(){
        return state.nextPhase();
    }

    public boolean previousPhase(){
        return state.previousPhase();
    }

    public boolean exitAndSave(){
        return state.exitAndSave();
    }

    public boolean loadSave(String filename){
        return state.loadSave(filename);
    }

    public boolean addAlunosCSV(){ return state.addAlunosCSV(); }

    public boolean addDocentesCSV(){ return state.addDocentesCSV(); }

}
