package pathfinding;

public final class Vector3 {
	public static final Vector3 ZERO = new Vector3(0, 0, 0);
	public static final Vector3 ONE  = new Vector3(1, 1, 1);
	public static final Vector3 RIGHT = new Vector3(1, 0, 0);
    public static final Vector3 FORWARD = new Vector3(0, 0, 1);
    public static final Vector3 UP = new Vector3(0, 1, 0);
	
	public final double x;
	public final double y;
	public final double z;
	
	public Vector3(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
	}
	
	public Vector3 plus(Vector3 other) {
		return new Vector3(x + other.x, y + other.y , z + other.z);
	}

	public Vector3 sub(Vector3 other) {
		return new Vector3(x - other.x, y - other.y , z - other.z);
	}
	
	public Vector3 multi(double r) {
		return new Vector3(x * r, y * r, z * r);
	}
	
	public Vector3 scale(double s) {
		return multi(s / Math.max(1e-7, getMagnitude()));
	}
	
	public Vector3 scaleXZ(double s) {
		return multi(s / Math.max(1e-7, getXZMagnitude()));
	}
	
	public double getSquare() {
		return x * x + y * y + z * z;
	}
	
	public double getSubSquare(Vector3 o) {
		final double dx = x - o.x;
		final double dy = y - o.y;
		final double dz = z - o.z;
		return dx * dx + dy * dy + dz * dz;
	}
	
	public double getXZSquare() {
		return x * x + z * z;
	}
	
	public double getSubXZSquare(Vector3 o) {
		final double dx = x - o.x;
		final double dz = z - o.z;
		return dx * dx + dz * dz;
	}
	
	public double getMagnitude() {
		return Math.sqrt(getSquare());
	}
	
	public double getSubMagnitude(Vector3 o) {
		return Math.sqrt(getSubSquare(o));
	}
	
	public double getXZMagnitude() {
		return Math.sqrt(x * x + z * z);
	}
	
	public double getSubXZMagnitude(Vector3 o) {
		return Math.sqrt(getSubXZSquare(o));
	}

    public boolean isSubXZMagnitudeInRadius(Vector3 o, double radius) {
        return getSubXZSquare(o) <= radius * radius;
    }
	
	public double dot(Vector3 other) {
		return x * other.x + y * other.y + z * other.z;
	}
	
	public double dotXZ(Vector3 other) {
		return x * other.x + z * other.z;
	}
	
	public Vector3 det(Vector3 other) {
		return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
	}
	
	public double detxz(Vector3 other) {
		return z * other.x - x * other.z;
	}
	
	public Vector3 lerp(Vector3 other, double otherWeight) {
		double selfWeight = 1 - otherWeight;
		return new Vector3(selfWeight * x + otherWeight *other.x, selfWeight * y + otherWeight * other.y, selfWeight * z + otherWeight * other.z);
	}
	
	public Vector3 rotateZX(Vector3 other) {
		final double xzmag = other.getXZMagnitude();
		return new Vector3((x * -other.z + z * other.x) / xzmag , y, (z * -other.z - x * other.x) / xzmag);
	}

    public Vector3 rotateXZ(Vector3 other) {
        final double xzmag = other.getXZMagnitude();
        return new Vector3((x * other.z - z * other.x) / xzmag , y, (z * other.z + x * other.x) / xzmag);
    }

    public Vector3 rotateForword(Vector3 forward) {
        final double xzmag = forward.getXZMagnitude();
        return new Vector3((x * forward.z + z * forward.x) / xzmag , y, (z * forward.z - x * forward.x) / xzmag);
    }
	
	public Vector3 rotateXZRadian(double radian) {
		final double sin = Math.sin(radian);
		final double cos = Math.cos(radian);
		return new Vector3(x * cos - z * sin, y, z * cos + x * sin);
	}

    public Vector3 rotateXZAngle(double angle) {
        return rotateXZRadian(Math.toRadians(angle));
    }
	
	public double radianBetween(Vector3 other) {
		double cosX = dot(other) / (this.getMagnitude() * other.getMagnitude());
		if(cosX >= 1.0)
			return 0;
		else if(cosX <= -1.0)
			return -Math.PI;
		else
			return Math.acos(cosX);
	}
	
	public double radianXZBetween(Vector3 other) {
		double cosX = dotXZ(other) / (this.getXZMagnitude() * other.getXZMagnitude());
		if(cosX >= 1.0)
			return 0;
		else if(cosX <= -1.0)
			return -Math.PI;
		else
			return Math.acos(cosX);
	}
	
	public double radianXZ() {
		if(x == 0)
			return z > 0 ? Math.PI/2 : Math.PI * 3/2;
		else {
			double r = Math.atan(z/x);
			if(x > 0) 
				return z >= 0 ? r : r + Math.PI * 2;
			else
				return r + Math.PI;
		}
	}
	
	@Override
	public int hashCode() {
		return ((Double)x).hashCode() * 16777619 + ((Double)z).hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Vector3) {
			Vector3 e = (Vector3)o;
			return x==e.x && z==e.z;
			//return x == e.x && y == e.y && z == e.z;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.format("[%f,%f,%f]", x, y, z);
	}
}
