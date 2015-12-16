package com.prime.idea.icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class PrimeUiIcons {
    public static String basePath = "/icons/fileTypes/";


    public static final String FILE_PHP = "php.png";
    public static final String FILE_CODE = "code.png";
    public static final String FILE_JS = "javaScript.png";
    public static final String FILE_CSS = "css.png";
    public static final String FILE_HTML = "html.png";
    public static final String FILE_PLAIN_TEXT = "text.png";
    public static final String FILE_MEDIA = "media.png";
    public static final String FILE_GIT = "git.png";
    public static final String FILE_SQL = "sql.png";
    public static final String FILE_JSON = "json.png";
    public static final String FILE_YAML = "yaml.png";
    public static final String FILE_MARKDOWN = "markdown.png";
    public static final String FILE_SASS = "sass.png";
    public static final String FILE_LESS = "less.png";
    public static final String FILE_TWIG = "twig.png";
    public static final String FILE_ARCHIVE = "archive.png";
    public static final String FILE_SHELL = "shell.png";
    public static final String FILE_GEAR = "gear.png";
    public static final String FILE_JAVA = "java.png";
    public static final String FILE_BOWER = "bower.png";
    public static final String FILE_APACHE = "apache.png";

    public static Icon getIcon(String iconFile) {
        String path = basePath;

        return IconLoader.getIcon(path.concat(iconFile));
    }
}
