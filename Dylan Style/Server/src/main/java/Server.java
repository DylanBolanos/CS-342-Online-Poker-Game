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

	Server(int port){
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
				System.out.println("Caught exception");
				printToServerInfo("Caught exception");

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

		public void run(){

			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}

			while(true) {
				try {
					PokerInfo p = (PokerInfo) in.readObject();
					printToServerInfo("STATE IS " + p.gameState);

					if(p.fold){
						printToServerInfo("Player " + count + ": Folded!");
						p.setTotalWinnings(p.getTotalWinnings() - p.getAnteBet());
						p.addTotalMoney(p.getAnteBet());
						System.out.println("Total winnings " + p.getTotalWinnings());
						System.out.println("Ante Bet " + p.getAnteBet());
						p.setAnteBet(0);
						p.fold = false;
					}

					if(p.getGameState() == 69 && p.sendingAnte){
						//printToServerInfo("Game State is 69!");
						printToServerInfo("Player " + count +  ": Set Ante Bet to " + p.getAnteBet());
						p.sendingAnte = false;
						p.addTotalMoney(-p.getAnteBet());
						printToServerInfo("Player " + count +  ": Has $ " + p.getTotalMoney() + " left!");
					} else if(p.getGameState() == 69 && p.sendingPairPlus){
						//printToServerInfo("Game State is 69!");
						printToServerInfo("Player " + count +  ": Set PairPlus Bet to: " + p.getPairPlusBet());
						//p.sendingPairPlus = false;
						p.addTotalMoney(-p.getAnteBet());
						printToServerInfo("Player " + count +  ": Has $ " + p.getTotalMoney() + " left!");
					} else if(p.getGameState() == 169){
						printToServerInfo("Game State is 169!");
						p.setPlayBet(p.getAnteBet());
						p.addTotalMoney(-p.getAnteBet());
						Dealer d = new Dealer();
						p.setPlayerHand(d.dealHand());
						p.setDealerHand(d.dealHand());
						printToServerInfo("Player " + count + ": Cards were dealt");
					} else if(p.getGameState() == 269){
						printToServerInfo("Game State is 269!");


						ArrayList<Card> playerHand = p.getPlayerHand();
						ArrayList<Card> dealerHand = p.getDealerHand();

						if(p.sendingPairPlus){
							int ppWinnings = ThreeCardLogic.evalPPWinnings(playerHand, p.getPairPlusBet());
							System.out.println("pair plus winnings: " + ppWinnings);
							if(ppWinnings == 0){
								p.setTotalWinnings(p.getTotalWinnings() - p.getPairPlusBet());
							} else {
								p.setTotalMoney(ppWinnings + p.getTotalMoney());
								p.setTotalWinnings(ppWinnings + p.getTotalWinnings());
							}
							p.sendingPairPlus = false;
						}

						int gameResult = ThreeCardLogic.compareHands(dealerHand, playerHand);
						p.gameResult = gameResult; // saving the game result to the player object
						System.out.println("PLAYER HAS: " + ThreeCardLogic.evalHand(playerHand));
						System.out.println("DEAL HAS: " + ThreeCardLogic.evalHand(dealerHand));

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

						}  else if(gameResult == 0 || !ThreeCardLogic.queenHigh(dealerHand)){ // tie we must check for Queen High
							if(!ThreeCardLogic.queenHigh(dealerHand)){
								printToServerInfo("Player " + count + ": Game Resulted in a tie because of queen high!");
							}
							printToServerInfo("Player " + count + ": Game Resulted in a tie");
							p.setTotalMoney(p.getAnteBet() + p.getPlayBet() + p.getTotalMoney());
						}

					} else if(p.getGameState() == 369){
						printToServerInfo("Game Result is 369!");
						printToServerInfo("Player " + count + ": Playing another game!");
						p.gameState = 69;
					}

					out.writeObject(p);
					out.flush();

				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					printToServerInfo("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");

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






