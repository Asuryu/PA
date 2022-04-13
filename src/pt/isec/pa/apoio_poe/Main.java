package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.utils.PAInput;

public class Main {
    public static void main(String[] args){
        PAInput.chooseOption("Escolha uma opção", "Gestão de Alunos", "Gestão de Docentes", "Gestão de propostas de estágio ou projeto", "Sair");
    }
}