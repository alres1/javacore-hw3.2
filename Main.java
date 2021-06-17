package hwCore32;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(30,10,12,5.5);
        GameProgress game2 = new GameProgress(55,9,13,45.1);
        GameProgress game3 = new GameProgress(9,17,14,4.8);

        if(game1.saveGame("C:/testfiles/games/savegames/save1.dat")){
            System.out.println("Сохранен успешно!");
        }
        if(game2.saveGame("C:/testfiles/games/savegames/save2.dat")){
            System.out.println("Сохранен успешно!");
        }
        if(game3.saveGame("C:/testfiles/games/savegames/save3.dat")){
            System.out.println("Сохранен успешно!");
        }

        List<String> savedfiles = Arrays.asList("C:/testfiles/games/savegames/save1.dat",
                "C:/testfiles/games/savegames/save2.dat", "C:/testfiles/games/savegames/save3.dat");

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:/testfiles/games/savegames/zip.zip"))) {
            for (String str: savedfiles) {
                FileInputStream fis = new FileInputStream(str);
                File file = new File(str);
                zout.putNextEntry(new ZipEntry(file.getName()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                if(file.delete())
                    System.out.println("Удален успешно!");
            }
            System.out.println("Запакован успешно!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
