/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;


import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 * @author Cristian
 * 
 * Esta clase lleva acabo el funcionamiento del laberinto.
 */
@XmlRootElement
public class Maze implements  Serializable{

    private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;
/**
 * Constructor por defecto de la clase
 */
    public Maze() {
    }
/**
 * Metodo para inicializar los bloques del laberinto
 */
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();

            }
        }
    }
/**
 * Metodo para resetear el laberinto quitando los bloques
 */
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;

            }
        }
        this.setBlocks(null);
    }
/**
 * Metodo para resetear el tamaño del laberinto
 * 
 * @param newsize
 */
    public void reset(Size newsize) {
        this.reset();;
        this.setSize(newsize);
        this.init();
    }
/**
 * Metodo para establecer el valor a un bloque de su posicion y contenido
 * 
 * @param value
 * @param row
 * @param col
 */
    public void setBlockValue(String value, int row, int col) {
        this.getBlocks()[col][row].setValue(value);
    }
/**
 * Metodo para obtener un bloque del laberinto con su fila y columna
 * 
 * @param row
 * @param col
 * @return devuelve los bloques sus posiciones
 */
    public String getBlockValue(int row, int col) {
        return this.getBlocks()[row][col].getValue();
    }
/**
 * Metodo para obtener el tamaño del laberinto
 * 
 * @return devuelve el tamaño
 */
    public Size getSize() {
        return size;
    }
/**
 * Establece el valor del tamaño del laberinto
 * 
 * @param size
 */
    public void setSize(Size size) {
        this.size = size;
    }
/**
 * Devuelve el valor del tiempo para el laberinto
 * 
 * @return devuelve el contenido del valor time
 */
    public double getTime() {
        return time;
    }
/**
 * Establece el valor del tiempo para el laberinto
 * 
 * @param time
 */
    public void setTime(double time) {
        this.time = time;
    }
/**
 * Obtiene el sonido del laberinto
 * 
 * @return devuelve el sonido del laberinto
 */
    public String getSound() {
        return sound;
    }
/**
 * Establece el sonido para el laberinto
 * 
 * @param sound
 */
    public void setSound(String sound) {
        this.sound = sound;
    }
/**
 * Obtiene los bloques del laberinto
 * 
 * @return devuelve un array de bloques
 */
    public Block[][] getBlocks() {
        return blocks;
    }
/**
 * Establece los bloques del laberinto
 * 
 * @param blocks
 */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }
/**
 * Metodo para cargar el laberinto que se mostrara en pantalla
 * 
 * @param file
 * @return devuelve un laberinto
 * @throws JAXBException
 * @throws IOException
 * @throws FileNotFoundException
 * @throws ClassNotFoundException
 * @throws Exception
 */
    public static Maze load(File file) throws JAXBException, IOException, FileNotFoundException, ClassNotFoundException, Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Maze m = null;
        if (extension.equals("xml")) {
            m = Maze.loadXML(file);
        } else {
            if (extension.equals("json")) {
                m = Maze.loadJSON(file);

            } else {
                if (extension.equals("bin")) {
                    m = Maze.loadBin(file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
        return m;

    }
/**
 * Metodo para guardar un laberinto en el equipo
 * 
 * @param maze
 * @param file
 * @throws JAXBException
 * @throws Exception
 */
    public static void save(Maze maze, File file) throws JAXBException, Exception {
        if (maze.sound == null || maze.sound.equals("")) {
            throw new Exception("Es necesario indicar el sonido del laberinto");
        }
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            Maze.saveXML(maze, file);
        } else {
            if (extension.equals("json")) {
                Maze.saveJSON(maze, file);

            } else {
                if (extension.equals("bin")) {
                    Maze.saveBin(maze, file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
    }
/**
 * Metodo para cargar un laberinto guardado en JSON
 * 
 * @param file
 * @return devuelve un laberinto
 * @throws FileNotFoundException
 * @throws IOException
 */
    private static Maze loadJSON(File file) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Reader reader;
        reader = Files.newBufferedReader(file.toPath());
        Maze m = gson.fromJson(reader, Maze.class);
        reader.close();
        return m;
    }
/**
 * Metodo para cargar un laberinto guardado en XML
 * 
 * @param file
 * @return devuelve un laberinto
 * @throws JAXBException
 * @throws IOException
 */
    private static Maze loadXML(File file) throws JAXBException, IOException {

           JAXBContext context = JAXBContext.newInstance(Maze.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Maze maze = (Maze) unmarshaller.unmarshal(
                        file);
                return maze;
          
    }
/**
 * Metodo cargar los laberintos que se importen a la aplicacion
 * 
 * @param file
 * @return devuelve un laberinto
 * @throws FileNotFoundException
 * @throws IOException
 * @throws ClassNotFoundException
 */
    public static Maze loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream os = new FileInputStream(file);
        
        ObjectInputStream oos = new ObjectInputStream(os);
        Maze m = (Maze) oos.readObject();
        oos.close();;
        os.close();
        return m;
    }
/**
 * Metodo para guardar el laberinto en JSON
 * 
 * @param maze
 * @param file
 * @throws FileNotFoundException
 * @throws UnsupportedEncodingException
 */
    private static void saveJSON(Maze maze, File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(maze);
        java.io.PrintWriter pw = new PrintWriter(file, "UTF-8");
        pw.print(json);
        pw.close();
    }
/**
 * Metodo para guardar el laberinto en XML
 * 
 * @param maze
 * @param file
 * @throws JAXBException
 * @throws IOException
 */
    private static void saveXML(Maze maze, File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(maze.getClass());
        // create an instance of `Marshaller`
        Marshaller marshaller = context.createMarshaller();
        // enable pretty-print XML output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // write XML to `StringWriter`
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);//(file, "UTF-8");
        marshaller.marshal(maze, fw);
        fw.close();

    }
/**
 * Metodo para guardar el laberinto
 * 
 * @param maze
 * @param file
 * @throws FileNotFoundException
 * @throws IOException
 */
    public static void saveBin(Maze maze, File file) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(maze);
        oos.close();;
        os.close();
    }

}
