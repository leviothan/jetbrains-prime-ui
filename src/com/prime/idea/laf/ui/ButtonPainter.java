package com.prime.idea.laf.ui;

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.prime.idea.PrimeTheme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import java.awt.*;

public class ButtonPainter implements Border, UIResource {
    private static final int myOffset = 4;

    public ButtonPainter() {
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D)g;
        Insets ins = this.getBorderInsets(c);
        int yOff = (ins.top + ins.bottom) / 4;
        boolean square = DarculaButtonUI.isSquare(c);
        int offset = JBUI.scale(square?1:this.getOffset());
        int w = c.getWidth();
        int h = c.getHeight();
        int diam = JBUI.scale(22);
        if (c.hasFocus()) {
            c.setForeground(Color.WHITE);
        }
//        if(c.hasFocus()) {
//            if(DarculaButtonUI.isHelpButton((JComponent)c)) {
//                DarculaUIUtil.paintFocusOval(g2d, (w - diam) / 2, (h - diam) / 2, diam, diam);
//            } else {
//                DarculaUIUtil.paintFocusRing(g2d, offset, yOff, width - 2 * offset, height - 2 * yOff);
//            }
//        } else {
            GraphicsConfig config = new GraphicsConfig(g);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
//            g2d.setPaint(UIUtil.getGradientPaint((float)(width / 2), (float)(y + yOff + JBUI.scale(1)), PrimeTheme.level1Color, (float)(width / 2), (float)(height - 2 * yOff), PrimeTheme.level1Color));
//            g2d.setPaint(UIUtil.getGradientPaint((float)(width / 2), (float)(y + yOff + JBUI.scale(1)), Gray._80.withAlpha(90), (float)(width / 2), (float)(height - 2 * yOff), Gray._90.withAlpha(90)));
//            ((Graphics2D)g).setPaint(Gray._100.withAlpha(180));
            ((Graphics2D)g).setPaint(PrimeTheme.buttonBorderColor);
            if(DarculaButtonUI.isHelpButton((JComponent)c)) {
                g.drawOval((w - diam) / 2, (h - diam) / 2, diam, diam);
            } else {
                g.translate(x, y);
                int r = JBUI.scale(square?3:5);
                ((Graphics2D) g).setStroke(new BasicStroke(1));
                g.drawRoundRect(offset, yOff, width - 2 * offset, height - 2 * yOff, r, r);
                g.translate(-x, -y);
            }

            config.restore();
//        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return (Insets)(c.getParent() instanceof ActionToolbar ?JBUI.insets(4, 16, 4, 16):(DarculaButtonUI.isSquare(c)?JBUI.insets(2, 0, 2, 0).asUIResource():JBUI.insets(12, 24, 12, 24).asUIResource()));
    }

    protected int getOffset() {
        return 4;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
