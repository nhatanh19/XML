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
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2 {
    private static ArrayList<SinhVien> listSV;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<SinhVien> sv = addSinhVien();
        OutFileXml(sv);
    }

    public static ArrayList<SinhVien> addSinhVien(){
        listSV = new ArrayList<>();
        boolean check = true;
        while(check == true){
            System.out.print("Họ và tên: ");
            String ten = sc.nextLine();
            System.out.print("Tuổi: ");
            int tuoi = sc.nextInt();
            sc.nextLine();
            System.out.print("GPA: ");
            String gpa = sc.nextLine();
            SinhVien sv = new SinhVien(ten, tuoi, gpa);
            listSV.add(sv);
            System.out.print("Tiếp tục (1.Đúng/2.Không): ");
            int ktra = sc.nextInt();
            sc.nextLine();
            if(ktra != 1){
                check = false;
            }
        }
        return listSV;
    }

    public static void OutFileXml(ArrayList<SinhVien> listSV){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("DanhSachSinhVien");
            doc.appendChild(rootElement);
            int count = 0;
            for(SinhVien sv : listSV){
                count++;
                Element sinhVien = doc.createElement("Sinh_vien");
                sinhVien.setAttribute("STT", String.valueOf(count));
                rootElement.appendChild(sinhVien);

                Element ten = doc.createElement("Ho_va_ten");
                ten.appendChild(doc.createTextNode(sv.getTen()));
                sinhVien.appendChild(ten);

                Element tuoi = doc.createElement("Tuoi");
                tuoi.appendChild(doc.createTextNode(String.valueOf(sv.getTuoi())));
                sinhVien.appendChild(tuoi);

                Element gpa = doc.createElement("GPA");
                gpa.appendChild(doc.createTextNode(sv.getGpa()));
                sinhVien.appendChild(gpa);
            }
            //Ghi ra file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Sinh_vien.xml"));

            transformer.transform(source, result);
            System.out.println("File XML đã được tạo thành công.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
