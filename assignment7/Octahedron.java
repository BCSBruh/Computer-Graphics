package assignment7;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.vecmath.Point3f;

/**
 *
 * @author 
 */
public class Octahedron extends IndexedTriangleArray {

    public Octahedron() {
        super(6, GeometryArray.COORDINATES, 24); // 6 vertices, 3*8 indices
        setCoordinate(0, new Point3f(0f, 0f, 1f));
        setCoordinate(1, new Point3f(-1f, 0f, 0f));
        setCoordinate(2, new Point3f(0f, -1f, 0f));
        setCoordinate(3, new Point3f(1f, 0f, 0f));
        setCoordinate(4, new Point3f(0f, 1f, 0f));
        setCoordinate(5, new Point3f(0f, 0f, -1f));

        int[] coordinates = {};
    }
}
