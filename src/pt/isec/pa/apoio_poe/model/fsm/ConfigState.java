package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEData;
import pt.isec.pa.apoio_poe.model.data.PoEDocente;
import pt.isec.pa.apoio_poe.utils.PAInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ConfigState extends PoEStateAdapter {

    ConfigState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean closePhase(){
        data.closePhase(getState());
        return true;
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
    public boolean addAlunosCSV(){
        try {
            String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (alunos): ", false);
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            int line = 0, sucessos = 0;
            while(sc.hasNextLine()) {
                line++;
                String[] values = sc.nextLine().split(",");
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
            System.out.println("[*] Foram adicionados " + sucessos + " alunos.");
            sc.close();
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("[!] Não foi possível abrir o ficheiro.");
            return false;
        }
    }

    @Override
    public boolean addDocentesCSV(){
        try {
            String filePath = PAInput.readString("Introduza o nome do ficheiro CSV (docentes): ", false);
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            int line = 0, sucessos = 0;
            while(sc.hasNextLine()) {
                line++;
                String[] values = sc.nextLine().split(",");
                String nome = values[0];
                if(data.getDocenteByName(nome) != null){
                    System.out.println("[!] Docente com o nome " + nome + " já existe!");
                    continue;
                }
                String email = values[2];
                if(data.getDocenteByEmail(email) != null){
                    System.out.println("[!] Docente com o email " + email + " já existe!");
                    continue;
                }
                PoEDocente docente = new PoEDocente(nome, email);
                data.addDocente(docente);
                sucessos++;
            }
            System.out.println("[*] Foram adicionados " + sucessos + " docentes.");
            sc.close();
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("[!] Não foi possível abrir o ficheiro.");
            return false;
        }
    }

    @Override
    public PoEState getState(){
        return PoEState.CONFIG;
    }
}
