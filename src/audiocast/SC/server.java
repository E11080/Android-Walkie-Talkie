package audiocast.SC;
/** 
 * @author (C) E/11/080(Dhanushka J.P.T) And E/11/485(WEHELLA W.T.S)
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

public class server extends Thread {
	public static boolean send = false;
	final public BlockingQueue<byte[]> queue;
	public server(BlockingQueue<byte[]> queue){
		this.queue=queue;
	}
	
	public void run(){
		
		    try{
		    	InetAddress group = InetAddress.getByName("224.0.0.1");
			    MulticastSocket s = new MulticastSocket(3210);			
		        s.joinGroup(group);
		        
				try {		
					while (!Thread.interrupted()) {
						DatagramPacket pack = new DatagramPacket(queue.take(), queue.take().length, group, 6789);
						if(send)s.send(pack);
						
					}
				} catch (InterruptedException e) {
					
				} finally {
					try {
						s.leaveGroup(group);
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
		    }catch (Exception e1){
		    	
		    }
	}
}
