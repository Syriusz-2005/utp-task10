package zad1;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.concurrent.FutureTask;

public class Timer {
    private final JLabel label = new JLabel("To 2025");
    private final FutureTask<Void> task = new FutureTask<Void>(() -> {
        while (!Thread.interrupted()) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime newYear = LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR) + 1, 1, 1, 0, 0, 0);
            Duration delta = Duration.between(now, newYear);
            // Java 9 adds to<unit>Part() method that would be very convenient here
            long days = delta.toDays();
            long hours = delta.minusDays(days).toHours();
            long minutes = delta.minusDays(days).minusHours(hours).toMinutes();
            long seconds = delta.minusDays(days).minusHours(hours).minusMinutes(minutes).getSeconds();
            label.setText(days + " days, " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds to " + newYear.getYear());
            Thread.sleep(100);
        }
        return null;
    }){
        @Override
        public String toString() {
            return "Timer " + (task.isCancelled() ? "(cancelled)" : "(running)");
        }
    };
    private final JPanel container = new JPanel();

    public Timer() {
        container.setOpaque(false);
        container.setLayout(new GridBagLayout());
        label.setBackground(Color.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        label.setForeground(Color.WHITE);
        container.add(label);
    }

    public FutureTask<Void> getTask() {
        return task;
    }

    public JPanel getContainer() {
        return container;
    }
}
