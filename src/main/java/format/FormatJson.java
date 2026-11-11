package format;


import mediatheque.Document;

import java.util.List;

public class FormatJson implements FormatRapport {

    @Override
    public String formater(List<Document> docs) {

        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < docs.size(); i++) {

            Document d = docs.get(i);

            String id = echapper(String.valueOf(d.getId()));
            String titre = echapper(d.getTitre());

            sb.append("  {\"id\": \"").append(id)
                    .append("\", \"titre\": \"").append(titre)
                    .append("\"}");

            if (i < docs.size() - 1) {
                sb.append(',');
            }

            sb.append('\n');
        }

        return sb.append("]").toString();
    }

    private String echapper(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
