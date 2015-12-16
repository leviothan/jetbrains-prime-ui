package com.prime.idea.laf;

import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.ui.tabs.impl.DefaultEditorTabsPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.JBEditorTabsPainter;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class PrimeTabsPainterPatcher implements ApplicationComponent {

    public PrimeTabsPainterPatcher() {
    }

    @Override
    public void initComponent() {
        final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();
        bus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerAdapter() {
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                final FileEditor editor = event.getNewEditor();
                if (editor != null) {
                    Component component = editor.getComponent();
                    while (component != null) {
                        if (component instanceof JBEditorTabs) {
                            patchPainter((JBEditorTabs) component);
                            return;
                        }
                        component = component.getParent();
                    }
                }
            }
        });
    }

    private void patchPainter(JBEditorTabs component) {
        final JBEditorTabsPainter painter = ReflectionUtil.getField(JBEditorTabs.class, component,
                JBEditorTabsPainter.class, "myDarkPainter");

        if (painter instanceof MTTabsPainter) return;

        final MTTabsPainter tabsPainter = new MTTabsPainter();
        final JBEditorTabsPainter proxy = (MTTabsPainter) Enhancer.create(MTTabsPainter.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                final Object result = method.invoke(tabsPainter, objects);

                if ("paintSelectionAndBorder".equals(method.getName())) {
                    final Graphics2D g2d = (Graphics2D) objects[0];
                    final Rectangle rect = (Rectangle) objects[1];

                    Color tabBorderColor = UIManager.getColor("TabbedPane.highlight");
                    g2d.setColor(tabBorderColor);
                    g2d.fillRect(rect.x, 1, 2, rect.height - 4);
                }

                if ("createToolTip".equals(method.getName())) {
                    System.out.println("CREATE TOOLTIP");
//                    final Graphics2D g2d = (Graphics2D) objects[0];
//                    final Rectangle rect = (Rectangle) objects[1];
//
//                    Color tabBorderColor = UIManager.getColor("ToolTip.background");
//                    g2d.setColor(tabBorderColor);
//                    g2d.fillRect(rect.x, 1, 2, rect.height - 4);
                }

                return result;
            }
        });

        ReflectionUtil.setField(JBEditorTabs.class, component, JBEditorTabsPainter.class, "myDarkPainter", proxy);
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "PrimeTabsPainterPatcher";
    }


    public static class MTTabsPainter extends DefaultEditorTabsPainter {

        @Override
        protected Color getDefaultTabColor() {
            if (myDefaultTabColor != null) {
                return myDefaultTabColor;
            }

            return UIManager.getColor("TabbedPane.background");
        }

        @Override
        protected Color getInactiveMaskColor() {
            return this.getDefaultTabColor();
        }
    }
}
