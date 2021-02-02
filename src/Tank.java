import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tank {
    private int x;
    private int y;
    private static int SPEED = 10;
    private TankFrame tankFrame;

    private static int TANK_WIDTH = 50;
    private static int TANK_HEIGHT = 50;

    private Dir dir = Dir.DOWN;

    public Tank(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        this.tankFrame.addKeyListener(new MyKeyAdapter());
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
    }

    private void fire() {
        int bX = x + (TANK_WIDTH / 2);
        int bY = y + (TANK_HEIGHT / 2);
        tankFrame.bullets.add(new Bullet(bX, bY, dir, this.tankFrame));
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    dir = Dir.LEFT;
                    x -= SPEED;
                    break;
                case KeyEvent.VK_RIGHT:
                    dir = Dir.RIGHT;
                    x += SPEED;
                    break;
                case KeyEvent.VK_UP:
                    dir = Dir.UP;
                    y -= SPEED;
                    break;
                case KeyEvent.VK_DOWN:
                    dir = Dir.DOWN;
                    y += SPEED;
                    break;
                default:
            }
            tankFrame.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_CONTROL:
                    fire();
                default:
            }
            tankFrame.repaint();
        }
    }
}
