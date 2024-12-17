import javax.swing.*;
import java.awt.*;
import java.util.concurrent.FutureTask;

public class ShootingStarEmitter implements SceneItem {
    private final int frequency;
    private int frameIdx = 0;
    private final DefaultListModel<FutureTask<?>> tasks;
    public ShootingStarEmitter(int frequency, DefaultListModel<FutureTask<?>> tasks) {
        this.frequency = frequency;
        this.tasks = tasks;
    }

    @Override
    public void step(int length, ParticleScene scene) {
        frameIdx += 1;
        if (frameIdx % frequency == 0) {
            ShootingStar shootingStar = new ShootingStar(scene);
            scene.addItem(shootingStar);
            tasks.addElement(shootingStar.getTask());
        }
    }
}
