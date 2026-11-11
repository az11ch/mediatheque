package smtp;


import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class ServeurSmtp {

    private final String expediteur;
    private final String motDePasse;

    // Constructeur
    public ServeurSmtp(String expediteur, String motDePasse) {
        this.expediteur = expediteur;
        this.motDePasse = motDePasse;
    }

    // Méthode pour envoyer un email
    public void envoyer(String destinataire, String message) {

        // Configuration du serveur SMTP
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");

        // Création de la session SMTP
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                expediteur,
                                motDePasse
                        );
                    }
                });

        try {
            // Création du message
            Message email = new MimeMessage(session);

            // Expéditeur
            email.setFrom(new InternetAddress(expediteur));

            // Destinataire
            email.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinataire)
            );

            // Sujet
            email.setSubject("Notification");

            // Contenu
            email.setText(message);

            // Envoi de l'email
            Transport.send(email);

            System.out.println("Email envoyé avec succès !");

        } catch (MessagingException e) {

            System.out.println("Erreur lors de l'envoi de l'email :");
            e.printStackTrace();
        }
    }
}
