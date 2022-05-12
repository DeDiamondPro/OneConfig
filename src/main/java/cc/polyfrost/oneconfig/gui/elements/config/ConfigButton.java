package cc.polyfrost.oneconfig.gui.elements.config;

import cc.polyfrost.oneconfig.config.OneConfigConfig;
import cc.polyfrost.oneconfig.config.interfaces.BasicOption;
import cc.polyfrost.oneconfig.gui.elements.BasicButton;
import cc.polyfrost.oneconfig.lwjgl.RenderManager;
import cc.polyfrost.oneconfig.lwjgl.font.Fonts;
import org.lwjgl.nanovg.NanoVG;

import java.lang.reflect.Field;

public class ConfigButton extends BasicOption {
    private final BasicButton button;

    public ConfigButton(Field field, Object parent, String name, int size, String text) {
        super(field, parent, name, size);
        Runnable runnable = () -> {
        };
        try {
            runnable = (Runnable) get();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.button = new BasicButton(size == 1 ? 128 : 256, 32, text, null, null, 1, BasicButton.ALIGNMENT_CENTER, runnable);
    }

    @Override
    public void draw(long vg, int x, int y) {
        button.disable(!isEnabled());
        if(!isEnabled()) NanoVG.nvgGlobalAlpha(vg, 0.5f);
        RenderManager.drawString(vg, name, x, y + 17, OneConfigConfig.WHITE, 14f, Fonts.MEDIUM);
        button.draw(vg, x + (size == 1 ? 352 : 736), y);
        NanoVG.nvgGlobalAlpha(vg, 1f);
    }

    @Override
    public int getHeight() {
        return 32;
    }
}
