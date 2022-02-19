package mailAPI;

import utilities.Utilities;

import javax.mail.Folder;
import javax.mail.MessagingException;
import java.io.IOException;

public class MainMailAPI {

    public static void main(String args[]) throws IOException, MessagingException {

        MailAPI ma = new MailAPI();

        //connect to gmail account and get the inbox folder
        Folder inbox=ma.connectToGmailInboxFolder(Utilities.getDataXML("User"), Utilities.getDataXML("Password"));


        //get the latest message from inbox folder
        try {
            ma.getLatestMessage(inbox);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //search specific mail from inbox folder with given subject and body
        try {
            ma.searchSpecificEmail(inbox,"Security alert","App password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //search specific mail from inbox folder with given subject and body, seen or not seen, recent or not recent
        try {
            ma.searchEmailWithSeenRecent(inbox,"Critical security alert","less secure",true,false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}