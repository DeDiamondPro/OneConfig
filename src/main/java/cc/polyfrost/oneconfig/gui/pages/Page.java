package cc.polyfrost.oneconfig.gui.pages;

import cc.polyfrost.oneconfig.gui.OneConfigGui;
import cc.polyfrost.oneconfig.lwjgl.scissor.Scissor;
import cc.polyfrost.oneconfig.lwjgl.scissor.ScissorManager;
import cc.polyfrost.oneconfig.utils.MathUtils;
import org.lwjgl.input.Mouse;

/**
 * A page is a 1056x728 rectangle of the GUI. It is the main content of the gui, and can be switched back and forwards easily. All the content of OneConfig is in a page.
 */
public abstract class Page {
    private float currentScroll = 0f;
    private float scrollTarget;

    protected final String title;

    Page(String title) {
        this.title = title;
    }

    public abstract void draw(long vg, int x, int y);

    /**
     * Use this method to draw elements that are static on the page (ignore the scrolling).
     *
     * @return the total height of the elements, so they are excluded from the scissor rectangle.
     */
    public int drawStatic(long vg, int x, int y) {
        return 0;
    }

    public void finishUpAndClose() {
    }

    public void scrollWithDraw(long vg, int x, int y) {     // TODO scroll bar
        int maxScroll = getMaxScrollHeight();
        int scissorOffset = drawStatic(vg, x, y);
        Scissor scissor = ScissorManager.scissor(vg, x, y + scissorOffset, x + 1056, y + 728 - scissorOffset);
        if (maxScroll <= 728) {
            draw(vg, x, y);
            ScissorManager.resetScissor(vg, scissor);
            return;
        }
        draw(vg, x, (int) (y + currentScroll));
        int dWheel = Mouse.getDWheel();
        scrollTarget += dWheel;

        if (scrollTarget > 0f) scrollTarget = 0f;
        else if (scrollTarget < -maxScroll + 728) scrollTarget = -maxScroll + 728;

        currentScroll = MathUtils.easeOut(currentScroll, scrollTarget, 50f);
        ScissorManager.resetScissor(vg, scissor);
    }

    public String getTitle() {
        return title;
    }

    public void keyTyped(char key, int keyCode) {
    }

    /**
     * Overwrite this method and make it return true if you want this to always be the base in breadcrumbs
     */
    public boolean isBase() {
        return false;
    }

    /**
     * Use this method to set the maximum scroll height of the page.
     */
    public int getMaxScrollHeight() {
        return 728;
    }
}
