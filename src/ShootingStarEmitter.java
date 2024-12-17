import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ShootingStarEmitter implements SceneItem {
    private final int frequency;
    private int frameIdx = 0;
    private final DefaultListModel<FutureTask<?>> tasks;
    private final ExecutorService pool;
    public ShootingStarEmitter(int frequency, DefaultListModel<FutureTask<?>> tasks, ExecutorService pool) {
        this.frequency = frequency;
        this.tasks = tasks;
        this.pool = pool;
    }

    @Override
    public void step(int length, ParticleScene scene) {
        frameIdx += 1;
        if (frameIdx % frequency == 0) {
            ShootingStar shootingStar = new ShootingStar(scene);
            scene.addItem(shootingStar);
            tasks.addElement(shootingStar.getTask());
            pool.submit(shootingStar.getTask());
        }
    }
}
