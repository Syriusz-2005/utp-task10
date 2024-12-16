import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class ParticleScene {
    public final long frameLengthNs = 14_000_000;
    private final int stepLength = 1;
    private final List<SceneItem> items = new ArrayList<>();
    private List<SceneItem> newItemsQueue = new ArrayList<>();
    private final List<Particle> particles = new ArrayList<>();
    private final JPanel container = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            var bounds = g.getClipBounds();
            g.clearRect(0, 0, bounds.x, bounds.y);
            synchronized (particles) {
                for (var particle : particles) {
                    particle.render(g);
                }
            }
        }
    };
    private final FutureTask<Void> task = new FutureTask<>(() -> {
        try {
            int frameIdx = 0;
            while (!Thread.interrupted()) {
                long prev = System.nanoTime();
                for (var item : items) {
                    item.step(stepLength, this);
                }
                items.addAll(newItemsQueue);
                newItemsQueue = new ArrayList<>();
                synchronized (particles) {
                    for (var particle : particles) {
                        particle.step(stepLength);
                    }
                    particles.removeIf((p) -> p.lifetime < 0);
                }
                items.removeIf((p) -> p.getLifetime() < 0);
                container.getParent().getParent().repaint();
                long curr = System.nanoTime();
                long renderTimeNs = curr - prev;
                long sleepTime = Math.max(0, frameLengthNs - renderTimeNs) / 1_000_000;
                frameIdx++;
                if (frameIdx % 100 == 0) {
                    System.out.println("Task free time: " + sleepTime + "ms, particles: " + particles.size());
                }
                Thread.sleep(sleepTime);
            }
        } catch(Exception err) {
            err.printStackTrace();
            throw err;
        }
        return null;
    }){
        @Override
        public String toString() {
            return "Particle renderer (" + particles.size() + " particles)";
        }
    };
    public ParticleScene() {
        container.setOpaque(false);
    }

    public void addParticle(Particle p) {
        synchronized (particles) {
            particles.add(p);
        }
    }

    public void addItem(SceneItem item) {
        newItemsQueue.add(item);
    }

    public JPanel getContainer() {
        return container;
    }
    public FutureTask<Void> getTask() {
        return task;
    }
}
