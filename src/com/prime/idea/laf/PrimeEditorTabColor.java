package com.prime.idea.laf;

import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class PrimeEditorTabColor implements EditorTabColorProvider {

    @Nullable
    @Override
    public Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        Color regularColor = UIManager.getColor("TabbedPane.background");
        Color selectedColor = UIManager.getColor("TabbedPane.selected");
        return new JBColor(regularColor, selectedColor);
    }


}
