package live.u14.app.animation;

/**
 * Created by Kevin Dong on 2017/4/12.
 */
public interface RevealViewGroup {

    /**
     * @return Bridge between view and circular reveal animation
     */
    ViewRevealManager getViewRevealManager();

    /**
     *
     * @param manager
     */
    void setViewRevealManager(ViewRevealManager manager);
}
