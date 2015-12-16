package com.prime.idea.laf;

import com.intellij.ide.IdeEventQueue;
import com.intellij.ide.ui.laf.IdeaLaf;
import com.intellij.ide.ui.laf.IntelliJLaf;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.ColorUtil;
import com.intellij.util.Alarm;
import com.intellij.util.containers.hash.HashMap;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.prime.idea.laf.ui.CheckBoxUI;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import org.jetbrains.annotations.NotNull;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.List;

public class PrimeLaf extends DarculaLaf {

    @Override
    public String getName() {
        return "PrimeLaf";
    }
//
//    @Override
//    public String getID() {
//        return null;
//    }
//
//    @Override
//    public String getDescription() {
//        return null;
//    }
//
//    @Override
//    public boolean isNativeLookAndFeel() {
//        return false;
//    }
//
//    @Override
//    public boolean isSupportedLookAndFeel() {
//        return true;
//    }

//    protected void loadDefaults(UIDefaults defaults) {
////        super.loadDefaults(defaults);
////        defaults.remove("CheckBoxUI");
//        defaults.put("CheckBoxUI", new CheckBoxUI());
//    }
//
//    @Override
//    protected void initIdeaDefaults(UIDefaults defaults) {
//        super.initIdeaDefaults(defaults);
//
//        System.out.println(defaults.get("CheckBoxUI"));
//    }

