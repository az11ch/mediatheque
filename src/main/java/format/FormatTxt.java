package format;

import mediatheque.Document;

import java.util.List;

public class FormatTxt implements FormatRapport {
    @Override
    public String formater(List<Document> docs) {
        StringBuilder sb = new StringBuilder();
        for (Document d : docs) {
            sb.append("-").append(d.descriptionCourte()).append('\n');
        }
        return sb.toString();
    }
}
