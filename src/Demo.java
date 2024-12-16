import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Demo extends JFrame {
    private final JList<FutureTask<?>> list1 = new JList<>();
    private final JLayeredPane content;
    public Demo() {
        DefaultListModel<FutureTask<?>> model = new DefaultListModel<>();
        content = new ContentPanel(model);
        add(list1);
        add(content);
        var layout = new GridBagLayout();
        list1.setFixedCellWidth(200);
        setLayout(layout);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        list1.setModel(model);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < model.size(); i++) {
            service.submit(model.get(i));
        }
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Demo::new);
    }

}