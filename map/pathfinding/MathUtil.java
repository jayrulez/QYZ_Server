package pathfinding;

public final class MathUtil {
	public final static double Epselon = 1.0e-7; 
	
	/**
	 * return : 向量a 是否在向量b的左侧
	 */
	public static boolean leftOf(Vector3 a, Vector3 b) {
		return a.detxz(b) > Epselon;
	}
	
	/**
	 * return : 向量a 是否在向量b的右侧
	 */
	public static boolean rightOf(Vector3 a, Vector3 b) {
		return a.detxz(b) < -Epselon;
	}
	
	/**
	 * return : 向量a 是否在向量b上
	 */
	public static boolean lineOf(Vector3 a, Vector3 b) {
		final double d = b.detxz(a);
		return d > -Epselon && d < Epselon;
	}
	
	/**
	 * return : 点p 是否在 向量  (b - a) 的左侧(不包括在向量上)
	 */
	public static boolean leftOf(Vector3 a, Vector3 b, Vector3 p) {
		return detxz(a, p, b) > Epselon;
	}
	
	/**
	 * return : 点p 是否在 向量  (b - a) 的右侧(不包括在向量上)
	 */
	public static boolean rightOf(Vector3 a, Vector3 b, Vector3 p) {
		return detxz(a, p, b) < -Epselon;
	}
	
	/**
	 * return : a b c 是否共线 上
	 */
	public static boolean lineOf(Vector3 a, Vector3 b, Vector3 c) {
		final double d = detxz(a, b, c);
		return d > -Epselon && d < Epselon;
	}
	
	/**
	 * @return ab cd四点是否平行
	 */
	public static boolean lineOf(Vector3 a, Vector3 b, Vector3 c, Vector3 d) {
		final double e = (b.x - a.x) * (d.z - c.z) - (b.z - a.z) * (d.x - c.x);
		return e > -Epselon && e < Epselon;
	}
	
	/**
	 * @return 向量ab与ac的叉积
	 */
	public static double detxz(Vector3 a, Vector3 b, Vector3 c) {
		return (c.x - a.x) * (b.z - a.z) - (b.x - a.x) * (c.z - a.z);
	}
	
	/**
	 * 
	 * @param la
	 * @param lb
	 * @param sa
	 * @param sb
	 * @return 直线a,b是否与线段sa,sb相交
	 */
	public static boolean lineCrossSegment(Vector3 la, Vector3 lb, Vector3 sa, Vector3 sb) {
		final double d1 = detxz(la, lb, sa);
		final double d2 = detxz(la, lb, sb);
		return (d1 > -Epselon && d2 < Epselon) || (d1 < Epselon && d2 > -Epselon);
	}
	
//	/**
//	 * 
//	 * @param v1
//	 * @param v2
//	 * @param s1
//	 * @param s2
//	 * @return 返回 直线 v1v2 与 s1s2的交点,如果两直线平行,返回null
//	 */
//	public static Vector3 crossPostion(Vector3 v1, Vector3 v2, Vector3 s1, Vector3 s2) {
//		final double a1 = v1.z - v2.z;
//		final double b1 = v2.x - v1.x;
//		final double c1 = v1.x * v2.z - v1.z * v2.x;
//		
//		final double a2 = s1.z - s2.z;
//		final double b2 = s2.x - s1.x;
//		final double c2 = s1.x * s2.z - s1.z * s2.x;
//		
//		final double d = a1 * b2 - a2 * b1;
//		if(d < - Epselon || d > Epselon) {
//			final double x = (b1 * c2 - b2 * c1) / d;
//			final double y = (c1 * a2 - c2 * a1) / d;
//			return new Vector3(x, y);
//		} else {
//			return null;
//		}
//	}
	
	/**
	 * 判定两线段是否交叉
	 */
	public static boolean intersect(Vector3 a1, Vector3 a2, Vector3 b1, Vector3 b2) {
		/*
		 *  如果线段交叉,则要 b1,b2在 向量 a1a2的两边
		 *  同时a1a2在向量b1b2的两边
		 */
		return Math.min(a1.x, a2.x) < Math.max(b1.x, b2.x) + Epselon
				&& Math.min(b1.x, b2.x) < Math.max(a1.x, a2.x) + Epselon
				&& Math.min(a1.z, a2.z) < Math.max(b1.z, b2.z) + Epselon
				&& Math.min(b1.z, b2.z) < Math.max(a1.z, a2.z) + Epselon
			&& lineCrossSegment(a1, a2, b1, b2) && lineCrossSegment(b1, b2, a1, a2);
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param p
	 * @return 点p到a b所在直线的距离的平方
	 */
	public static double vertex2lineSquareDistance(Vector3 a, Vector3 b, Vector3 p) {
		/*
		 *  (x - ax)(ay - by) - (y - ay)(ax - bx) = 0; 
		 *  (x - ax)(az - bz) - (z - az)(ax - bx) = 0;
		 */
		final double dx = p.x - a.x;
		final double dy = p.y - a.y;
		final double dz = p.z - a.z;
		
		final double h = b.x - a.x;
		final double i = b.y - a.y;
		final double j = b.z - a.z;
		
		final double dk = h * dx + i * dy + j * dz;
		return dx * dx + dy * dy + dz * dz - dk * dk / (h * h + i * i + j * j);
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param p
	 * @return 点p到a b所在直线的xz距离的平方
	 */
	public static double vertex2lineXZSquareDistance(Vector3 a, Vector3 b, Vector3 p) {
		/*
		 *  (x - ax)(by - ay) - (y - ay)(bx - ax) = 0; 
		 */
		//final double r = (p.x - a.x) * (p.z - b.z) - (p.z - a.z) * (p.x - b.x);
		//return (r * r) / ((b.z - a.z) * (b.z - a.z) + (b.x - a.x) * (b.x - a.x));
		final Vector3 pa = p.sub(a);
		final Vector3 pb = p.sub(b);
		final Vector3 ab = a.sub(b);
		final double r = pa.detxz(pb);
		return r * r / ab.getXZSquare();
	}

    /**
     *
     * @param a
     * @param b
     * @return 矢量a的末端到矢量b的距离
     */
	public static double vector2VectorXZSquareDistance(Vector3 a, Vector3 b) {
	    double k = (a.x * b.x + a.z * b.z);
	    return (a.x * a.x + a.z * a.z)  - k * k / Math.max(Epselon, b.x * b.x + b.z * b.z);
    }
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return 返回 a,b,c构成的三角形中,ab夹角
	 */
	public static double radianOfTriangle(double a, double b, double c) {
		double cos = (a*a + b*b - c*c)/(2*a*b);
		if(cos >= 1) 
			return 0;
		else if(cos <= -1)
			return Math.PI;
		else
			return Math.acos(cos);
	}
	
	public static void main(String[] args) {
//		final Vector3 r = crossPostion(new Vector3(0, 0), new Vector3(1, 1), new Vector3(0, 1), new Vector3(2, 0));
//		System.out.println(r);
//		
//		System.out.println(crossPostion(new Vector3(0, 0), new Vector3(1, 1), new Vector3(1, 1), new Vector3(3, 3)));
	}
}
