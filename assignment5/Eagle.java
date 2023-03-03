package assignment5;

import java.awt.geom.Path2D;

/**
 *
 * @author Hong Zhang
 */
public class Eagle extends Path2D.Double {
    
    public Eagle() {
        this.moveTo(173, 308);
        this.curveTo(400, 300, 620, 350, 544, 382);
        this.lineTo(405, 382);
        this.curveTo(425, 380, 490, 420, 509, 399);
        this.quadTo(499, 400, 491, 394);
        this.curveTo(650, 380, 750, 420, 710, 637);
        this.curveTo(730, 540, 405, 520, 318, 752);
        this.quadTo(270, 670, 280, 632);
        this.quadTo(250, 645, 224, 679);
        this.quadTo(215, 600, 221, 576);
        this.quadTo(190, 579, 163, 581);
        this.quadTo(170, 541, 183, 515);
        this.quadTo(161, 520, 145, 527);
        this.quadTo(153, 500, 159, 480);
        this.quadTo(150, 470, 140, 461);
    }
    
}
