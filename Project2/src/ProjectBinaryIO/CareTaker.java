package ProjectBinaryIO;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * CareTaker creates a file in the working directory in which it can write and read Mementos to.
 * Has methods to write a Memento to storage, and to read the Memento back as a Memento.
 * @author Alexander Dung
 *
 */
public class CareTaker {
	
	/** The reader. */
	private ObjectInputStream reader;
	
	/** The writer. */
	private ObjectOutputStream writer;
	
	/** The file dest. */
	private String fileDest;
	
	/** Is the PrintWriter closed */
	private boolean isClosed;
	
	/**
	 * Instantiates a new care taker.
	 */
	public CareTaker() {
		isClosed = false;
		fileDest = "cones.dat";
		File file = new File(fileDest);
		try {
			//create the file
			file.createNewFile();
			System.out.println("The file that stores the mementos is located at :"+file.getAbsolutePath());
			//MUST create the output stream first, or else input stream hangs.
			this.writer = new ObjectOutputStream(new FileOutputStream(fileDest));
			this.reader = new ObjectInputStream(new FileInputStream(fileDest));
		}
		catch(FileNotFoundException e) {
			System.out.println("CareTaker Construction: File cones.bin could not be created/found.");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("CareTaker Construction: IOException thrown while constructing CareTaker.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves a Memento to file.
	 *
	 * @param toSave the Memento to save
	 */
	public void saveMemento(Memento toSave) {
		try {
			//save Memento to file
			this.writer.writeObject(toSave);
		}
		catch(IOException e) {
			System.out.println("CareTaker: Problem saving current state of IceCreamCone.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a Memento from file.
	 *
	 * @return the Memento read from file
	 */
	public Memento readMemento() {
		Memento prevState = new Memento(1, "vanila", "regular");
		try{
			prevState = (Memento)reader.readObject();
		}
		catch(IOException e) {
			System.out.println("CareTaker: IOException thrown while reading Memento from file.");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.out.println("CareTaker: ClassNotFoundException thrown while reading Memento from file.");
			e.printStackTrace();
		}
		return prevState;
	}
	
	/**
	 * Closes the ObjectOutputStream.
	 */
	public void closeWriter() {
		try {
			this.writer.close();
			isClosed = true;
		}
		catch(IOException e) {
			System.out.println("CareTaker: IOException thrown while closing ObjectOutputStream.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the ObjectInputStream.
	 */
	public void closeReader() {
		try {
			this.reader.close();
		}
		catch(IOException e){
			System.out.println("CareTaker: IOException thrown while closing ObjectInputStream.");
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		//We try to make a new reader shouldn't ever really throw an error.
		try {
			ObjectInputStream tempreader = new ObjectInputStream(new FileInputStream(fileDest));
			//We check to make sure the writer is closed before reading from it.
			if(isClosed) {
				AdvancedIceCreamCone temp = new AdvancedIceCreamCone();
				String toRet = "All of the stored mementos:\n";
				//We use this try block to catch when we get to the end of the file and when we do catch it we close our temp reader and return everything.
				try {
					while(true) {
						//read cone from file
						temp.restore((Memento)tempreader.readObject());
						toRet+=(temp.toString()+"\n");
					}
				}catch(EOFException eof) {
					tempreader.close();
					return toRet+"End of memento file contents.";
				}
			//If the reader is open we can't do anything and we just return that we can't do anything.
			}else {
				tempreader.close();
				return "The memento file cannot currently be read because it is open";
			}
			//If we have some problem reading from the memento file we print the stack trace and return the error.  We use a soft error here because
		}catch(Exception e) {
			
			e.printStackTrace();
			return "Error when trying to read from the memento file";
			
		}
		
		
		
	}
}
