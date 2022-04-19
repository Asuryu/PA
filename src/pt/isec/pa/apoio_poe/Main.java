package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.data.PoEDocente;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.ui.text.PoEUI;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PoEContext fsm = new PoEContext();
        PoEUI ui = new PoEUI(fsm);
        ui.start();

//        <ESCREVER OBJETO PARA UM FICHEIRO>
//        PoEDocente docente = new PoEDocente("Tomás", "a2020143845@isec.pt");
//        FileOutputStream fos = new FileOutputStream("docente.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fos);
//        out.writeObject(docente);


//        <LER OBJETO DE UM FICHEIRO>
//        FileInputStream fis = new FileInputStream("docente.ser");
//        ObjectInputStream in = new ObjectInputStream(fis);
//        PoEDocente docente = (PoEDocente) in.readObject();
//        System.out.println(docente);
    }
}