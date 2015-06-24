package audiocast.SC;
/** 
 * @author (C) E/11/080(Dhanushka J.P.T) And E/11/485(WEHELLA W.T.S)
 */

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.BlockingQueue;

public class client extends Thread {
	byte [] packet=new byte[1024];
	final public BlockingQueue<byte[]> queue;
	public static boolean recie=false;
	public client(BlockingQueue<byte[]> queue){
		this.queue=queue;
		
	}
	
	public void run(){
		try {
			InetAddress group=InetAddress.getByName("224.0.0.1");
			MulticastSocket ss=new MulticastSocket(6768);
			ss.joinGroup(group);
			
			while(!Thread.interrupted() ){
				DatagramPacket pack=new DatagramPacket(packet,packet.length);
				if(recie)
					ss.receive(pack);
				queue.put(packet);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
