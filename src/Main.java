import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main extends JFrame {
    private final JList<FutureTask<?>> list1 = new JList<>();
    private final JLayeredPane content;
    public Main() {
        DefaultListModel<FutureTask<?>> model = new DefaultListModel<>();
        ExecutorService pool = Executors.newCachedThreadPool();
        content = new ContentPanel(model, pool);
        var scrollPane = new JScrollPane(list1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(220, 750));
        list1.setFixedCellWidth(210);
        add(scrollPane);
        add(content);
        var layout = new GridBagLayout();
        setLayout(layout);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        list1.setModel(model);
        for (int i = 0; i < model.size(); i++) {
            pool.submit(model.get(i));
        }
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}