    //    public String getName() {
//        return "Darcula";
//    }
//    BasicLookAndFeel base;
//    private static Disposable myDisposable;
//    private static Alarm myMnemonicAlarm;
//    private static boolean myAltPressed;
////
//    public PrimeLaf() {
//        try {
//            if(!SystemInfo.isWindows && !SystemInfo.isLinux) {
//                String e = UIManager.getSystemLookAndFeelClassName();
//                this.base = (BasicLookAndFeel)Class.forName(e).newInstance();
//            } else {
//                this.base = new IdeaLaf();
//                System.out.println(this.base);
//            }
//        } catch (Exception var2) {
////            log(var2);
//        }
//    }
////
//    private void callInit(String method, UIDefaults defaults) {
//        try {
//            Method e = BasicLookAndFeel.class.getDeclaredMethod(method, new Class[]{UIDefaults.class});
//            e.setAccessible(true);
//            e.invoke(this.base, new Object[]{defaults});
//        } catch (Exception var4) {
////            log(var4);
//        }
//
//    }
////
////    private static void log(Exception e) {
////        e.printStackTrace();
////    }
////
//    public UIDefaults getDefaults() {
//        try {
//            Method e = BasicLookAndFeel.class.getDeclaredMethod("getDefaults", new Class[0]);
//            e.setAccessible(true);
//            UIDefaults metalDefaults = (UIDefaults)e.invoke(new MetalLookAndFeel(), new Object[0]);
//            UIDefaults defaults = (UIDefaults)e.invoke(this.base, new Object[0]);
//            if(SystemInfo.isLinux) {
//                if(!Registry.is("darcula.use.native.fonts.on.linux")) {
//                    Font font = findFont("DejaVu Sans");
//                    if(font != null) {
//                        Iterator key = defaults.keySet().iterator();
//
//                        while(key.hasNext()) {
//                            Object font1 = key.next();
//                            if(font1 instanceof String && ((String)font1).endsWith(".font")) {
//                                defaults.put(font1, new FontUIResource(font.deriveFont(13.0F)));
//                            }
//                        }
//                    }
//                } else if(Arrays.asList(new String[]{"CN", "JP", "KR", "TW"}).contains(Locale.getDefault().getCountry())) {
//                    Iterator font2 = defaults.keySet().iterator();
//
//                    while(font2.hasNext()) {
//                        Object key1 = font2.next();
//                        if(key1 instanceof String && ((String)key1).endsWith(".font")) {
//                            Font font3 = defaults.getFont(key1);
//                            if(font3 != null) {
//                                defaults.put(key1, new FontUIResource("Dialog", font3.getStyle(), font3.getSize()));
//                            }
//                        }
//                    }
//                }
//            }
//
//            LafManagerImpl.initInputMapDefaults(defaults);
//            this.initIdeaDefaults(defaults);
//            this.patchStyledEditorKit(defaults);
//            patchComboBox(metalDefaults, defaults);
//            defaults.remove("Spinner.arrowButtonBorder");
//            defaults.put("Spinner.arrowButtonSize", JBUI.size(16, 5).asUIResource());
//            MetalLookAndFeel.setCurrentTheme(this.createMetalTheme());
//            if(SystemInfo.isWindows && Registry.is("ide.win.frame.decoration")) {
//                JFrame.setDefaultLookAndFeelDecorated(true);
//                JDialog.setDefaultLookAndFeelDecorated(true);
//            }
//
//            if(SystemInfo.isLinux && JBUI.isHiDPI()) {
//                applySystemFonts(defaults);
//            }
//
//            defaults.put("EditorPane.font", defaults.getFont("TextField.font"));
//            return defaults;
//        } catch (Exception var7) {
////            log(var7);
//            return super.getDefaults();
//        }
//    }
////
//    private static void applySystemFonts(UIDefaults defaults) {
//        try {
//            String e = UIManager.getSystemLookAndFeelClassName();
//            Object systemLookAndFeel = Class.forName(e).newInstance();
//            Method superMethod = BasicLookAndFeel.class.getDeclaredMethod("getDefaults", new Class[0]);
//            superMethod.setAccessible(true);
//            UIDefaults systemDefaults = (UIDefaults)superMethod.invoke(systemLookAndFeel, new Object[0]);
//            Iterator var5 = systemDefaults.entrySet().iterator();
//
//            while(var5.hasNext()) {
//                Map.Entry entry = (Map.Entry)var5.next();
//                if(entry.getValue() instanceof Font) {
//                    defaults.put(entry.getKey(), entry.getValue());
//                }
//            }
//        } catch (Exception var7) {
////            log(var7);
//        }
//
//    }
////
////    protected DefaultMetalTheme createMetalTheme() {
////        return new PrimeMetalTheme();
////    }
////
//    private static Font findFont(String name) {
//        Font[] var1 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
//        int var2 = var1.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            Font font = var1[var3];
//            if(font.getName().equals(name)) {
//                return font;
//            }
//        }
//
//        return null;
//    }
////
//    private static void patchComboBox(UIDefaults metalDefaults, UIDefaults defaults) {
//        defaults.remove("ComboBox.ancestorInputMap");
//        defaults.remove("ComboBox.actionMap");
//        defaults.put("ComboBox.ancestorInputMap", metalDefaults.get("ComboBox.ancestorInputMap"));
//        defaults.put("ComboBox.actionMap", metalDefaults.get("ComboBox.actionMap"));
//    }
////
//    private void patchStyledEditorKit(UIDefaults defaults) {
//        URL url = this.getClass().getResource("/resources/prime" + (JBUI.isHiDPI()?"@2x.css":".css"));
//        StyleSheet styleSheet = UIUtil.loadStyleSheet(url);
//        defaults.put("StyledEditorKit.JBDefaultStyle", styleSheet);
//
//        try {
//            Field e = HTMLEditorKit.class.getDeclaredField("DEFAULT_STYLES_KEY");
//            e.setAccessible(true);
//            AppContext.getAppContext().put(e.get((Object)null), UIUtil.loadStyleSheet(url));
//        } catch (Exception var5) {
////            log(var5);
//        }
//
//    }
////
//    protected String getPrefix() {
//        return "darcula";
//    }
////
//    private void call(String method) {
//        try {
//            Method ignore = BasicLookAndFeel.class.getDeclaredMethod(method, new Class[0]);
//            ignore.setAccessible(true);
//            ignore.invoke(this.base, new Object[0]);
//        } catch (Exception var3) {
////            log(var3);
//        }
//
//    }
//
//    public void initComponentDefaults(UIDefaults defaults) {
//        this.callInit("initComponentDefaults", defaults);
//    }
////
//    protected void initIdeaDefaults(UIDefaults defaults) {
//        this.loadDefaults(defaults);
//        defaults.put("Table.ancestorInputMap", new UIDefaults.LazyInputMap(new Object[]{"ctrl C", "copy", "ctrl V", "paste", "ctrl X", "cut", "COPY", "copy", "PASTE", "paste", "CUT", "cut", "control INSERT", "copy", "shift INSERT", "paste", "shift DELETE", "cut", "RIGHT", "selectNextColumn", "KP_RIGHT", "selectNextColumn", "LEFT", "selectPreviousColumn", "KP_LEFT", "selectPreviousColumn", "DOWN", "selectNextRow", "KP_DOWN", "selectNextRow", "UP", "selectPreviousRow", "KP_UP", "selectPreviousRow", "shift RIGHT", "selectNextColumnExtendSelection", "shift KP_RIGHT", "selectNextColumnExtendSelection", "shift LEFT", "selectPreviousColumnExtendSelection", "shift KP_LEFT", "selectPreviousColumnExtendSelection", "shift DOWN", "selectNextRowExtendSelection", "shift KP_DOWN", "selectNextRowExtendSelection", "shift UP", "selectPreviousRowExtendSelection", "shift KP_UP", "selectPreviousRowExtendSelection", "PAGE_UP", "scrollUpChangeSelection", "PAGE_DOWN", "scrollDownChangeSelection", "HOME", "selectFirstColumn", "END", "selectLastColumn", "shift PAGE_UP", "scrollUpExtendSelection", "shift PAGE_DOWN", "scrollDownExtendSelection", "shift HOME", "selectFirstColumnExtendSelection", "shift END", "selectLastColumnExtendSelection", "ctrl PAGE_UP", "scrollLeftChangeSelection", "ctrl PAGE_DOWN", "scrollRightChangeSelection", "ctrl HOME", "selectFirstRow", "ctrl END", "selectLastRow", "ctrl shift PAGE_UP", "scrollRightExtendSelection", "ctrl shift PAGE_DOWN", "scrollLeftExtendSelection", "ctrl shift HOME", "selectFirstRowExtendSelection", "ctrl shift END", "selectLastRowExtendSelection", "TAB", "selectNextColumnCell", "shift TAB", "selectPreviousColumnCell", "shift ENTER", "selectPreviousRowCell", "ctrl A", "selectAll", "meta A", "selectAll", "ESCAPE", "cancel", "F2", "startEditing"}));
//    }
////
//    protected void loadDefaults(UIDefaults defaults) {
//        Properties properties = new Properties();
//        String osSuffix = SystemInfo.isMac?"mac":(SystemInfo.isWindows?"windows":"linux");
//
//        try {
//            InputStream e = this.getClass().getResourceAsStream("/properties/prime.properties");
//            properties.load(e);
//            e.close();
//            e = this.getClass().getResourceAsStream("/properties/prime_" + osSuffix + ".properties");
//            properties.load(e);
//            e.close();
//
//            HashMap darculaGlobalSettings = new HashMap();
//            String prefix = this.getPrefix() + ".";
//            Iterator var7 = properties.stringPropertyNames().iterator();
//
//            String key;
//            while(var7.hasNext()) {
//                key = (String)var7.next();
//                if(key.startsWith(prefix)) {
//                    darculaGlobalSettings.put(key.substring(prefix.length()), this.parseValue(key, properties.getProperty(key)));
//                }
//            }
//
//            var7 = defaults.keySet().iterator();
//
//            String value;
//            while(var7.hasNext()) {
//                Object key1 = var7.next();
//                if(key1 instanceof String && ((String)key1).contains(".")) {
//                    value = (String)key1;
//                    String darculaKey = value.substring(value.lastIndexOf(46) + 1);
//                    if(darculaGlobalSettings.containsKey(darculaKey)) {
//                        if (darculaKey.contentEquals("Button.darcula.selection.color2")) {
//                            System.out.println(key1);
//                            System.out.println(darculaKey);
//                        }
//                        defaults.put(key1, darculaGlobalSettings.get(darculaKey));
//                    }
//                }
//            }
//
//            var7 = properties.stringPropertyNames().iterator();
//
//            while(var7.hasNext()) {
//                key = (String)var7.next();
//                value = properties.getProperty(key);
//                defaults.put(key, this.parseValue(key, value));
//            }
//
////            System.out.println(defaults.get("window"));
//
//        } catch (IOException var11) {
////            log(var11);
//        }
//
//    }
////
//    protected Object parseValue(String key, @NotNull String value) {
//        if("null".equals(value)) {
//            return null;
//        } else if(key.endsWith("Insets")) {
//            return parseInsets(value);
//        } else {
//            if(!key.endsWith("Border") && !key.endsWith("border")) {
//                Color color = parseColor(value);
//                Integer invVal = getInteger(value);
//                Boolean boolVal = "true".equals(value)?Boolean.TRUE:("false".equals(value)?Boolean.FALSE:null);
//                Icon icon = value.startsWith("AllIcons.")? IconLoader.getIcon(value):null;
//                if(icon == null && value.endsWith(".png")) {
//                    icon = IconLoader.findIcon(value, PrimeLaf.class, true);
//                }
//
//                if(color != null) {
//                    return new ColorUIResource(color);
//                }
//
//                if(invVal != null) {
//                    return invVal;
//                }
//
//                if(icon != null) {
//                    return new IconUIResource(icon);
//                }
//
//                if(boolVal != null) {
//                    return boolVal;
//                }
//            } else {
//                try {
//                    if(StringUtil.split(value, ",").size() == 4) {
//                        return new BorderUIResource.EmptyBorderUIResource(parseInsets(value));
//                    }
//
//                    return Class.forName(value).newInstance();
//                } catch (Exception var7) {
////                    log(var7);
//                }
//            }
//
//            return value;
//        }
//    }
////
//    private static Insets parseInsets(String value) {
//        List numbers = StringUtil.split(value, ",");
//        return new InsetsUIResource(Integer.parseInt((String)numbers.get(0)), Integer.parseInt((String)numbers.get(1)), Integer.parseInt((String)numbers.get(2)), Integer.parseInt((String)numbers.get(3)));
//    }
////
//    private static Color parseColor(String value) {
//        if(value != null && value.length() == 8) {
//            Color color = ColorUtil.fromHex(value.substring(0, 6));
//            if(color != null) {
//                try {
//                    int alpha = Integer.parseInt(value.substring(6, 8), 16);
//                    return new ColorUIResource(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
//                } catch (Exception var3) {
//                    ;
//                }
//            }
//
//            return null;
//        } else {
//            return ColorUtil.fromHex(value, (Color)null);
//        }
//    }
////
//    private static Integer getInteger(String value) {
//        try {
//            return Integer.valueOf(Integer.parseInt(value));
//        } catch (NumberFormatException var2) {
//            return null;
//        }
//    }
//
////    public String getName() {
////        return "Darcula";
////    }
//
//    public String getID() {
//        return this.getName();
//    }
//
//    public String getDescription() {
//        return "IntelliJ Dark Look and Feel";
//    }
//
//    public boolean isNativeLookAndFeel() {
//        return true;
//    }
//
//    public boolean isSupportedLookAndFeel() {
//        return true;
//    }
////
//    protected void initSystemColorDefaults(UIDefaults defaults) {
//        this.callInit("initSystemColorDefaults", defaults);
//    }
//
//    protected void initClassDefaults(UIDefaults defaults) {
//        this.callInit("initClassDefaults", defaults);
//    }
////
//    public void initialize() {
//        try {
//            this.base.initialize();
//        } catch (Exception var2) {
//            ;
//        }
//
//        myDisposable = Disposer.newDisposable();
//        Application application = ApplicationManager.getApplication();
//        if(application != null) {
//            Disposer.register(application, myDisposable);
//        }
//
//        myMnemonicAlarm = new Alarm(Alarm.ThreadToUse.SHARED_THREAD, myDisposable);
//        IdeEventQueue.getInstance().addDispatcher(new IdeEventQueue.EventDispatcher() {
//            public boolean dispatch(AWTEvent e) {
//                if(e instanceof KeyEvent && ((KeyEvent)e).getKeyCode() == 18) {
//                    PrimeLaf.myAltPressed = e.getID() == 401;
//                    PrimeLaf.myMnemonicAlarm.cancelAllRequests();
//                    final Component focusOwner = IdeFocusManager.findInstance().getFocusOwner();
//                    if(focusOwner != null) {
//                        PrimeLaf.myMnemonicAlarm.addRequest(new Runnable() {
//                            public void run() {
//                                PrimeLaf.repaintMnemonics(focusOwner, PrimeLaf.myAltPressed);
//                            }
//                        }, 10);
//                    }
//                }
//
//                return false;
//            }
//        }, myDisposable);
//    }
////
//    public static boolean isAltPressed() {
//        return myAltPressed;
//    }
////
//    private static void repaintMnemonics(@NotNull Component focusOwner, boolean pressed) {
//        if(pressed == myAltPressed) {
//            Window window = SwingUtilities.windowForComponent(focusOwner);
//            if(window != null) {
//                Component[] var3 = window.getComponents();
//                int var4 = var3.length;
//
//                label43:
//                for(int var5 = 0; var5 < var4; ++var5) {
//                    Component component = var3[var5];
//                    if(component instanceof JComponent) {
//                        Iterator var7 = UIUtil.findComponentsOfType((JComponent)component, JComponent.class).iterator();
//
//                        while(true) {
//                            JComponent c;
//                            do {
//                                if(!var7.hasNext()) {
//                                    continue label43;
//                                }
//
//                                c = (JComponent)var7.next();
//                            } while((!(c instanceof JLabel) || ((JLabel)c).getDisplayedMnemonicIndex() == -1) && (!(c instanceof AbstractButton) || ((AbstractButton)c).getDisplayedMnemonicIndex() == -1));
//
//                            c.repaint();
//                        }
//                    }
//                }
//            }
//
//        }
//    }
////
//    public void uninitialize() {
//        try {
//            this.base.initialize();
//        } catch (Exception var2) {
//            ;
//        }
//
//        Disposer.dispose(myDisposable);
//        myDisposable = null;
//    }
////
//    protected void loadSystemColors(UIDefaults defaults, String[] systemColors, boolean useNative) {
//        try {
//            System.out.println("here i am");
//            Method ignore = BasicLookAndFeel.class.getDeclaredMethod("loadSystemColors", new Class[]{UIDefaults.class, String[].class, Boolean.TYPE});
//            ignore.setAccessible(true);
//            ignore.invoke(this.base, new Object[]{defaults, systemColors, Boolean.valueOf(useNative)});
//        } catch (Exception var5) {
//            System.out.println("here i am");
////            log(var5);
//        }
//
//    }
////
//    public boolean getSupportsWindowDecorations() {
//        return true;
//    }
//
//    public static Icon loadIcon(String iconName) {
//        return IconLoader.findIcon("/com/intellij/ide/ui/laf/icons/" + iconName, PrimeLaf.class, true);
//    }
}
