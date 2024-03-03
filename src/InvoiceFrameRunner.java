import javax.swing.*;

public class InvoiceFrameRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InvoiceFrame();
            }
        });
    }
}