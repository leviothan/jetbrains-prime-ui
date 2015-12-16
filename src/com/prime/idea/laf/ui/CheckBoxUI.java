package com.prime.idea.laf.ui;

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaCheckBoxUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalCheckBoxUI;
import javax.swing.text.View;
import java.awt.*;

public class CheckBoxUI extends MetalCheckBoxUI {
    public CheckBoxUI() {
        System.out.println("MyCheckBoxUI");
    }

    public static ComponentUI createUI ( JComponent c ) {
        if(UIUtil.getParentOfType(CellRendererPane.class, c) != null) {
            c.setBorder((Border)null);
        }

        return new CheckBoxUI();
    }

    public synchronized void paint(Graphics g2d, JComponent c) {
        System.out.println("my Paint!!!! OMG!!!");
        Graphics2D g = (Graphics2D)g2d;
        JCheckBox b = (JCheckBox)c;
        Dimension size = c.getSize();
        Font font = c.getFont();
        g.setFont(font);
        FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, font);
        Rectangle viewRect = new Rectangle(size);
        Rectangle iconRect = new Rectangle();
        Rectangle textRect = new Rectangle();
        JBInsets.removeFrom(viewRect, c.getInsets());
        String text = SwingUtilities.layoutCompoundLabel(c, fm, b.getText(), this.getDefaultIcon(), b.getVerticalAlignment(), b.getHorizontalAlignment(), b.getVerticalTextPosition(), b.getHorizontalTextPosition(), viewRect, iconRect, textRect, b.getIconTextGap());
        if(c.isOpaque()) {
            g.setColor(b.getBackground());
            g.fillRect(0, 0, size.width, size.height);
        }

