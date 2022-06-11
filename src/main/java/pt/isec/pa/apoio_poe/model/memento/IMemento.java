package pt.isec.pa.apoio_poe.model.memento;

public interface IMemento {
    default Object getSnapshot() { return null; }
}
