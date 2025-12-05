package org.example;

import java.awt.*;

// Helper package for doing math with dimension entries
public class DimMath {
    static Dimension add(Dimension d1, Dimension d2) {
        return new Dimension(d1.width + d2.width, d1.height + d2.height);
    }

    static Dimension times(int n, Dimension d) {
        return new Dimension(n * d.width, n * d.height);
    }
}
