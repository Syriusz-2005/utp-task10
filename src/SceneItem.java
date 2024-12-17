import java.awt.*;

public interface SceneItem {
    default void step(int length, ParticleScene scene) {}
    default int getLifetime() {
        return Integer.MAX_VALUE;
    }
    default void render(Graphics g) {}
}
