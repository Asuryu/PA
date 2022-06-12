package pt.isec.pa.apoio_poe.model.data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa uma proposta.
 */
public class PoEProposta implements Serializable{
    static final long serialVersionUID = 106L;
    private final String id;
    private String titulo;
    private Long nrAlunoAtribuido;
    private final ArrayList<PoECandidatura> candidaturas;
    private PoEOrientador orientador;

    /**
     * Construtor de uma proposta.
     * @param id ID da proposta.
     * @param titulo Título da proposta.
     * @param nrAlunoAtribuido Número de estudante do aluno atribuído à proposta.
     */
    public PoEProposta(String id, String titulo, Long nrAlunoAtribuido) {
        this.id = id;
        this.titulo = titulo;
        this.nrAlunoAtribuido = nrAlunoAtribuido;
        this.candidaturas = new ArrayList<>();
    }

    /**
     * Construtor de uma proposta.
     * @param proposta Proposta a ser copiada.
     */
    public PoEProposta(PoEProposta proposta) {
        this.id = proposta.getId();
        this.titulo = proposta.getTitulo();
        this.nrAlunoAtribuido = proposta.getNrAlunoAtribuido();
        this.candidaturas = new ArrayList<>(proposta.getCandidaturas());
        this.orientador = proposta.getOrientador();
    }

    /**
     * Obtém o orientador da proposta.
     * @return Orientador da proposta.
     */
    public PoEOrientador getOrientador() {
        return orientador;
    }

    /**
     * Atribui um orientador à proposta.
     * @param orientador Orientador a ser atribuído.
     */
    public void setOrientador(PoEOrientador orientador) {
        this.orientador = orientador;
    }

    /**
     * Obtém o docente responsável pela proposta.
     * @return Docente responsável pela proposta.
     */
    public PoEDocente getDocente() {
        return null;
    }

    /**
     * Obtém o ID da proposta.
     * @return ID da proposta.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o título da proposta.
     * @return Título da proposta.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Atribui um título à proposta.
     * @param titulo Título a ser atribuído.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o número de estudante do aluno atribuído à proposta.
     * @return Número de estudante do aluno atribuído à proposta.
     */
    public Long getNrAlunoAtribuido() {
        return nrAlunoAtribuido;
    }

    /**
     * Atribui um número de estudante do aluno atribuído à proposta.
     * @param nrAlunoAtribuido Número de estudante do aluno atribuído à proposta.
     */
    public void setNrAlunoAtribuido(Long nrAlunoAtribuido) {
        this.nrAlunoAtribuido = nrAlunoAtribuido;
    }

    /**
     * Obtém os ramos de destino da proposta.
     * @return Ramos de destino da proposta.
     */
    public ArrayList<String> getRamosDestino(){ return new ArrayList<>(); }

    /**
     * Obtém o aluno associado à proposta.
     * @return Aluno associado à proposta.
     */
    public PoEAluno getAluno(){
        return null;
    }

    /**
     * Obtém a lista de candidaturas à proposta.
     * @return Lista de candidaturas à proposta.
     */
    public ArrayList<PoECandidatura> getCandidaturas() {
        return candidaturas;
    }

    /**
     * Adiciona uma candidatura à proposta.
     * @param candidatura Candidatura a ser adicionada.
     */
    public void addCandidatura(PoECandidatura candidatura) {
        candidaturas.add(candidatura);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("---- Proposta nº ").append(id).append(" ----\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Nr. Aluno Atribuido: ").append(nrAlunoAtribuido).append("\n");
        if(orientador != null){
            sb.append("Orientador: ").append(orientador.getDocente().getNome()).append("   (").append(orientador.getDocente().getEmail()).append(")\n");
        }
        return sb.toString();
    }

    public String[] toStringArray(){
        return new String[]{
                String.valueOf(id),
                titulo,
                String.valueOf(nrAlunoAtribuido)
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoEProposta that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
