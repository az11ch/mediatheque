package format;

import mediatheque.Document;

import java.util.List;

public class FormatCsv implements FormatRapport {
    @Override
    public String formater(List<Document> docs) {
        StringBuilder sb = new StringBuilder();
        for (Document d : docs) {
            sb.append(d.getTitre()).append(';').append(d.getId()).append('\n');
        }
        return sb.toString();
    }
}
