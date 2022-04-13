package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.data.PoEData;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.ui.text.PoEUI;
import pt.isec.pa.apoio_poe.utils.PAInput;

public class Main {
    public static void main(String[] args){
        PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Sair");
        PoEContext fsm = new PoEContext();
        PoEUI ui = new PoEUI(fsm);
        ui.start();
    }
}