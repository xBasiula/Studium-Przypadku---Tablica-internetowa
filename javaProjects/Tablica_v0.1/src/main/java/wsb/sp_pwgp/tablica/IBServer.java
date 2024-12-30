package wsb.sp_pwgp.tablica;

import java.awt.Button;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author kmi
 */
@SuppressWarnings("serial")
public class IBServer extends Frame implements Runnable {
    private ServerSocket serverSocket;

    private ArrayList<IBService> clients = new ArrayList<>();

    private Properties props;

    public IBServer(Properties p, String title) {
        super(title);
        props = p;
        int port = Integer.parseInt(props.getProperty("port"));
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Error starting IBServer.");
            System.exit(1);
        }
        Button b = new Button("stop and exit");
        b.addActionListener((actionEvent) -> {
            send(IBProtocol.STOP);
            while (clients.size() != 0) {// nieładnie... busy waiting!
            	// jak to wyeliminować? ;)
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            System.exit(0);
        });
        add(b);
        pack();
        setVisible(true);
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                addClientService(clientSocket);
            } catch (IOException e) {
                System.err.println("Error accepting connection. "
                        + "Client will not be served...");
            }
        }
    }

    synchronized void addClientService(Socket clientSocket)
            throws IOException {
    	IBService clientService = new IBService(clientSocket, this);
    	clients.add(clientService);
    	clientService.init();
        new Thread(clientService).start();
        System.out.println("Client added. Nc=" + clients.size());
    }

    synchronized void removeClientService(IBService clientService) {
        clients.remove(clientService);
        clientService.close();
        System.out.println("Client removed. Nc=" + clients.size());
    }

    synchronized void send(String msg) {
    	for (IBService s : clients) { // roześlij do wszystkich klientów
            s.send(msg);
            System.out.println("Send to all " + msg);
    	}
    }

    synchronized void send(String msg, IBService skip) {
    	for (IBService s : clients) { // roześlij do wszystkich klientów
    		if (s != skip) { // oprócz jednego, którego trzeba pominąć...
                s.send(msg);
                System.out.println("Send to all with skip " + msg);
    		}
    	}
    }

    private int $lastID = -1;

    synchronized int nextID() {
        return ++$lastID;
    }

    int nextColor() {
        return $lastID % IBProtocol.colors.length;
    }

    int boardWidth() {
        return Integer.parseInt(props.getProperty("width"));
    }

    int boardHeight() {
        return Integer.parseInt(props.getProperty("height"));
    }

    public static void main(String args[]) {
        Properties p = new Properties();
        String pName = "IBServer.properties";
        try {
            p.load(new FileInputStream(pName));
        } catch (Exception e) {
            p.put("port", "40000");
            p.put("width", "250");
            p.put("height", "250");
        }
        try {
            p.store(new FileOutputStream(pName), null);
        } catch (Exception e) {
        }
        new IBServer(p, "Internet Board Server");
    }
}