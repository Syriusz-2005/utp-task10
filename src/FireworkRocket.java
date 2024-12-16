import java.awt.*;

public class FireworkRocket implements SceneItem {
    private int flightTime;
    private final int initialX;
    private Vec2f pos;
    private Vec2f vel;
    private ParticleScene scene;
    private Color color = Color.RED;

    public FireworkRocket(ParticleScene scene, int flightTime, int posX, Color rocketColor) {
        this.flightTime = flightTime;
        this.scene = scene;
        this.initialX = posX;
        pos = new Vec2f(initialX, 800);
        vel = new Vec2f(0, -1.3f);

        var flightParticle = new Particle();
        flightParticle.lifetime = flightTime;
        flightParticle.vel = vel.clone();
        flightParticle.color = rocketColor;
        flightParticle.pos = pos;
        scene.addParticle(flightParticle);
    }

    @Override
    public void step(int length, ParticleScene scene) {
        pos.add(vel);
        flightTime -= 1;
        if (flightTime < 0) {
            for (int i = 0; i < 100; i++) {
                var newParticle = new Particle();
                newParticle.pos = pos.clone();
                newParticle.size = 7;
                newParticle.lifetime = Utils.randInt(90, 250);
                float[] hsb = new float[4];
                Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
                hsb[0] = Utils.clamp(0, hsb[0] + Utils.randFloat(0, 0.07f), 1);
                newParticle.color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
                newParticle.vel = Vec2f
                        .random(-1, 1)
                        .normalize()
                        .multiply(Utils.randFloat(0.3f, 3));
                scene.addParticle(newParticle);
            }
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getLifetime() {
        return flightTime;
    }
}
