package comunicacaoserial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import javax.swing.JOptionPane;
import tcc.telas.DesenhoPeso;
import tcc.telas.DesenhoReservatorio;

public class ControlePortaSerial implements SerialPortEventListener {
    private Boolean Pareado = false;
    private SerialPort serialPort;
    private String nomePorta = "COM3";
    private DesenhoReservatorio reservatorio;
    private DesenhoPeso peso;
    
    public ControlePortaSerial(DesenhoReservatorio reservatorio , DesenhoPeso peso) {
        this.reservatorio = reservatorio;
        this.peso = peso;
    }
    
    
    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the
     * bytes into characters making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    public void parear() throws TooManyListenersException {
        CommPortIdentifier portId = null;
        this.nomePorta = nomePorta;
        
        try {
            //Tenta verificar se a porta COM informada existe
            portId = CommPortIdentifier.getPortIdentifier(this.nomePorta);
        } catch (NoSuchPortException npe) {
            //Caso a porta COM não exista será exibido um erro 
            JOptionPane.showMessageDialog(null, "Porta COM não encontrada.",
                    "Porta COM", JOptionPane.PLAIN_MESSAGE);
        }
        if (portId != null) {
            try {
                serialPort = (SerialPort) portId.open("Iniciando Serial", this.DATA_RATE);
                output = serialPort.getOutputStream();
                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                serialPort.setSerialPortParams(this.DATA_RATE, //taxa de transferência da porta serial 
                        SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                        SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                        SerialPort.PARITY_NONE); //receber e enviar dados

                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);

                Pareado = true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            } catch (PortInUseException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            } catch (UnsupportedCommOperationException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        }else{
            
        }
       
    }

   
    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     *
     * @param oEvent
     */
    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                peso.atualizaPeso(inputLine);
                reservatorio.setVolReservatorio(inputLine);
                
                System.out.println(this.getClass().getName()+" Valor inputLine: "+inputLine);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    public Boolean getPareado() {
        return Pareado;
    }
    
    public static void main(String[] args) throws Exception {
        //ControlePortaSerial main = new ControlePortaSerial();
       
        Thread t = new Thread() {
            @Override
            public void run() {
				//the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");
    }

    public String getNomePorta() {
        return nomePorta;
    }

    public void setNomePorta(String nomePorta) {
        this.nomePorta = nomePorta;
    }
}
