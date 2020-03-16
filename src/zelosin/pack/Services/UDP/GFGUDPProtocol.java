package zelosin.pack.Services.UDP;

        import zelosin.pack.Data.GFGCell;

        import java.io.Serializable;

public class GFGUDPProtocol implements Serializable {
    private static final long serialVersionUID = 1L;
    public GFGCell mGFGCell;
    public Integer mThreadCount;
    public GFGActionType mStatus;

    public GFGUDPProtocol(GFGCell mGFGCell, Integer mThreadCount, GFGActionType mStatus) {
        this.mGFGCell = mGFGCell;
        this.mThreadCount = mThreadCount;
        this.mStatus = mStatus;
    }

    public GFGUDPProtocol(GFGActionType mStatus) {
        this.mStatus = mStatus;
    }
}
