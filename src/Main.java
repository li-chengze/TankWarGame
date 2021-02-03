public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();
        int initialEnemyCouns = Integer.parseInt((String)PropertyManager.get("initialEnemyCount"));
        // initalize enemies
        for(int i = 0; i < initialEnemyCouns; i++) {
            tf.enemies.add(new Tank(i * 100, 400, tf, Group.BAD));
        }
        tf.draw();

        while(true) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tf.repaint();
        }
    }
}
