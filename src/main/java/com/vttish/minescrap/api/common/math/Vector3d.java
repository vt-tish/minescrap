package com.vttish.minescrap.api.common.math;

public record Vector3d(double x, double y, double z) {
    private static final double EPSILON = 1e-9;
    public static final Vector3d ZERO = new Vector3d(0, 0, 0);

    public Vector3d add(double x, double y, double z) {
        return new Vector3d(this.x + x, this.y + y, this.z + z);
    }

    public Vector3d add(Vector3d vec) {
        return add(vec.x, vec.y, vec.z);
    }

    public Vector3d substruct(Vector3d vec) {
        return new Vector3d(x - vec.x, y - vec.y, z - vec.z);
    }

    public Vector3d scale(double factor) {
        return new Vector3d(x * factor, y * factor, z * factor);
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double distanceToSquared(Vector3d vec) {
        double dx = x - vec.x;
        double dy = y - vec.y;
        double dz = z - vec.z;

        return dx * dx + dy * dy + dz * dz;
    }

    public double distanceTo(Vector3d vec) {
        return Math.sqrt(distanceToSquared(vec));
    }

    public double horizontalDistanceToSquared(Vector3d vec) {
        double dx = x - vec.x;
        double dz = z - vec.z;

        return dx * dx + dz * dz;
    }

    public double horizontalDistanceTo(Vector3d vec) {
        return Math.sqrt(horizontalDistanceToSquared(vec));
    }

    public double dot(Vector3d vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    public Vector3d cross(Vector3d vec) {
        return new Vector3d(
                y * vec.z - z * vec.y,
                z * vec.x - x * vec.z,
                x * vec.y - y * vec.x
        );
    }

    public Vector3d normalized() {
        double len = length();

        return len < EPSILON ? ZERO : scale(1 / len);
    }

    public Vector3d lerp(Vector3d target, double delta) {
        return new Vector3d(
                x + (target.x - x) * delta,
                y + (target.y - y) * delta,
                z + (target.z - z) * delta
        );
    }
}
