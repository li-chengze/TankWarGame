import org.w3c.dom.css.Rect;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    public static int BULLET_WIDTH = 10;
    public static int BULLET_HEIGHT = 10;

    private int SPEED = 10;

    private Dir dir;

    private TankFrame tankFrame;

    public Rectangle bulletTangle;

    private Group group;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        bulletTangle = new Rectangle(x, y, Bullet.BULLET_WIDTH, Bullet.BULLET_HEIGHT);
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.red);
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
        g.fillOval(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        g.setColor(color);

        this.bulletTangle.x = x;
        this.bulletTangle.y = y;

        if(x < 0 || x > TankFrame.FRAME_WIDTH || y < 0 || y > TankFrame.FRAME_HEIGHT) {
            tankFrame.bullets.remove(this);
        }

    }

    public void die() {
        tankFrame.bullets.remove(this);
    }

    public Group getGroup() {
        return group;
    }
}
