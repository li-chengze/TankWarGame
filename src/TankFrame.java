import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 600;

    private Tank player = new Tank(100, 100, this, Group.GOOD);

    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> enemies = new ArrayList<>();

    public void draw(){
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        setBackground(Color.black);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("bullets count: " + bullets.size(), 50, 50);
        g.drawString("enemies count: " + enemies.size(), 50, 100);
        g.setColor(color);

        player.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        // collision check
        for (int j = 0; j < enemies.size(); j++) {
            for (int i = 0; i < bullets.size(); i++) {
                Tank enemy = enemies.get(j);
                Bullet bullet = bullets.get(i);
                if (enemy.collide(bullet)){
                    enemy.die();
                    bullet.die();
                }
            }
        }
    }
}
