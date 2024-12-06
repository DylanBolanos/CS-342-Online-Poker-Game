import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{


	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	int port;
	String ipAddress;
	gameController g;
	PokerInfo p;
	//private Consumer<Serializable> callback;

	public ListView<String> serverComLog = new ListView<>();
	static public ObservableList<String> serverComLogList = FXCollections.observableArrayList();

	Client(Consumer<Serializable> call, String ipAddress, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
		p = new PokerInfo();

		//callback = call;
	}

	public void setController(gameController controller) {
		g = controller;
	}

	public void run() {

		try {
			socketClient= new Socket(ipAddress,port);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
			System.out.println("Client connected");
		}
		catch(Exception e) {}

		while(true) {

			try {
				p = (PokerInfo)in.readObject();

				if(p.getGameState() == 169){
					System.out.println("Cards are being delt");
				}
				//String message = in.readObject().toString();
				//callback.accept(message);
			}
			catch(Exception e) {}
		}

	}

	public void send(PokerInfo data) {

		try {
			out.reset();
			out.writeObject(data);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printToServerComLog(String message) {
		Platform.runLater(() -> {
			serverComLogList.addAll(message);
		});
	}
}
