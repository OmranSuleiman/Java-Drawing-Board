package se.miun.ommo2200.dt187g.jpaint;

import javax.swing.SwingUtilities;

import se.miun.ommo2200.dt187g.jpaint.GUI.JPaintFrame;

public class AppStart {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JPaintFrame().setVisible(true);

            }
        });
    }
}
