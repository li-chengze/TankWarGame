public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        // initalize enemies
        for(int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(i * 100, 400, tf, Group.BAD));
        }
        tf.draw();
    }
}
