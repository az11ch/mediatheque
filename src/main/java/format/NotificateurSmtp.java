package format;

import smtp.ServeurSmtp;

public class NotificateurSmtp implements Notificateur {
    private final ServeurSmtp smtp;
    private final String destinataire;

    public NotificateurSmtp(ServeurSmtp smtp, String destinataire) {
        this.smtp = smtp;
        this.destinataire = destinataire;
    }

    @Override
    public void notifier(String message) {
        smtp.envoyer(destinataire, message);
    }
}