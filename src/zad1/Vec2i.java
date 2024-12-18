package zad1;

import java.awt.*;

public class Vec2i {
    public static Vec2i ZERO = new Vec2i(0, 0);
    public int x;
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i(Point p) {
        this(p.x, p.y);
    }

    public Vec2i(Dimension d) {
        this(d.width, d.height);
    }

    public Vec2i(Image i) {
        this(i.getWidth(null), i.getHeight(null));
    }

    public Vec2i(int s) {
        this(s, s);
    }

    public Vec2i(Vec2i vec) {
        this(vec.x, vec.y);
    }

    public Vec2i clone() {
        return new Vec2i(x, y);
    }

    public boolean equals(Vec2i v) {
        return v.x == x && v.y == y;
    }

    public Vec2i copy(Vec2i v) {
        x = v.x;
        y = v.y;
        return this;
    }

    public Vec2i add(int s) {
        x += s;
        y += s;
        return this;
    }

    public Vec2i add(Vec2i v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vec2i add(Point p) {
        x += p.x;
        y += p.y;
        return this;
    }

    public Vec2i subtract(int s) {
        x -= s;
        y -= s;
        return this;
    }

    public Vec2i subtract(Vec2i v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vec2i multiply(int s) {
        x *= s;
        y *= s;
        return this;
    }

    public Vec2i multiply(Vec2i v) {
        x *= v.x;
        y *= v.y;
        return this;
    }

    public Vec2i divide(int s) {
        x /= s;
        y /= s;
        return this;
    }

    public Vec2i divide(Vec2i v) {
        x /= v.x;
        y /= v.y;
        return this;
    }

    public int length() {
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int distance(Vec2i v) {
        return v.clone().subtract(this).length();
    }

    public Vec2i normalize() {
        divide(length());
        return this;
    }

    @Override
    public String toString() {
        return "Vec2i{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}