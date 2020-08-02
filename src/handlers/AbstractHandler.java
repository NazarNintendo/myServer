package handlers;

public class AbstractHandler {
    protected boolean useArtifactLogging;

    public AbstractHandler(boolean useArtifactLogging) {
        this.useArtifactLogging = useArtifactLogging;
    }
}
