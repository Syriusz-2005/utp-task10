package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ContentPanel extends JLayeredPane {
    private final JPanel bg = new JPanel();
    public ContentPanel(DefaultListModel<FutureTask<?>> tasks, ExecutorService pool) {
        Dimension dim = new Dimension(1300, 750);
        setPreferredSize(dim);
        bg.setSize(dim);
        bg.setBackground(Color.BLACK);
        add(bg, JLayeredPane.DEFAULT_LAYER);

        ParticleScene scene = new ParticleScene();
        JPanel sceneContainer = scene.getContainer();
        sceneContainer.setSize(dim);
        scene.addItem(new RocketEmitter(130));
        for (int i = 0; i < 100; i++) {
            Particle star = new Particle();
            star.color = Color.WHITE;
            star.lifetime = Integer.MAX_VALUE;
            star.size = Utils.randInt(1, 4);
            star.pos = new Vec2f(Utils.randInt(0, dim.width), Utils.randInt(0, dim.height));
            scene.addParticle(star);
        }
        add(sceneContainer, JLayeredPane.PALETTE_LAYER);
        tasks.addElement(scene.getTask());

        ShootingStarEmitter shootingStarEmitter = new ShootingStarEmitter(100, tasks, pool);
        scene.addItem(shootingStarEmitter);

        Timer timer = new Timer();
        JPanel timerContainer = timer.getContainer();
        timerContainer.setSize(dim);
        add(timerContainer, JLayeredPane.MODAL_LAYER);
        tasks.addElement(timer.getTask());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                FireworkRocket customRocket = new FireworkRocket(scene, Utils.randInt(160, 250), x, Color.CYAN);
                customRocket.setColor(Color.CYAN);
                scene.addItem(customRocket);
            }
        });
    }
}
