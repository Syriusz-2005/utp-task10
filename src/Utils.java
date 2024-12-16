public class Utils {
    public static int randInt(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
    public static float randFloat(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    public static float clamp(float min, float x, float max) {
        return (float) (Math.max(min, Math.min(max, x)));
    }
}
