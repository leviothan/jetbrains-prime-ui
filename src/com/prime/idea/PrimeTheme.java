package com.prime.idea;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.UIUtil;
import com.prime.idea.laf.PrimeLaf;
import com.prime.idea.laf.ui.ButtonPainter;
import com.prime.idea.laf.ui.CheckBoxBorder;
import com.prime.idea.laf.ui.CheckBoxUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalCheckBoxUI;
import java.awt.*;

public class PrimeTheme implements ApplicationComponent {

    // $color-darkest-grey:        #393939;
    // $color-darker-grey:         #515151;
    // $color-dark-grey:           #747369;
    // $color-grey:                #a09f93;
    // $color-light-grey:          #d3d0c8;
    // $color-lighter-grey:        #e8e6df;
    // $color-lightest-grey:       #f2f0ec;
    private final ColorUIResource black = new ColorUIResource(45, 45, 45);
    private final ColorUIResource darkestGrey = new ColorUIResource(57, 57, 57);

    private final ColorUIResource lightestGrey = new ColorUIResource(242, 240, 236);



//    $color-red:                 #f2777a;
//    $color-orange:              #f99157;
//    $color-yellow:              #fc6;
//    $color-green:               #9c9;
//    $color-cyan:                #6cc;
//    $color-blue:                #69c;
//    $color-magenta:             #c9c;
//    $color-brown:               #d27b53;
    private final ColorUIResource cyan = new ColorUIResource(102, 204, 204);
    private final ColorUIResource blue = new ColorUIResource(102, 153, 204);






    // Remove this all
    private final ColorUIResource darkestBrown = new ColorUIResource(43, 41, 39);
    private final ColorUIResource veryDarkBrown = new ColorUIResource(53, 49, 44);
    private final ColorUIResource darkBrown = new ColorUIResource(81, 75, 67);
    private final ColorUIResource brown = new ColorUIResource(123, 114, 101);
    private final ColorUIResource orange = new ColorUIResource(255, 180, 82);

    private final ColorUIResource textColor = new ColorUIResource(173, 169, 164);
//    private final ColorUIResource black = new ColorUIResource(0, 0, 0);

    public ColorUIResource baseBackgroundColor;
    public static Color baseBackgroundHighlight;
    public static Color syntaxBackgroundColor;

    public static Color baseForeground;

    public static Color accentColor;

    public static Color buttonBorderColor;
    public static Color buttonColor;
    public static Color buttonSelectionColor;
    public static Color buttonSelectionForeground;


    public static Color level1Color;
    public static Color level2Color;
    public static Color level3Color;


    public PrimeTheme() {

    }

