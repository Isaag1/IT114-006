package Module6.Part9.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.ArrayList;

import Module6.Part9.common.Payload;
import Module6.Part9.common.PayloadType;
import Module6.Part9.common.RoomResultPayload;
/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ArrayList<String> mutedList = new ArrayList<String>();
    private ObjectOutputStream out;
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myId;

    public void setClientId(long id) {
        myId = id;
    }

    public long getClientId() {
        
        return myId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Room room) {
        info("Thread created");
        //Communication to single client
        this.client = myClient;
        this.currentRoom = room;

    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            System.err.println("Invalid client name being set");
            return;
        }
        clientName = name;

    }

    protected String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myId, getClientName(), false);
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    //Methods
    public boolean sendRoomName(String name){
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }
    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        if(rooms != null && rooms.length == 0){
            payload.setMessage("No rooms found containing your query string");
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        p.setMessage(isConnected ? "connected" : "disconnected");
        return send(p);
    }

    private boolean send(Payload payload) {
        //Boolean to see if send was successful
        try {
            // TODO add logger
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            info("Message was attempted to be sent before outbound stream was opened: " + payload);
            return true;// true so that it can pend being opened
        }
    }

    // finished send methods
    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && //let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from the inputStream 
            ) {

                info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close the loop
        } catch (Exception e) {
            e.printStackTrace();
            info("Client disconnected");
        } finally {
            isRunning = false;
            info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }
    //iag8 5/3/23
    void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT:
                break;
            case MESSAGE:
            //iag8 5/3/23
                // Check if there is a valid room to handle the message

                if (currentRoom != null) {
                 // Check if the message starts with "/flip"

                    if (p.getMessage().startsWith("/flip")) {
                // Call the flip() function and send the result to the current room

                        currentRoom.sendMessage(this, "<b>#r#" + flip() + "#r#</b>");
                    }
                // Check if the message starts with "/roll"

                    else if (p.getMessage().startsWith("/roll"))
                    {   
                // Call the roll() function and send the result to the current room
                        currentRoom.sendMessage(this, "<b>#g#" + roll() + "</b>#g#");
                    }
                // Check if the message starts with "@"

                    else if (p.getMessage().startsWith("@")) {
                        String message = p.getMessage();
                // Get the username by finding the substring between "@" and the first space

                        String username = message.substring(1,message.indexOf(" "));
                // Extract the rest of the message after the first space
                         currentRoom.sendMessage(this, username, message.substring(message.indexOf(" ") + 1));
                // Send the message to the current room, addressing it to the specified username

                    } 
                    else {
                        currentRoom.sendMessage(this, p.getMessage());
                    }

                } else {
                    // TODO migrate 
                    logger.log(Level.INFO, "Migrating to lobby on message with null room");
                    Room.joinRoom("lobby", this);
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            default:
                break;

        }

    }

    private String roll() {
        Random rand = new Random();
        int upperBound = 6;
        int randInt = rand.nextInt(upperBound);
        return String.valueOf(randInt);
    }

    private String flip() {
        String toss;
        Random rand1 = new Random();
        int randInteger = rand1.nextInt(2);
        if (randInteger == 0) {
                toss = "heads";
            }
        else {
                toss = "tails";
            }
        return toss;
    }
    
// This method adds a name to the muted list.

    public void addToMutedList(String name) {

        mutedList.add(name);
    }
// This method checks if a name is present in the muted list.

    public boolean inMutedList(String name) {
        return mutedList.contains(name);
    }
// This method removes a name from the muted list.

    public void removeFromMutedList(String name) {
        mutedList.remove(name);
    }
    

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }
}