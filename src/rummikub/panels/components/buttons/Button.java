package rummikub.panels.components.buttons;

import rummikub.panels.components.Drawable;

import java.awt.*;
import java.util.function.Supplier;

public abstract class Button extends Drawable {

    public Button(Point position, int width, int height) {
        super(position, width, height);
    }

    public boolean isButtonClicked(Point mp) {
        if (position.x < mp.x && mp.x < position.x + width)
            return position.y < mp.y && mp.y < position.y + height;
        return false;
    }

    public void ifClicked(Point mp, Supplier<Void> f) {
        if (isButtonClicked(mp)) {
            f.get();
        }
    }

    @Deprecated
    public boolean isAvailableToClick() {
        return false;
    }

}