    /**
     * Algorithm from atom-one-dark-ui
     * https://github.com/atom/one-dark-ui/blob/master/styles/ui-variables.less;
     */
    public void initColors() {
        Color editorSyntaxBackgroundColor = EditorColorsManager.getInstance().getScheme(
                EditorColorsManager.getInstance().getGlobalScheme().getName()
        ).getDefaultBackground();

        syntaxBackgroundColor = new Color(editorSyntaxBackgroundColor.getRGB());

        HSLColor syntaxBackgroundColor = new HSLColor(editorSyntaxBackgroundColor);
        HSLColor uiSyntaxColor = new HSLColor(syntaxBackgroundColor.getHSL());

        Float uiSyntaxSaturation = uiSyntaxColor.getSaturation();
        Float uiSyntaxHue = uiSyntaxColor.getHue();
        Float uiSyntaxLightness = uiSyntaxColor.getLuminance();

        Float uiInv = 10.f; // inverse lightness if below

        Float uiSaturation;
        Float uiLightness;
        Float uiLightnessBorder;
        Float uiHue;


        if (uiSyntaxHue <= 80 ) {
            uiSaturation = Math.min(uiSyntaxSaturation, 5); // minimize saturation for brown
        } else if (uiSyntaxHue > 80 && uiSyntaxHue < 160) {
            uiSaturation = Math.min(uiSyntaxSaturation, 12); // reduce saturation for green
        } else if (uiSyntaxHue >= 160 && uiSyntaxLightness < uiInv) {
            uiSaturation = Math.min(uiSyntaxSaturation, 48); // limit max saturaiotn for very dark backgrounds
        } else {
            // if (uiSyntaxHue >= 160 && uiSyntaxLightness >= uiInv)
            uiSaturation = uiSyntaxSaturation;
        }




        if (uiSyntaxLightness < uiInv) {
            uiLightness = uiSyntaxLightness + 8; // increase lightness when too dark
            uiLightnessBorder = uiLightness * 0.3f;
        } else {
            uiLightness = Math.min(uiSyntaxLightness, 20); // limit max lightness (for light syntax themes)
            uiLightnessBorder = uiLightness * 0.6f;
        }


        if (uiSyntaxSaturation == 0) {
            uiHue = 220.f; // Use blue hue when no saturation
        } else {
            uiHue = uiSyntaxHue;
        }

        HSLColor uiBg = new HSLColor(uiHue, uiSaturation, uiLightness);
        buttonBorderColor = new HSLColor(uiHue, uiSaturation, uiLightnessBorder).getRGB();
        HSLColor baseBackgroundHSLColor = new HSLColor(uiBg.adjustLuminance(uiBg.getLuminance() - 3));
        Color baseBackgroundColor = baseBackgroundHSLColor.getRGB();
        this.baseBackgroundColor = new ColorUIResource(baseBackgroundColor);

        //@background-color-highlight: lighten(@base-background-color, 4%);
        baseBackgroundHighlight = baseBackgroundHSLColor.adjustLuminance(baseBackgroundHSLColor.getLuminance() + 4);

        baseForeground = new HSLColor(uiHue, Math.min(uiSaturation, 18), Math.max(uiLightness*3, 66)).getRGB();

        accentColor = new HSLColor(uiHue, 100, 66).getRGB();

        buttonColor = uiBg.adjustLuminance(uiBg.getLuminance() + 6);
        buttonSelectionColor = new Color(accentColor.getRGB());

        HSLColor buttonSelectionHSLColor = new HSLColor(buttonSelectionColor);
        if (buttonSelectionHSLColor.getLuminance() > 40) {
            buttonSelectionForeground = new HSLColor(uiHue, 100, 10).getRGB();
        } else {
            buttonSelectionForeground = Color.WHITE;
        }

        level1Color = uiBg.adjustLuminance(uiBg.getLuminance() + 6);
        level2Color = uiBg.getRGB();
        level3Color = new Color(baseBackgroundColor.getRGB());
    }

