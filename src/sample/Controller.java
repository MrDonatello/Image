package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextArea text;

    @FXML
    private Button getInfo;

    @FXML
    private TextField linkField;

    @FXML
    void initialize() {
        getInfo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                String mainUrl = linkField.getText();

                Document doc = null;
                try {
                    doc = Jsoup.connect(mainUrl).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert doc != null;
                Elements media = doc.select("[src]");

                ArrayList<ImageInfo> imageInfo = new ArrayList<>();
                for (Element src : media) {
                    if (src.normalName().equals("img")) {
                        String url = src.attr("abs:src");
                        String width = src.attr("width");
                        String height = src.attr("height");

                        if (width.equals("") || height.equals("")) {
                            BufferedImage img = null;
                            try {
                                img = ImageIO.read(new URL(httpToHttps(src.attr("abs:src"))));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            assert img != null;
                            width = String.valueOf(img.getWidth());
                            height = String.valueOf(img.getHeight());
                        }
                        imageInfo.add(new ImageInfo(url, width, height));
                    }
                }
                Controller.this.print(imageInfo);
            }
        });
    }

    private static String httpToHttps(String s) {
        if (s.toCharArray()[4] != 's') {
            return s.substring(0, 4) + "s" + s.substring(4);
        }
        return s;
    }

    private void print(ArrayList<ImageInfo> list) {
        List<String> res = new ArrayList<String>();
        for (ImageInfo img : list) {
            //System.out.println("Url: " + img.getUrl() + "  " + img.getWidth() + "x" + img.getHeight() + " " + "Size: " + img.getSize());
            res.add("Url: " + "" + img.getUrl() + "  " + img.getWidth() + " x " + img.getHeight() + "  Size: " + img.getSize() + "\n");
        }
        text.setText(res.toString());
    }
}
