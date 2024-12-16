public class Vec2f {
    public static Vec2f ZERO = new Vec2f(0, 0);
    public static Vec2f random(float min, float max) {
        return new Vec2f(Utils.randFloat(min, max), Utils.randFloat(min, max));
    }

    public float x;
    public float y;
    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f(Vec2f vec) {
        this(vec.x, vec.y);
    }

    public Vec2f clone() {
        return new Vec2f(x, y);
    }

    public Vec2i toInt() {
        return new Vec2i((int) x, (int) y);
    }

    public Vec2f copy(Vec2f v) {
        x = v.x;
        y = v.y;
        return this;
    }

    public Vec2f add(float s) {
        x += s;
        y += s;
        return this;
    }

    public Vec2f add(Vec2f v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vec2f subtract(float s) {
        x -= s;
        y -= s;
        return this;
    }

    public Vec2f subtract(Vec2f v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vec2f multiply(float s) {
        x *= s;
        y *= s;
        return this;
    }

    public Vec2f multiply(Vec2f v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    public Vec2f divide(float s) {
        x /= s;
        y /= s;
        return this;
    }

    public Vec2f divide(Vec2f v) {
        x /= v.x;
        y /= v.y;
        return this;
    }

    public float angle() {
        return (float) Math.atan2(y, x);
    }

    public float length() {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public float distance(Vec2f v) {
        return v.clone().subtract(this).length();
    }

    public Vec2f normalize() {
        divide(length());
        return this;
    }

    @Override
    public String toString() {
        return "Vec2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}