        boolean selected = b.isSelected();
        boolean enabled = b.isEnabled();
        this.drawCheckIcon(c, g, b, iconRect, selected, enabled);
        this.drawText(c, g, b, fm, textRect, text);
    }

    protected void drawCheckIcon(JComponent c, Graphics2D g, JCheckBox b, Rectangle iconRect, boolean selected, boolean enabled) {
        if(selected && b.getSelectedIcon() != null) {
            b.getSelectedIcon().paintIcon(b, g, iconRect.x + JBUI.scale(4), iconRect.y + JBUI.scale(2));
        } else if(!selected && b.getIcon() != null) {
            b.getIcon().paintIcon(b, g, iconRect.x + JBUI.scale(4), iconRect.y + JBUI.scale(2));
        } else {
            int off = JBUI.scale(3);
            int x = iconRect.x + off;
            int y = iconRect.y + off;
            int w = iconRect.width - 2 * off;
            int h = iconRect.height - 2 * off;
            g.translate(x, y);
            Paint paint = UIUtil.getGradientPaint((float)(w / 2), 0.0F, b.getBackground().brighter(), (float)(w / 2), (float)h, b.getBackground());
            g.setPaint(paint);
            int fillOffset = JBUI.scale(1);
            g.fillRect(fillOffset, fillOffset, w - 2 * fillOffset, h - 2 * fillOffset);
            GraphicsConfig config = new GraphicsConfig(g);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
            boolean armed = b.getModel().isArmed();
            int R = JBUI.scale(4);
            if(c.hasFocus()) {
                g.setPaint(UIUtil.getGradientPaint((float)(w / 2), 1.0F, this.getFocusedBackgroundColor1(armed, selected), (float)(w / 2), (float)h, this.getFocusedBackgroundColor2(armed, selected)));
                g.fillRoundRect(0, 0, w, h, R, R);
                DarculaUIUtil.paintFocusRing(g, 1, 1, w - 2, h - 2);
            } else {
                g.setPaint(UIUtil.getGradientPaint((float)(w / 2), 1.0F, this.getBackgroundColor1(enabled, selected), (float)(w / 2), (float)h, this.getBackgroundColor2(enabled, selected)));
                g.fillRoundRect(0, 0, w, h, R, R);
                Color borderColor1 = this.getBorderColor1(enabled, selected);
                Color borderColor2 = this.getBorderColor2(enabled, selected);
                g.setPaint(UIUtil.getGradientPaint((float)(w / 2), 1.0F, borderColor1, (float)(w / 2), (float)h, borderColor2));
                g.drawRoundRect(0, UIUtil.isUnderDarcula()?1:0, w, h - 1, R, R);
                g.setPaint(this.getInactiveFillColor());
                g.drawRoundRect(0, 0, w, h - 1, R, R);
            }

            if(b.getModel().isSelected()) {
                this.paintCheckSign(g, enabled, w, h);
            }

            g.translate(-x, -y);
            config.restore();
        }

    }

    protected void drawText(JComponent c, Graphics2D g, JCheckBox b, FontMetrics fm, Rectangle textRect, String text) {
        if(text != null) {
            View view = (View)c.getClientProperty("html");
            if(view != null) {
                view.paint(g, textRect);
            } else {
//                g.setColor(b.isEnabled()?b.getForeground():this.getDisabledTextColor());
                SwingUtilities2.drawStringUnderlineCharAt(c, g, text, b.getDisplayedMnemonicIndex(), textRect.x, textRect.y + fm.getAscent());
            }
        }

    }

    protected void paintCheckSign(Graphics2D g, boolean enabled, int w, int h) {
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setStroke(new BasicStroke(1.0F * JBUI.scale(2.0F), 1, 1));
        g.setPaint(this.getShadowColor(enabled, true));
        int x1 = JBUI.scale(4);
        int y1 = JBUI.scale(7);
        int x2 = JBUI.scale(7);
        int y2 = JBUI.scale(11);
        int y3 = JBUI.scale(2);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, w, y3);
        g.setPaint(this.getCheckSignColor(enabled, true));
        g.translate(0, -2);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, w, y3);
        g.translate(0, 2);
    }

    protected Color getInactiveFillColor() {
        return getColor("inactiveFillColor", Gray._40.withAlpha(180));
    }

    protected Color getBorderColor1(boolean enabled, boolean selected) {
        return enabled?getColor("borderColor1", Gray._120.withAlpha(90), selected):getColor("disabledBorderColor1", Gray._120.withAlpha(90), selected);
    }

    protected Color getBorderColor2(boolean enabled, boolean selected) {
        return enabled?getColor("borderColor2", Gray._105.withAlpha(90), selected):getColor("disabledBorderColor2", Gray._105.withAlpha(90), selected);
    }

    protected Color getBackgroundColor1(boolean enabled, boolean selected) {
        return getColor("backgroundColor1", Gray._110, selected);
    }

    protected Color getBackgroundColor2(boolean enabled, boolean selected) {
        return getColor("backgroundColor2", Gray._95, selected);
    }

    protected Color getCheckSignColor(boolean enabled, boolean selected) {
        return enabled?getColor("checkSignColor", Gray._170, selected):getColor("checkSignColorDisabled", Gray._120, selected);
    }

    protected Color getShadowColor(boolean enabled, boolean selected) {
        return enabled?getColor("shadowColor", Gray._30, selected):getColor("shadowColorDisabled", Gray._60, selected);
    }

    protected Color getFocusedBackgroundColor1(boolean armed, boolean selected) {
        return armed?getColor("focusedArmed.backgroundColor1", Gray._100, selected):getColor("focused.backgroundColor1", Gray._120, selected);
    }

    protected Color getFocusedBackgroundColor2(boolean armed, boolean selected) {
        return armed?getColor("focusedArmed.backgroundColor2", Gray._55, selected):getColor("focused.backgroundColor2", Gray._75, selected);
    }

    protected static Color getColor(String shortPropertyName, Color defaultValue) {
        Color color = UIManager.getColor("CheckBox.darcula." + shortPropertyName);
        return color == null?defaultValue:color;
    }

    protected static Color getColor(String shortPropertyName, Color defaultValue, boolean selected) {
        if(selected) {
            Color color = getColor(shortPropertyName + ".selected", (Color)null);
            if(color != null) {
                return color;
            }
        }

        return getColor(shortPropertyName, defaultValue);
    }

    public Icon getDefaultIcon() {
        return JBUI.emptyIcon(20).asUIResource();
    }
}
