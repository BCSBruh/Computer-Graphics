package assignment8;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.border.Border;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import java.applet.Applet;
import java.awt.*;

public class Assignment8 extends Applet {
    public static void main(String[] s) {
        new MainFrame(new Assignment8(), 640, 480);
    }

    //Create viewing window parameters
    public void init() {
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();

        Canvas3D cv = new Canvas3D(gc);

        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);

        BranchGroup bg = createSceneGraph();
        bg.compile();

        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();

        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);

        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0));

        Shape3D shape = new Shape3D(createGeometry(), ap);

        Transform3D tr = new Transform3D();
        tr.setScale(0.2);

        TransformGroup tg = new TransformGroup(tr);
        spin.addChild(tg);
        tg.addChild(shape);

        Alpha alpha = new Alpha(-1, 12000);
        RotationInterpolator rotator = new RotationInterpolator(alpha, spin);

        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        spin.addChild(rotator);

        Background background = new Background(1.0f, 1.0f, 1.0f);
        background.setApplicationBounds(bounds);
        root.addChild(background);

        AmbientLight light = new AmbientLight(true, new Color3f(Color.yellow));
        light.setInfluencingBounds(bounds);
        root.addChild(light);

        PointLight ptLight = new PointLight(new Color3f(Color.white), new Point3f(3f, 3f, 3f), new Point3f(1f, 0.2f, 0f));
        ptLight.setInfluencingBounds(bounds);
        root.addChild(ptLight);

        return root;
    }

    private Geometry createGeometry() {
        //u_i = a + i(b-a)/m,   i = 0, 1, 2, 3, ..., m
        //v_j = c + j(d-c)/n,   j = 0, 1, 2, 3, ..., n
        float u;
        float v;

        //Set starting and ending values
        float a = 0;
        float b = (float)(4 * Math.PI);     //Use 4PI because you need to go around the shape twice to get full strip
        float c = -0.3f;
        float d = 0.3f;

        //Set how many triangles in order to smooth out the edges
        int m = 200;
        int n = 200;

        Point3f[] points = new Point3f[m*n];
        int index = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //Use m-1 and n-1 to connect both ends of the strip
                u = a + (i*(b - a) / (m - 1));
                v = c + (j*(d - c) / (n - 1));

                //Equation from review Question 6.20
                float x = (1 + v * (float)(Math.cos(u/2))) * (float)(Math.cos(u));
                float y = (1 + v * (float)(Math.cos(u/2))) * (float)(Math.sin(u));
                float z = v * (float)(Math.sin(u/2));

                points[index++] = new Point3f(x, y, z);
            }
        }

        int[] coords = new int[2*n*(m-1)];
        index = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                coords[index++] = i*n + j;
                coords[index++] = (i-1)*n + j;
            }
        }

        int[] stripCounts = new int[m-1];
        for (int i = 0; i < m-1; i++)
            stripCounts[i] = 2*n;

        GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_STRIP_ARRAY);
        gi.setCoordinates(points);
        gi.setCoordinateIndices(coords);
        gi.setStripCounts(stripCounts);

        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);

        return gi.getGeometryArray();
    }
}