    @Override
    public void initComponent() {
        this.initColors();

//        try {
//            UIManager.setLookAndFeel(new PrimeLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(UIUtil.isUnderDarcula());

//        updateUIManagerValue("darcula.textForeground", lightestGrey);
//
//        updateUIManagerValue("Panel.background", this.baseBackgroundColor); // All windows bg
//        updateUIManagerValue("Tree.background", this.baseBackgroundColor); // Tree component bg
//        updateUIManagerValue("control", this.baseBackgroundColor); // controls bg
//        updateUIManagerValue("TabbedPane.background", this.darkestGrey); // Tab default bg
//        updateUIManagerValue("darcula.background", syntaxBackgroundColor);
//
        updateUIManagerValue("TabbedPane.selected", syntaxBackgroundColor); // Tab selected bg

        updateUIManagerValue("TabbedPane.highlight", syntaxBackgroundColor);
//
//        // To darkest color
//        updateUIManagerValue("List.background", this.baseBackgroundColor);
//        updateUIManagerValue("TextArea.inactiveBackground", this.baseBackgroundColor);
//        updateUIManagerValue("darcula.inactiveBackground", this.baseBackgroundColor);
//        updateUIManagerValue("TextPane.inactiveBackground", this.baseBackgroundColor);
//        updateUIManagerValue("ScrollPane.background", this.baseBackgroundColor);
//        updateUIManagerValue("MenuBar.shadow", this.baseBackgroundColor);
//        updateUIManagerValue("Spinner.background", this.baseBackgroundColor);
//        updateUIManagerValue("PopupMenu.background", this.baseBackgroundColor);
//        updateUIManagerValue("Menu.background", this.baseBackgroundColor);
//        updateUIManagerValue("RadioButtonMenuItem.background", this.baseBackgroundColor);
//        updateUIManagerValue("TableHeader.background", this.baseBackgroundColor);
//        updateUIManagerValue("Desktop.background", this.baseBackgroundColor);
//        updateUIManagerValue("TabbedPane.shadow", this.baseBackgroundColor);
//        updateUIManagerValue("ProgressBar.background", this.baseBackgroundColor);
//        updateUIManagerValue("darcula.textBackground", this.baseBackgroundColor);
//        updateUIManagerValue("Panel.background", this.baseBackgroundColor);
//        updateUIManagerValue("window", this.baseBackgroundColor);
//        updateUIManagerValue("CheckBox.background", this.baseBackgroundColor);
//        updateUIManagerValue("RadioButton.background", this.baseBackgroundColor);
//        updateUIManagerValue("Slider.background", this.baseBackgroundColor);
//        updateUIManagerValue("ScrollBar.background", this.baseBackgroundColor);
//        updateUIManagerValue("ColorChooser.background", this.baseBackgroundColor);
//        updateUIManagerValue("SplitPane.highlight", this.baseBackgroundColor);
//        updateUIManagerValue("InternalFrame.background", this.baseBackgroundColor);
//        updateUIManagerValue("MenuBar.background", this.baseBackgroundColor);
//        updateUIManagerValue("MenuItem.background", this.baseBackgroundColor);
//        updateUIManagerValue("Tree.background", this.baseBackgroundColor);
//        updateUIManagerValue("ToolBar.background", this.baseBackgroundColor);
//        updateUIManagerValue("Label.background", this.baseBackgroundColor);
//        updateUIManagerValue("ComboBox.background", this.baseBackgroundColor);
//        updateUIManagerValue("OptionPane.background", this.baseBackgroundColor);
//        updateUIManagerValue("EditorPane.background", this.baseBackgroundColor);
//        updateUIManagerValue("PopupMenu.translucentBackground", this.baseBackgroundColor);
//        updateUIManagerValue("control", this.baseBackgroundColor);
//        updateUIManagerValue("TextPane.background", this.baseBackgroundColor);
//        updateUIManagerValue("ComboBox.disabledBackground", this.baseBackgroundColor);
//        updateUIManagerValue("Tree.textBackground", this.baseBackgroundColor);
//        updateUIManagerValue("TextField.inactiveBackground", this.baseBackgroundColor);
//        updateUIManagerValue("PasswordField.inactiveBackground", this.baseBackgroundColor);
//        updateUIManagerValue("CheckBoxMenuItem.background", this.baseBackgroundColor);
//        updateUIManagerValue("TabbedPane.background", this.baseBackgroundColor);
//        updateUIManagerValue("Button.background", this.baseBackgroundColor);
//        updateUIManagerValue("TabbedPane.selectHighlight", this.baseBackgroundColor);
//        updateUIManagerValue("SplitPane.background", this.baseBackgroundColor);
//        updateUIManagerValue("MenuBar.disabledBackground", this.baseBackgroundColor);
//        updateUIManagerValue("FormattedTextField.inactiveBackground", this.baseBackgroundColor);
//        updateUIManagerValue("ToggleButton.background", this.baseBackgroundColor);
//        updateUIManagerValue("Viewport.background", this.baseBackgroundColor);
//        updateUIManagerValue("Table.background", this.baseBackgroundColor);
//        updateUIManagerValue("TextField.shadow", this.baseBackgroundColor);
//        updateUIManagerValue("TabbedPane.darkShadow", this.baseBackgroundColor);
//        updateUIManagerValue("TabbedPane.highlight", this.baseBackgroundColor);
//
//        // to very dark brown
//        updateUIManagerValue("TextArea.background", this.veryDarkBrown);
//        updateUIManagerValue("PasswordField.background", this.veryDarkBrown);
//        updateUIManagerValue("EditorPane.inactiveBackground", this.veryDarkBrown);
//        updateUIManagerValue("FormattedTextField.background", this.veryDarkBrown);
//        updateUIManagerValue("TextField.background", this.veryDarkBrown);
//
//
//        // Selection
//        updateUIManagerValue("TextField.selectionBackground", this.darkBrown);
//        updateUIManagerValue("ComboBox.selectionBackground", this.darkBrown);
//        updateUIManagerValue("TextArea.selectionBackground", this.darkBrown);
//        updateUIManagerValue("Tree.selectionBackground", baseBackgroundHighlight);
//        updateUIManagerValue("FormattedTextField.selectionBackground", this.darkBrown);
//        updateUIManagerValue("EditorPane.selectionBackground", this.darkBrown);
//        updateUIManagerValue("PopupMenu.selectionBackground", this.darkBrown);
//        updateUIManagerValue("PasswordField.selectionBackground", this.darkBrown);
//        updateUIManagerValue("List.selectionBackground", this.darkBrown);
//        updateUIManagerValue("Table.selectionBackground", this.darkBrown);
//        updateUIManagerValue("Menu.selectionBackground", this.darkBrown);
//        updateUIManagerValue("MenuItem.selectionBackground", this.darkBrown);
//        updateUIManagerValue("MenuBar.selectionBackground", this.darkBrown);
//        updateUIManagerValue("TextPane.selectionBackground", this.darkBrown);
//
//        updateUIManagerValue("TextComponent.selectionBackgroundInactive", this.darkBrown);// = #0d293e
//        updateUIManagerValue("List.selectionInactiveBackground", this.darkBrown);// = #0d293e
//        updateUIManagerValue("Tree.selectionInactiveBackground", this.darkBrown);// = #0d293e
//        updateUIManagerValue("Table.selectionInactiveBackground", this.darkBrown);// = #0d293e
//        updateUIManagerValue("darcula.selectionBackgroundInactive", this.darkBrown);
//        updateUIManagerValue("darcula.selectionInactiveBackground", this.darkBrown);
//
//
//
//        // ????
//        updateUIManagerValue("activeCaptionText", this.darkestBrown);//
//        updateUIManagerValue("TextField.darkShadow", this.darkestBrown);//
//        updateUIManagerValue("PasswordField.capsLockIconColor", this.darkestBrown);//
//        updateUIManagerValue("ScrollBar.thumbDarkShadow", this.darkestBrown);//
//        updateUIManagerValue("TabbedPane.selectedTabTitleShadowNormalColor", this.darkestBrown);//
//        updateUIManagerValue("RadioButtonMenuItem.acceleratorSelectionForeground", this.darkestBrown);//
//        updateUIManagerValue("DesktopIcon.borderColor", this.darkestBrown);//
//        updateUIManagerValue("ComboBox.buttonDarkShadow", this.darkestBrown);//
//        updateUIManagerValue("CheckBoxMenuItem.acceleratorForeground", this.darkestBrown);//
//        updateUIManagerValue("ScrollBar.trackHighlight", this.darkestBrown);//
//        updateUIManagerValue("RadioButton.darkShadow", this.darkestBrown);//
//        updateUIManagerValue("TabbedPane.selectedTabTitleShadowDisabledColor", this.darkestBrown);//
//        updateUIManagerValue("ToggleButton.darkShadow", this.darkestBrown);//
//        updateUIManagerValue("menuText", this.darkestBrown);//
//        updateUIManagerValue("Slider.focus", this.darkestBrown);//
//        updateUIManagerValue("TabbedPane.nonSelectedTabTitleNormalColor", this.darkestBrown);//
//        updateUIManagerValue("Button.darkShadow", this.darkestBrown);//
//        updateUIManagerValue("SplitPane.darkShadow", this.darkestBrown);//
//        updateUIManagerValue("windowText", this.darkestBrown);//
//        updateUIManagerValue("Table.dropLineShortColor", this.darkestBrown);//
//        updateUIManagerValue("TabbedPane.focus", this.darkestBrown);//
//        updateUIManagerValue("Button.darcula.disabledText.shadow", this.darkestBrown);//
//        updateUIManagerValue("ToolBar.darkShadow", this.darkestBrown);//
//        updateUIManagerValue("RadioButtonMenuItem.acceleratorForeground", this.darkestBrown);//
//        updateUIManagerValue("textHighlightText", this.darkestBrown);//
//        updateUIManagerValue("DesktopIcon.labelBackground", this.darkestBrown);//
//        updateUIManagerValue("controlText", this.darkestBrown);//
//        updateUIManagerValue("CheckBoxMenuItem.acceleratorSelectionForeground", this.darkestBrown);//
//        updateUIManagerValue("Menu.acceleratorForeground", this.darkestBrown);//
//        updateUIManagerValue("InternalFrame.activeTitleForeground", this.darkestBrown);//
//        updateUIManagerValue("Menu.acceleratorSelectionForeground", this.darkestBrown);//
//        updateUIManagerValue("controlDkShadow", this.darkestBrown);//
//
//
//
//        updateUIManagerValue("Separator.highlight", this.darkestBrown);// = #ffffff
//        updateUIManagerValue("Separator.shadow", this.darkestBrown);// = #8e8e8e
//        updateUIManagerValue("Separator.foreground", this.veryDarkBrown);// = #515151
//
//
//        // Button gradient
//        updateUIManagerValue("Button.darcula.color1", buttonColor);
//        updateUIManagerValue("Button.darcula.color2", buttonColor);
////        updateUIManagerValue("Button.foreground", buttonSelectionForeground);
//
//        // Selected button gradient
//        updateUIManagerValue("Button.darcula.selection.color1", buttonSelectionColor);
//        updateUIManagerValue("Button.darcula.selection.color2", buttonSelectionColor);
//        updateUIManagerValue("Button.darcula.selectedButtonForeground", buttonSelectionForeground);
//
//
//        updateUIManagerValue("RadioButtonMenuItem.selectionBackground", this.darkestBrown);
//        updateUIManagerValue("ProgressBar.selectionBackground", this.darkestBrown);
//        updateUIManagerValue("darcula.selectionBackground", this.darkestBrown);
//        updateUIManagerValue("CheckBoxMenuItem.selectionBackground", this.darkestBrown);
//
//        updateUIManagerValue("ToolTip.background", accentColor);
//        updateUIManagerValue("ToolTip.foreground", this.black);
//        updateUIManagerValue("link.foreground", accentColor);
//        updateUIManagerValue("Hyperlink.linkColor", accentColor);
//
//
//        updateUIManagerValue("Tree.foreground", baseForeground);
//        updateUIManagerValue("Tree.textForeground", baseForeground);
//        updateUIManagerValue("Tree.selectionInactiveForeground", baseForeground);
////
////
////        updateUIManagerValue("ToolTip.border", BorderFactory.createLineBorder(this.orange)); // IdeToolTipManager:322
////        updateUIManagerValue("ToolTip.borderInactive", BorderFactory.createEmptyBorder());
////
////        updateUIManagerValue("SplitPane.shadow", this.orange);
////        updateUIManagerValue("SplitPane.darkShadow", this.orange);
//
//        updateUIManagerValue("Button.border", new ButtonPainter()); // Implement something
////        updateUIManagerValue("windowBorder", this.orange);
//
//
        updateUIManagerValue("Tree.rowHeight", 24);
//
//        updateUIManagerValue("Separator.background", this.orange);
//        ColorUtil.isDark()


        // Checkboxes

//        updateUIManagerValue("CheckBoxUI", CheckBoxUI.class.getCanonicalName());
//        System.out.println(UIManager.get("CheckBoxUI"));

//        updateUIManagerValue("CheckBox.border", new CheckBoxBorder());
//        updateUIManagerValue("CheckBox.icon", null);
//        System.out.println(UIManager.get("CheckBox.icon"));
//        updateUIManagerValue("CheckBox.darcula.backgroundColor1", new HSLColor(baseBackgroundColor).adjustLuminance(new HSLColor(baseBackgroundColor).getLuminance() - 6));
//        updateUIManagerValue("CheckBox.darcula.backgroundColor2", new HSLColor(baseBackgroundColor).adjustLuminance(new HSLColor(baseBackgroundColor).getLuminance() - 6));
//
//
//        updateUIManagerValue("CheckBox.darcula.focused.backgroundColor1", new HSLColor(baseBackgroundColor).adjustLuminance(new HSLColor(baseBackgroundColor).getLuminance() - 6));
//        updateUIManagerValue("CheckBox.darcula.focused.backgroundColor2", new HSLColor(baseBackgroundColor).adjustLuminance(new HSLColor(baseBackgroundColor).getLuminance() - 6));
//
//        updateUIManagerValue("CheckBox.darcula.shadowColor", baseBackgroundColor);
//        updateUIManagerValue("CheckBox.darcula.shadowColorDisabled", baseBackgroundColor);
//
//        updateUIManagerValue("CheckBox.darcula.borderColor1", buttonBorderColor);
//        updateUIManagerValue("CheckBox.darcula.borderColor2", buttonBorderColor);



//        Ball

//        Hint

//        System.out.println();

//        Set defaults = UIManager.getDefaults().entrySet();
//        for (Iterator i = defaults.iterator(); i.hasNext();) {
//            Map.Entry entry = (Map.Entry) i.next();
////            if (entry.getValue() instanceof javax.swing.plaf.ColorUIResource) {
//                System.out.print(entry.getKey() + " = ");
//                System.out.println(entry.getValue());
////                String hex = String.format("#%02x%02x%02x", ((ColorUIResource) entry.getValue()).getRed(), ((ColorUIResource) entry.getValue()).getGreen(), ((ColorUIResource) entry.getValue()).getBlue());
////                System.out.println(hex);
////            }
//        }


//        try {
//            UIManager.setLookAndFeel(new PrimeLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    public void updateUIManagerValue(Object key, Object value) {
        Object existValue = UIManager.get(key);
        if (existValue != null) {
            UIManager.getDefaults().remove(key);
        }
        UIManager.put(key, value);
    }

    public String getComponentName() {
        return "PrimeTheme";
    }
}
