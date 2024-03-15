import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        System.out.print("Input path: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        try {
            savelistDirectoryTree(path);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listFiles(File directory , Element parentElement, Document doc){
        for(File file : directory.listFiles()){
            if(file.isDirectory()){
                Element element = doc.createElement(file.getName());
                parentElement.appendChild(element);
                listFiles(file, element,doc );
            }else{
                Element element = doc.createElement("file");
                element.appendChild(doc.createTextNode(file.getName()));
                parentElement.appendChild(element);
            }
        }

    }
    public static void savelistDirectoryTree(String path) throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        File file = new File(path);
        if(file.exists()){
            try {
                // Tạo root element
                Element rootElement = doc.createElement("DirectoryTree");
                doc.appendChild(rootElement);

                listFiles(file, rootElement, doc);

                //Ghi ra file XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("directory_structure.xml"));

                transformer.transform(source, result);
                System.out.println("File XML đã được tạo thành công.");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Thư mục không tồn tại!");
        }
    }
}
