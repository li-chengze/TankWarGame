import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private int WIDTH = 10;
    private int HEIGHT = 10;

    private int SPEED = 1;

    private Dir dir;

    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
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
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);

        if(x < 0 || x > TankFrame.FRAME_WIDTH || y < 0 || y > TankFrame.FRAME_HEIGHT) {
            tankFrame.bullets.remove(this);
        }
        tankFrame.repaint();
    }
}
