package assignment7;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.vecmath.Point3f;

/**
 *
 * @author Jerome Larson
 */
public class Octahedron extends IndexedTriangleArray {

    public Octahedron() {
        super(6, GeometryArray.COORDINATES, 24); // 6 vertices, 3*8 indices

        //Set the points defined in the assignment
        setCoordinate(0, new Point3f(0f, 0f, 1f));
        setCoordinate(1, new Point3f(-1f, 0f, 0f));
        setCoordinate(2, new Point3f(0f, -1f, 0f));
        setCoordinate(3, new Point3f(1f, 0f, 0f));
        setCoordinate(4, new Point3f(0f, 1f, 0f));
        setCoordinate(5, new Point3f(0f, 0f, -1f));

        //Here are the coordinates for each point in the 8 triangles
        int[] coordinates = {0,1,2, 1,2,5, 5,2,3, 3,2,0, 0,4,1, 1,4,5, 5,4,3, 3,4,0};

        setCoordinateIndices(0, coordinates);
    }
}
