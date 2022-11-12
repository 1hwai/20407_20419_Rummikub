package rummikub.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Drawable {
    private BufferedImage image = null;

    public final int width, height;
    public final Point position;

    protected Drawable(Point position, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public void setImage(String path) throws IOException {
        image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, width, height, null);
    }

}
