package com.prime.idea.laf.ui;

import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.ide.ui.laf.darcula.ui.DarculaCheckBoxBorder;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.JBUI;
import com.prime.idea.PrimeTheme;

import javax.swing.*;
import java.awt.*;

public class CheckBoxBorder extends DarculaCheckBoxBorder {


    @Override
    public boolean isBorderOpaque() {
//        System.out.println(UIManager.get("CheckBox.icon"));
        return true;
//        return super.isBorderOpaque();
    }
}
