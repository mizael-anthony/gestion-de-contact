/**
 * 
 */
package com.contact.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author mizael
 *
 */
public class FileManager {
	private File file;
	private DataInputStream dataReader;
	private DataOutputStream dataWriter;
	private ObjectInputStream objectReader;
	private ObjectOutputStream objectWriter;

	public FileManager() {
		this.file = null;
		this.dataReader = null;
		this.dataWriter = null;
		this.objectReader = null;
		this.objectWriter = null;
	}

	public FileManager(String path) {
		setFilePath(path);
		this.dataReader = null;
		this.dataWriter = null;
		this.objectReader = null;
		this.objectWriter = null;
	}

	public String browseFile(String title, String indication, String... filter) {
		JFileChooser choose = new JFileChooser();
		choose.setDialogTitle(title);
		choose.setAcceptAllFileFilterUsed(false);
		choose.addChoosableFileFilter(new FileNameExtensionFilter(indication, filter));

		int result = choose.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			return choose.getSelectedFile().getPath();
		}

		return "";
	}

	public void copyFile(String source, String destination) {
		
		Path sourceDir = Paths.get(source);
		Path destinationDir = Paths.get(destination);
		try {
			if(source.equals(destination)) {
				setFilePath(source.toString());
			}
			else {
				destinationDir = Paths.get(destinationDir.getParent() + "/" + sourceDir.getFileName());

				if (Files.exists(destinationDir)) {
					Files.delete(destinationDir);
				}
				
//				System.out.println(sourceDir.toString() + " " + destinationDir.toString());

				Files.createFile(destinationDir);
				setFilePath(destinationDir.toString());
			}
			
			Files.copy(sourceDir, destinationDir, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon createIcon(String path, int width, int height, int scale) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, scale));
	}

	public void delFilesFromSamePath() {
		File parent = (isFileDirectory()) ? this.file : this.file.getParentFile();
		for (File file : parent.listFiles())
			file.delete();
	}

	// Bola ho hita eo
	public boolean exitsFile() {
		return this.file.exists();
	}

	public String getFileName() {
		return this.file.getName();
	}

	public String getFilePath(boolean absoluteFilePath) {
		return (absoluteFilePath) ? this.file.getAbsolutePath() : this.file.getPath();
	}

	public List<FileManager> getFilesFromSamePath(String fileToFind) {
		List<FileManager> fileManagers = new ArrayList<FileManager>();

		fileToFind = (fileToFind == null) ? " " : fileToFind;
		File parent = (isFileDirectory()) ? this.file : this.file.getParentFile();

		for (File file : parent.listFiles()) {
			FileManager fileManager = new FileManager(file.getPath());
			if (fileManager.hasFileThis(fileToFind))
				fileManagers.add(fileManager);
			// System.out.println(file.getPath());
		}

		return fileManagers;

	}


	public boolean hasFileThis(String str) {
		String content = "";
		/**
		 * rehefa nosoratana tam DataOutputStream ihany vo afaka vakina am
		 * DataInputStream
		 */
//		List<String> fileContent = readDataFile();
//		= String.join(" ", fileContent.subList(1, fileContent.size()));
//		System.out.println(content);
		content = readObjectFile().toString();

		return content.contains(str);
	}

	public boolean isFileDirectory() {
		return this.file.isDirectory();
	}

	public List<String> readDataFile() {
		String tmp = "";
		try {
			this.dataReader = new DataInputStream(new BufferedInputStream(new FileInputStream(this.file)));

			while (this.dataReader.available() > 0) {
				tmp += this.dataReader.readChar();
			}

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Fichier introuvable");

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return new ArrayList<String>(Arrays.asList(tmp.split("\n")));

	}

	public Object readObjectFile() {
		Object obj = null;
		try {
			this.objectReader = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(this.file)));

			obj = this.objectReader.readObject();
			this.objectReader.close();

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Fichier introuvable !");
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return obj;
	}

	public void removeFile() {
		this.file.delete();
	}

	public void setFilePath(String path) {
		this.file = new File(path);
	}

	public void writeDataFile(List<String> informations) {
		try {
			this.dataWriter = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.file)));

			for (String information : informations) {
				this.dataWriter.writeChars(information + "\n");
			}
			this.dataWriter.close();

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Fichier introuvable");

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	public void writeObjectFile(Object obj) {
		try {
			this.objectWriter = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(this.file)));

			this.objectWriter.writeObject(obj);
			this.objectWriter.close();

		} catch (FileNotFoundException e) {
			//System.out.println("Fichier introuvalbe !");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// TODO: Methode: Renommer, Déplacer 


}
