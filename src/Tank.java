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
    private Group group = Group.BAD;

    private Rectangle tankRect = null;

    public Tank(int x, int y, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        this.group = group;
        if (group == Group.GOOD) {
            this.tankFrame.addKeyListener(new MyKeyAdapter());
        }

        this.tankRect = new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
    }

    public void paint(Graphics g) {
        Color originColor = g.getColor();
        Color tankColor = group == Group.GOOD ? Color.GREEN : Color.BLUE;
        g.setColor(tankColor);
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        g.setColor(originColor);
    }

    private void fire() {
        int bX = x + (TANK_WIDTH / 2);
        int bY = y + (TANK_HEIGHT / 2);
        tankFrame.bullets.add(new Bullet(bX, bY, dir, this.tankFrame));
    }

    public boolean collide(Bullet bullet) {
        Rectangle bulletRect = bullet.bulletTangle;
        return this.tankRect.intersects(bulletRect);
    }

    public void die() {
        tankFrame.enemies.remove(this);
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
            tankRect = new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
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
