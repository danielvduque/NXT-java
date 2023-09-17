import lejos.nxt.*;
import java.io.*;

import lejos.nxt.*;
import lejos.nxt.comm.*;

public class pruebaBT {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String connected = "Connected";
        String waiting = "Waiting...";
        String closing = "Closing...";
        
        String numero="0";
        byte[] c = new byte[1];

        int salir=0;
        
	//main loop     
	while (salir==0)
	{
		LCD.drawString(waiting,0,0);
	    LCD.refresh();

	    //Listen for incoming connection
	    NXTConnection btc = Bluetooth.waitForConnection();
	    btc.setIOMode(NXTConnection.RAW);

	    LCD.clear();
	    LCD.drawString(connected,0,0);
	    LCD.refresh();  

	    //The InputStream for read data 
	    DataInputStream dis=btc.openDataInputStream();

	    //loop for read data    
	   // while(numero.equals("0") || numero.equals("1") || numero.equals("2") || numero.equals("3") || numero.equals("4")){

		    	// acumulo cada byte que llega
				//for(int i=0;i<1000;i++)
		    	while(true)
	    		{ 		    		
		    		Byte n=-1;
		    		//lee lo que llega en bytes
		    		n=dis.readByte();
		    		
			    	// guarda el byte en la primera casilla del arreglo y luego lo transforma a String
			    	c[0] = n;
			    	numero = new String(c);
		    		
		    		 LCD.clear();
		    		 LCD.drawString(numero,0,0);
		    		 
		    		 //adelante (arriba)
		    		 if(numero.equals("1"))
		    		    {
		    		    	Motor.B.setSpeed(250);
		    		    	Motor.C.setSpeed(250);

		    		    	Motor.B.forward();
		    		    	Motor.C.forward();
		    		    	
		    		    	Thread.sleep(4000); 
		    		    	Motor.B.stop();
		    		    	Motor.C.stop();
		    		    	// reinicia
		    		    	c[0] = 0;
		    		    }
		    		   
		    		    //atras (abajo)
		    		    if(numero.equals("2"))
		    		    {
		    		    	Motor.B.setSpeed(250);
		    		    	Motor.C.setSpeed(250);

		    		    	Motor.B.backward();
		    		    	Motor.C.backward();
		    		    	
		    		    	Thread.sleep(4000); 
		    		    	Motor.B.stop();
		    		    	Motor.C.stop();
		    		    	// reinicia
		    		    	c[0] = 0;
		    		    }
		    		    
		    		    //izquierda
		    		    if(numero.equals("3"))
		    		    {
		    		    	Motor.B.setSpeed(0);
		    		    	Motor.C.setSpeed(300);

		    		    	Motor.C.forward();
		    		    	
		    		    	Thread.sleep(800); 
		    		    	Motor.B.stop();
		    		    	Motor.C.stop();
		    		    	// reinicia
		    		    	c[0] = 0;
		    		    }
		    		    
		    		    //derecha
		    		    if(numero.equals("4"))
		    		    {
		    		    	Motor.B.setSpeed(300);
		    		    	Motor.C.setSpeed(0);

		    		    	Motor.B.forward();
		    		    	
		    		    	Thread.sleep(800); 
		    		    	Motor.B.stop();
		    		    	Motor.C.stop();
		    		    	// reinicia
		    		    	c[0] = 0;
		    		    }
		    		    
		    		    if(numero.equals("5"))
		    		    {
		    			     dis.close();
		    				 LCD.drawString(closing,0,0);
		    				 LCD.refresh();

		    				 btc.close();
		    				 Thread.sleep(200);
		    				 Button.waitForAnyPress();   
		    				 salir=1;
		    				 break;
		    				    
		    		    }
			    	
		    	 } // if
	      //  } // for
	    
	    dis.close();
	    Thread.sleep(500); // wait for data to drain

	    
	 LCD.clear();
	 LCD.drawString(closing,0,0);
	 LCD.refresh();

	 btc.close();
	 } 
		
	}
	
}
