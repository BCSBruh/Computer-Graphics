package tests;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.applet.MainFrame;

public class Hello3D extends Applet {
  public static void main(String s[]) {
    System.setProperty("sun.awt.noerasebackground", "true");
    new MainFrame(new Hello3D(), 640, 480);
  }
  
  public void init() {
    GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
    Canvas3D cv = new Canvas3D(gc);
    setLayout(new BorderLayout());
    add(cv, BorderLayout.CENTER);
    BranchGroup bg = createSceneGraph();
    BranchGroup back = createBackground();
    bg.compile();
    SimpleUniverse su = new SimpleUniverse(cv);
    su.getViewingPlatform().setNominalViewingTransform();
    su.addBranchGraph(bg);
    su.addBranchGraph(back);
  }

  private BranchGroup createSceneGraph() {
    BranchGroup root = new BranchGroup();
    //object
    Appearance ap = new Appearance();
    ap.setMaterial(new Material());
    Font3D font = new Font3D(new Font("SansSerif", Font.PLAIN, 1), new FontExtrusion());

    Text3D firstName = new Text3D(font, "Jerome");
    Text3D lastName = new Text3D(font, "Larson");

    Shape3D firstShape = new Shape3D(firstName, ap);
    Shape3D lastShape = new Shape3D(lastName, ap);
    //transformation
    Transform3D tr = new Transform3D();
    tr.setScale(0.3);
    tr.setTranslation(new Vector3f(-0.5f, 0.25f, 0f));
    TransformGroup tg1 = new TransformGroup(tr);

    tr.setScale(0.5);
    tr.setTranslation(new Vector3f(-0.75f, -0.25f, 0f));
    TransformGroup tg2 = new TransformGroup(tr);
    root.addChild(tg1);
    root.addChild(tg2);
    tg1.addChild(firstShape);
    tg2.addChild(lastShape);
    //light
    PointLight light = new PointLight(new Color3f(Color.white),
                                      new Point3f(1f,1f,1f),
                                      new Point3f(1f,0.1f,0f));
    BoundingSphere bounds = new BoundingSphere();
    light.setInfluencingBounds(bounds);
    root.addChild(light);
    return root;
  }

  private BranchGroup createBackground() {
    BranchGroup backgroundGroup = new BranchGroup();

    Background b = new Background(new Color3f(Color.BLUE));
    b.setApplicationBounds(new BoundingSphere());
    backgroundGroup.addChild(b);

    return backgroundGroup;
  }
}