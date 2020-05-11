package com.AMRA.x00366519;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.swing.*;
import java.util.Date;
import java.io.*;

public class PDF {

    /*private String nombre, tomo;
    private Date fecha;
    private int numero;

    public PDF(String nombre, String tomo, Date fecha, int numero) {
        this.nombre = nombre;
        this.tomo = tomo;
        this.fecha = fecha;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
   */

    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;

    private String Text;
    private String filePath;
    private File file;

    public PDF() {

    }

    public String toText() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        file = new File(filePath);
        //parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        Text = pdfStripper.getText(pdDoc);
        return Text;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PDDocument getPdDoc() {
        return pdDoc;
    }

    public String getDirectory() {
        String ruta = "";
        // componente de dialogo
        JFileChooser jfc = new JFileChooser();
        // solo son directorios
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //mandamos el cuadro, esperamos una respuesta aprobatoria
        int dialogResult = jfc.showOpenDialog(null);
        if (dialogResult == JFileChooser.APPROVE_OPTION) {
            ruta = jfc.getSelectedFile().getAbsolutePath();
        }
        return ruta;
    }

    public void readPDF(String directory, String label) {
        File directoryFile = new File(directory); // carpeta donde estan los pdf
        String[] listFiles = directoryFile.list();//extrae los nombres de archivo
        String slash = System.getProperty("file.separator");
        if (listFiles.length == 0)
            JOptionPane.showMessageDialog(null, "No hay archivos en la carpeta especificada");

        else {
            //creamos un flujo donde se pondran los nombres de los archivos que contienen los elementos
            PrintWriter pw = null;
            try {
                pw = new PrintWriter("destination.txt");
                //recorro cada uno de los elementos
                for (String file : listFiles) {
                    //preguntamos si es un pdf
                    if (!file.toLowerCase().endsWith(".pdf"))
                        continue;
                    //carga el archivo
                    PDDocument pdf = PDDocument.load(new File
                            (directory + slash + file));
                    //extramos su contenido
                    String content = new PDFTextStripper()
                            .getText(pdf);
                    String text = pdfStripper.getText(pdf);
                    System.out.println(text);
                    //cerramos el archivo
                    pdf.close();
                    //preguntamos si existe lo que se quiere buscar
                    if (content.contains(label)) {
                        //escribimos en el archivo de destino
                        pw.println(file);
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Hubo un error al abrir el archivo");
            }
        }
    }
}



