import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MovingTextApplet extends Applet implements ActionListener {
    private String text;
    private int x, y, dx, dy;
    private Color color;
    private Choice colorChoice;

    public void init() {
        text = "Hello, world!";
        x = 0;
        y = 50;
        dx = 5;
        dy = 0;

        colorChoice = new Choice();
        colorChoice.add("Red");
        colorChoice.add("Green");
        colorChoice.add("Blue");
        colorChoice.addActionListener(this);
        add(colorChoice);
    }

    public void start() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x += dx;
                    y += dy;
                    if (x < 0 || x + getGraphics().getFontMetrics().stringWidth(text) > getWidth()) {
                        dx = -dx;
                        color = getRandomColor();
                    }
                    if (y < 0 || y + getGraphics().getFontMetrics().getHeight() > getHeight()) {
                        dy = -dy;
                        color = getRandomColor();
                    }
                    repaint();
                }
            }
        });
        t.start();
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.drawString(text, x, y);
    }

    public void actionPerformed(ActionEvent e) {
        String selectedColor = colorChoice.getSelectedItem();
        if (selectedColor.equals("Red")) {
            color = Color.RED;
        } else if (selectedColor.equals("Green")) {
            color = Color.GREEN;
        } else if (selectedColor.equals("Blue")) {
            color = Color.BLUE;
        }
    }

    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }
} 