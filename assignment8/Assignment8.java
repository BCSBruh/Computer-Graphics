package assignment8;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.border.Border;
import java.applet.Applet;
import java.awt.*;

public class Assignment8 extends Applet {
    public static void main(String[] s) {
        new MainFrame(new Assignment8(), 640, 480);
    }

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
    }

    private Geometry createGeometry() {
        //TODO
    }
}
