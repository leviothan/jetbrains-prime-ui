package com.prime.idea;


import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.prime.idea.icons.PrimeUiIcons;
import org.apache.commons.lang.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IconProvider extends com.intellij.ide.IconProvider {

    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement psiElement, @Iconable.IconFlags int i) {
        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile != null) {
            VirtualFile vFile = containingFile.getVirtualFile();

            if (vFile != null) {
                Icon byFileTypeIcon = getIconByFileType(vFile);
                Icon byFileNameIcon = getIconByFileName(vFile);

                if (byFileTypeIcon != null && byFileNameIcon == null) {
                    return byFileTypeIcon;
                }

                return byFileNameIcon;
            }
        }

        return null;
    }

    @Nullable
    protected Icon getIconByFileType(VirtualFile virtualFile) {
        String type = virtualFile.getFileType().getName().toLowerCase();

        if (type.equals("php")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_PHP);
        }

        if (type.equals("html")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_HTML);
        }

        if (type.equals("images")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_MEDIA);
        }

        if (type.equals("javascript")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_JS);
        }

        if (type.equals("css")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_CSS);
        }

        if (type.equals("git file")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_GIT);
        }

        if (type.equals("sql")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_SQL);
        }

        if (type.equals("json")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_JSON);
        }

        if (type.equals("yaml")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_YAML);
        }

        if (type.equals("multimarkdown") || type.equals("markdown")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_MARKDOWN);
        }

        if (type.equals("sass") || type.equals("scss")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_SASS);
        }

        if (type.equals("less")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_LESS);
        }

        if (type.equals("twig")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_TWIG);
        }

        if (type.equals("archive")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_ARCHIVE);
        }

        if (type.equals("xml")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_CODE);
        }

        if (type.equals("java")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_JAVA);
        }

        return null;
    }

    @Nullable
    protected Icon getIconByFileName(VirtualFile virtualFile) {
        String filePath = virtualFile.getPath().toLowerCase();

        if (filePath.endsWith(".php") || filePath.endsWith(".phtml") || filePath.endsWith(".phps")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_PHP);
        }

        if (virtualFile.getName().equals("bower.json")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_BOWER);
        }

        if (filePath.endsWith(".twig")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_TWIG);
        }

        if (filePath.endsWith(".md")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_MARKDOWN);
        }

        if (filePath.endsWith(".yml")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_YAML);
        }

        if (filePath.endsWith(".scss") || filePath.endsWith(".sass")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_SASS);
        }

        if (filePath.endsWith(".htaccess")) {
            return PrimeUiIcons.getIcon(PrimeUiIcons.FILE_APACHE);
        }

        return null;
    }
}
