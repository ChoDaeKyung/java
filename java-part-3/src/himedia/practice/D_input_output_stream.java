package himedia.practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class D_input_output_stream {
    private String desktopPath;
    private String folderPath;
    private Path myFolder;
    private String Name;

    public D_input_output_stream() {
        this.Name = "";
        this.desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        this.folderPath = desktopPath + File.separator + Name;
        this.myFolder = Paths.get(folderPath);
    }

    public void setName(String name){
        this.Name = name;
        this.folderPath = desktopPath + File.separator + Name;
        this.myFolder = Paths.get(folderPath);
    }

    public void test1(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("파일명을 입력해주세요 : ");
        String fileName = scanner.nextLine();

        setName(fileName);

        try {
            if ( Files.notExists(myFolder) ) {
                Files.createDirectory(myFolder);
                System.out.println("테스트 폴더가 생성되었습니다.");
            } else {
                System.out.println("폴더가 이미 존재합니다.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        D_input_output_stream d = new D_input_output_stream();
        d.test1();
    }

}
