import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 0; // Number of clients
	int port;

	public ListView<String> serverInfo = new ListView<>();
	static public ObservableList<String> serverInfoList = FXCollections.observableArrayList();

	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;

	//private Consumer<Serializable> callback;


	Server(int port){

		//callback = call;

		this.port = port;
		server = new TheServer();
		server.start();
	}


	public class TheServer extends Thread{

		public void run() {

			try(ServerSocket mysocket = new ServerSocket(port);){
				System.out.println("Server listening on port " + port);
				System.out.println("Server is waiting for a client!");


				while(true) {

					ClientThread c = new ClientThread(mysocket.accept(), count);
					System.out.println("Client has connected to server: \" + \"client #\" + count");
					printToServerInfo("client has connected to server: " + "client #" + count);

					//callback.accept("client has connected to server: " + "client #" + count);

					synchronized (clients){
						clients.add(c);
					}

					c.start();

					System.out.println("Client has connected!");
					printToServerInfo("Client has connected!");

					count++;
				}
			}//end of try
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("LMFAO BRUH MOMENTO");
				printToServerInfo("LMFAO BRUH MOMENTO");

				//callback.accept("Server socket did not launch");
			}
		}//end of while
	}


	class ClientThread extends Thread{


		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;

		ClientThread(Socket s, int count){
			this.connection = s;
			this.count = count;
		}

//		public void updateClients(String message) {
//			for(int i = 0; i < clients.size(); i++) {
//				ClientThread t = clients.get(i);
//				try {
//					t.out.writeObject(message);
//				}
//				catch(Exception e) {}
//			}
//		}

		public void run(){

			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}

			//updateClients("new client on server: client #"+count);

			while(true) {
				try {
					PokerInfo p = (PokerInfo) in.readObject();
					printToServerInfo("STATE IS " + p.gameState);
					if(p.getGameState() == 69 && p.sendingAnte){
						//printToServerInfo("Game State is 69!");
						printToServerInfo("Player " + count +  ": Set Ante Bet to " + p.getAnteBet());
						p.sendingAnte = false;
						p.addTotalMoney(-p.getAnteBet());
						printToServerInfo("Player " + count +  ": Has $ " + p.getTotalMoney() + " left!");
					} else if(p.getGameState() == 69 && p.sendingPairPlus){
						//printToServerInfo("Game State is 69!");
						printToServerInfo("Player " + count +  ": Set PairPlus Bet to: " + p.getPairPlusBet());
						p.sendingPairPlus = false;
						p.addTotalMoney(-p.getAnteBet());
						printToServerInfo("Player " + count +  ": Has $ " + p.getTotalMoney() + " left!");
					} else if(p.getGameState() == 169){
						printToServerInfo("Game State is 169!");
						p.setPlayBet(p.getAnteBet());
						Dealer d = new Dealer();
						p.setPlayerHand(d.dealHand());
						p.setDealerHand(d.dealHand());
						printToServerInfo("Player " + count + ": Cards Delt");
					} else if(p.getGameState() == 269){
						printToServerInfo("Game State is 269!");

						ArrayList<Card> playerHand = p.getPlayerHand();
						ArrayList<Card> dealerHand = p.getDealerHand();

						int gameResult = ThreeCardLogic.compareHands(dealerHand, playerHand);
						p.gameResult = gameResult; // saving the game result to the player object

						if(gameResult == 2) { // player wins
							printToServerInfo("Player " + count + ": Game Resulted in a Player win!");
							int winnings = (p.getAnteBet() + p.getPlayBet()) * 2;
							printToServerInfo("Player " + count + ": Winning this round is " + winnings);

							// putting money back into the pokerInfo object

							p.setTotalMoney(winnings + p.getTotalMoney());
							p.setTotalWinnings(winnings + p.getTotalWinnings());

						} else if (gameResult == 1){ // dealer wins
							printToServerInfo("Player " + count + ": Game Resulted in a Dealer win");
							int losings = p.getAnteBet() + p.getPlayBet();
							printToServerInfo("Player " + count + ": Lost this round is " + losings);

							//p.setTotalMoney(losings + p.getTotalMoney());
							p.setTotalWinnings(p.getTotalWinnings() - losings);

						}  else { // tie we must check for Queen High
							printToServerInfo("Player " + count + ": Game Resulted in a tie");
						}

					} else if(p.getGameState() == 369){
						printToServerInfo("Game Result is 369!");
						printToServerInfo("Player " + count + ": Playing another game!");
						p.gameState = 69;
					}

//					if(p != null) {
//						System.out.println("POKER OBJECT WAS FOUND MFER");
//						System.out.println(p.toString());
////						p.anteBet = 5;
//					}

					out.writeObject(p);
					out.flush();

					//String data = in.readObject().toString();
					//callback.accept("client: " + count + " sent: " + data);
					//updateClients("client #"+count+" said: "+data);

				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					printToServerInfo("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");


					//callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					//updateClients("Client #"+count+" has left the server!");
					clients.remove(this);
					break;
				}
			}
		}//end of run


	}//end of client thread

	public void printToServerInfo(String message){
		Platform.runLater(() -> {
			serverInfoList.addAll(message);
		});
	}

}






