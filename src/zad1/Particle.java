package zad1;

import java.awt.*;

public class Particle {
    public Vec2f pos = Vec2f.ZERO.clone();
    public Vec2f vel = Vec2f.ZERO.clone();
    public int lifetime = 100;
    public Color color = Color.RED;
    public int size = 5;

    public void step(int length) {
        lifetime -= length;
        pos.add(vel);
    }

    public void render(Graphics g) {
        g.setColor(color);
        Vec2i p = pos.toInt();
        g.fillOval(p.x, p.y, size, size);
    }

    @Override
    public String toString() {
        return "Particle{" +
                "pos=" + pos +
                ", vel=" + vel +
                ", lifetime=" + lifetime +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}
