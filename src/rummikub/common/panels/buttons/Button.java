package rummikub.common.panels.buttons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Button {
    private BufferedImage image = null;

    public final int width, height;
    public final Point position;

    private String innerText;

    public Button(Point position, int width, int height, String innerText) {
        this(position, width, height);
        this.innerText = innerText;
    }

    public Button(Point position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public boolean isButtonClicked(Point mp) {
        if (position.x < mp.x && mp.x < position.x + width)
            return position.y < mp.y && mp.y < position.y + height;
        return false;
    }

    public void ifButtonClicked(Point mp, Supplier<Void> f) {
        if (isButtonClicked(mp)) {
            f.get();
        }
    }

    public boolean isAvailableToClick() {
        return false;
    }

    public String getInnerText() {
        return innerText;
    }

    public void setImage(String path) throws IOException {
        image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
    }

    public BufferedImage getImage() {
        return image;
    }
}
