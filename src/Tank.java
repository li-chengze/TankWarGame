import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {
    private int x;
    private int y;
    private int SPEED = 5;
    private TankFrame tankFrame;

    public static int TANK_WIDTH = 50;
    public static int TANK_HEIGHT = 50;

    private Dir dir = Dir.DOWN;
    private Group group = Group.BAD;

    private Rectangle tankRect;

    private Random random = new Random();

    public Tank(int x, int y, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        this.group = group;
        if (group == Group.GOOD) {
            this.tankFrame.addKeyListener(new MyKeyAdapter());
        }
        this.SPEED = group == Group.GOOD? 10 : 3;
        this.tankRect = new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
    }

    public void paint(Graphics g) {
        Color originColor = g.getColor();
        Color tankColor = group == Group.GOOD ? Color.GREEN : Color.BLUE;
        g.setColor(tankColor);
        if(group == Group.BAD) {
            enemyMove();
            enemyFire();
        }
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        this.tankRect.x = x;
        this.tankRect.y = y;
        g.setColor(originColor);

        boundsLimit();
    }

    private void fire() {
        int bX = x + (TANK_WIDTH / 2) - (Bullet.BULLET_WIDTH / 2);
        int bY = y + (TANK_HEIGHT / 2) - (Bullet.BULLET_WIDTH / 2);
        tankFrame.bullets.add(new Bullet(bX, bY, dir, this.tankFrame, this.group));
    }

    public boolean collide(Bullet bullet) {
        Rectangle bulletRect = bullet.bulletTangle;
        return this.tankRect.intersects(bulletRect);
    }

    public void die() {
        tankFrame.enemies.remove(this);
    }

    private void enemyMove() {
        Dir[] dirValues = Dir.values();
        if (random.nextInt(10) > 8) {
            this.dir = dirValues[random.nextInt(dirValues.length)];
        }
        move(this.dir);
    }

    private void enemyFire() {
        if(random.nextInt(100) > 95) {
            fire();
        }
    }

    private void move(Dir dir) {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
        }
    }

    public void boundsLimit() {
        if (x <= 0) {
            dir = Dir.RIGHT;
        }
        if (x + TANK_WIDTH >= TankFrame.FRAME_WIDTH) {
            dir = Dir.LEFT;
        }
        if (y <= 0) {
            dir = Dir.DOWN;
        }
        if (y + TANK_HEIGHT >= TankFrame.FRAME_HEIGHT) {
            dir = Dir.UP;
        }
    }

    public Group getGroup() {
        return group;
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    dir = Dir.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    dir = Dir.RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    dir = Dir.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    dir = Dir.DOWN;
                    break;
                default:
                    return;
            }
            move(dir);
            tankRect = new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_CONTROL:
                    fire();
                default:
            }
        }
    }
}
