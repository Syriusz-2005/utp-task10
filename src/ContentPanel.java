import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.FutureTask;

public class ContentPanel extends JLayeredPane {
    private final JPanel bg = new JPanel();
    public ContentPanel(DefaultListModel<FutureTask<?>> tasks) {
        var dim = new Dimension(1300, 750);
        setPreferredSize(dim);
        bg.setSize(dim);
        bg.setBackground(Color.BLACK);
        add(bg, JLayeredPane.DEFAULT_LAYER);

        var scene = new ParticleScene();
        var sceneContainer = scene.getContainer();
        sceneContainer.setSize(dim);
        scene.addItem(new RocketEmitter(130));
        add(sceneContainer, JLayeredPane.PALETTE_LAYER);
        tasks.addElement(scene.getTask());

        var timer = new Timer();
        var timerContainer = timer.getContainer();
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
