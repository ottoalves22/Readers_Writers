package ep;

public class contexto {
    int activeReaders = 0;
    boolean hasWriter = false;

    boolean getter_has_writer() {
        return this.hasWriter;
    }

    void setter_has_writer(boolean entry) {
        this.hasWriter = entry;
    }

    int getter_activer_readers() {
        return this.activeReaders;
    }

    void setter_active_readers(int entry) {
        this.activeReaders = entry;
    }
}
