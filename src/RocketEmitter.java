import java.awt.*;

public class RocketEmitter implements SceneItem {
    private final int frequency;
    private int frameIdx = 0;
    public RocketEmitter(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public void step(int length, ParticleScene scene) {
        frameIdx += 1;
        if (frameIdx % frequency == 0) {
            SceneItem rocket = new FireworkRocket(scene, Utils.randInt(90, 300), Utils.randInt(40, 1240), Color.DARK_GRAY);
            scene.addItem(rocket);
        }
    }

    @Override
    public int getLifetime() {
        return Integer.MAX_VALUE;
    }
}
