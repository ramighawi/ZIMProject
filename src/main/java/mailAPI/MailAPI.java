package mailAPI;

import javax.mail.*;
import javax.mail.search.*;
import java.util.Properties;

public class MailAPI {

    //search specific mail from inbox folder with given subject and body
    public void searchSpecificEmail(Folder folder, String subject, String body)
    {
        System.out.println("=====SearchEmail======");
        try{
            SearchTerm searchTerm = new AndTerm(new SubjectTerm(subject), new BodyTerm(body));
            Message[] messages = folder.search(searchTerm);
            System.out.println("Message count: " + messages.length);
            Message msg = messages[0];
            PrintMsg(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //search specific mail from inbox folder with given subject and body, seen or not seen, recent or not recent
     public void searchEmailWithSeenRecent(Folder folder, String subject, String body, boolean seen, boolean recent)
    {
        System.out.println("=====SearchEmail with seen and recent======");
        try{
            SearchTerm[] srchTerms = new SearchTerm[4];
            srchTerms[0] = new SubjectTerm(subject);
            srchTerms[1] = new BodyTerm(body);
            srchTerms[2] = new FlagTerm(new Flags(Flags.Flag.SEEN), seen);
            srchTerms[3] = new FlagTerm(new Flags(Flags.Flag.RECENT), recent);

            SearchTerm searchTerm = new AndTerm(srchTerms);
            Message[] messages = folder.search(searchTerm);
            System.out.println("Message count: " + messages.length);
            Message msg = messages[0];
            PrintMsg(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //connect to gmail account and get return inbox folder
    public Folder connectToGmailInboxFolder(String user, String password) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
        store.connect("imap.gmail.com", user, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        return inbox;

    }

    //get the latest message from inbox folder
    public void getLatestMessage(Folder folder)
    {
        System.out.println("=====GetLatestMessage======");
        try
        {
            System.out.println("Message count: " + folder.getMessageCount());
            Message msg = folder.getMessage(folder.getMessageCount());
            PrintMsg(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //print given message
    public static void PrintMsg(Message msg)
    {
        try
        {
            Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
            }
            Multipart mp = (Multipart) msg.getContent();
            int count = mp.getCount();
            System.out.println("body count: "+count);
            for (int i = 0; i < count; i++)
            {
                System.out.println("===========Body no. " + i);
                BodyPart bp = mp.getBodyPart(i);
                System.out.println("SENT DATE:" + msg.getSentDate());
                System.out.println("SUBJECT:" + msg.getSubject());
                System.out.println("CONTENT:" + bp.getContent());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
