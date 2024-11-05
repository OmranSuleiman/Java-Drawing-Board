package se.miun.ommo2200.dt187g.jpaint.GUI;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;

/**
 * Menu
 * 
 * @author (ommo2200)
 * @version 1.0
 */

public class Menu extends JMenuBar {

    public void addJMenu(String name) {
        JMenu menu = new JMenu(name);
        this.add(menu);
    }

    public void addJMenuItem(String parentName, String itemName) {
        JMenu parentMenu = (JMenu) getComponentByName(parentName);
        if (parentMenu != null) {
            JMenuItem item = new JMenuItem(itemName);
            parentMenu.add(item);
        }
    }

    public void addJMenuItem(String parentName, String itemName, ActionListener al) {
        JMenu parentMenu = (JMenu) getComponentByName(parentName);
        if (parentMenu != null) {
            JMenuItem item = new JMenuItem(itemName);
            item.setName(itemName);
            item.addActionListener(al);
            parentMenu.add(item);
        }
    }

    public void addJMenuItem(String parentName, String itemName, ActionListener al, KeyStroke keyStroke) {
        JMenu parentMenu = (JMenu) getComponentByName(parentName);
        if (parentMenu != null) {
            JMenuItem item = new JMenuItem(itemName);
            item.setName(itemName);
            item.addActionListener(al);
            item.setAccelerator(keyStroke);
            parentMenu.add(item);
        }
    }

    public void addSubJMenu(String parentName, String subMenuName) {
        JMenu parentMenu = (JMenu) getComponentByName(parentName);
        if (parentMenu != null) {
            JMenu subMenu = new JMenu(subMenuName);
            parentMenu.add(subMenu);
        }
    }

    public JMenu getJMenu(int index) {
        if (index >= 0 && index < this.getMenuCount()) {
            return this.getMenu(index);
        }
        return null;
    }

    private JComponent getComponentByName(String name) {
        Component[] components = this.getComponents();
        for (Component component : components) {
            if (component instanceof JMenuItem && ((JMenuItem) component).getText().equals(name)) {
                return (JMenuItem) component;
            } else if (component instanceof JMenu) {
                JMenu menu = (JMenu) component;
                if (menu.getText().equals(name)) {
                    return menu;
                } else {
                    Component[] menuComponents = menu.getMenuComponents();
                    for (Component menuComponent : menuComponents) {
                        if (menuComponent instanceof JMenuItem && ((JMenuItem) menuComponent).getText().equals(name)) {
                            return (JMenuItem) menuComponent;
                        }
                    }
                }
            }
        }
        return null;
    }
}
