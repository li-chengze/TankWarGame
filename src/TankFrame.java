import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 600;

    private Tank player = new Tank(100, 100, this);

    public List<Bullet> bullets = new ArrayList<Bullet>();

    public void draw(){
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("bullets count: " + bullets.size(), 50, 50);

        player.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
    }
}
