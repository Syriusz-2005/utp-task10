import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ShootingStar implements SceneItem {
    private final ParticleScene scene;
    private Vec2f pos;
    private final Vec2f vel = new Vec2f(Utils.randFloat(-3, 3), Utils.randFloat(2f, 3.6f));
    private final int size = 5;

    private final FutureTask<String> task = new FutureTask<>(() -> {
        int lifetime = Utils.randInt(100, 160);
        for (int i = 0; i < lifetime; i++) {
            Thread.sleep(13);
            pos = pos.add(vel);
        }

        return pos.toInt().toString();
    }){
        @Override
        public String toString() {
            try {
                return "Shooting star " + (isDone() ? task.get() : "(Running)");
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public ShootingStar(ParticleScene scene) {
        this.scene = scene;
        var dim = scene.getSize();
        pos = new Vec2f(Utils.randFloat(0, dim.width), Utils.randFloat(0, (float) dim.height / 2));
    }

    @Override
    public void render(Graphics g) {
        var renderedPos = pos.clone();
        var color = Color.WHITE;
        for (int i = 0; i < 6; i++) {
            g.setColor(color);
            g.fillOval((int) renderedPos.x, (int) renderedPos.y, size, size);
            color = color.darker();
            renderedPos.subtract(vel);
        }
    }

    public FutureTask<String> getTask() {
        return task;
    }

    @Override
    public int getLifetime() {
        return task.isDone() || task.isCancelled() ? -1 : 1;
    }
}
