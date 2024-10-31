package wsb.sp_pwgp.tablica;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author kmi
 */
@SuppressWarnings("serial")
public class IBApplication extends Frame {
    private ArrayList<IBClientController> clients = new ArrayList<>();
    
	IBApplication() {
		super("IBApplication");
        Label info = new Label("Internet Board", Label.CENTER);
        TextField host = new TextField("localhost", 30);
        TextField port = new TextField("40000", 8);
        Button login = new Button("login");
        login.addActionListener((actionEvent) -> {
	        host.setEnabled(false);
	        port.setEnabled(false);
	        login.setEnabled(false);
	        try {
	        	clients.add(new IBClientController(host.getText(), port.getText()));
	        } catch (Exception e) {
	            info.setText(e.toString());
	            pack();
	        }
	        login.setEnabled(true);
	        port.setEnabled(true);
	        host.setEnabled(true);
        });

        setBackground(Color.lightGray);
        setLayout(new GridLayout(0, 1));
        add(info);
        Panel p100 = new Panel();
        p100.add(new Label(" host: ", Label.RIGHT));
        p100.add(host);
        add(p100);
        Panel p200 = new Panel();
        p200.add(new Label(" port: ", Label.RIGHT));
        p200.add(port);
        add(p200);
        Panel p300 = new Panel();
        p300.add(login);
        add(p300);
        
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent we) {
        		destroy();
        		System.exit(1);
        	}
        });
        
        pack();
        EventQueue.invokeLater(() -> setVisible(true));
        new Thread(() -> {
            while (true) {
                cleanHouse();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    void destroy() {
    	for (IBClientController c : clients) {
    		c.forceLogout();
    	}
    }

    private void cleanHouse() {
    	ListIterator<IBClientController> i = clients.listIterator();
    	while (i.hasNext()) {
    		IBClientController c = i.next();
    		if (c.isDisconnected()) {
            	i.remove();
    		}
    	}
    }
    
    public static void main(String[] args) {
    	new IBApplication();
    }
}