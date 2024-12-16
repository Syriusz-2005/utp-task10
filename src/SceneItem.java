public interface SceneItem {
    void step(int length, ParticleScene scene);
    int getLifetime();
}
