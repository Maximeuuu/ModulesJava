package component;

import java.awt.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * UI personnalisée pour DynamicTabbedPane.
 * Supprime les marges internes et force une hauteur uniforme.
 */
public class DynamicTabbedPaneUI extends BasicTabbedPaneUI {

    @Override
    protected Insets getTabInsets(int tabPlacement, int tabIndex) {
        // Supprime les marges internes (autour du texte/label)
        return new Insets(0, 0, 0, 0);
    }

    @Override
    protected Insets getTabAreaInsets(int tabPlacement) {
        // Supprime les marges autour de la zone des onglets
        return new Insets(0, 0, 0, 0);
    }

    @Override
    protected Insets getContentBorderInsets(int tabPlacement) {
        // Enlève aussi les bordures autour du contenu
        return new Insets(0, 0, 0, 0);
    }

    /*@Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        // Force une hauteur d’onglet uniforme
        return TAB_HEIGHT;
    }*/

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement,
                                       Rectangle[] rects, int tabIndex,
                                       Rectangle iconRect, Rectangle textRect,
                                       boolean isSelected) {
        // Supprime l’indicateur de focus
    }
}
