package com.selenium.pages;

import com.selenium.test.common.POP3Client;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.yandex.qatools.allure.annotations.Step;

import javax.mail.*;
import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.selenium.test.common.Constants.EMAIL;
import static com.selenium.test.common.Constants.EMAIL_PASSWORD;

public class EmailSteps {
    private POP3Client client;
    private Folder folder;
    private Message[] messages;

    /**
     * Раскрытие списка сообщений
     * @param folder_name название директории со списком сообщений
     * @throws MessagingException
     */
    public void openFolder(String folder_name) throws MessagingException {
        if(folder == null){
            folder = client.openFolder(folder_name, Folder.READ_WRITE);
        } else {
            if(folder.isOpen()){
                if(!folder.getName().equals(folder_name)){
                    folder.close(true);
                    folder = client.openFolder(folder_name,Folder.READ_WRITE);
                } else {
                    folder.open(Folder.READ_WRITE);
                }
            } else {
                folder = client.openFolder(folder_name,Folder.READ_WRITE);
            }
        }
    }

    /**
     * Получение полного списка писем
     * @throws MessagingException
     */
    public void getMessagesList() throws MessagingException{
        folder.close(true);
        folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
    }

    /**
     * Получение заданного сообщения по номеру
     * @param id номер сообщения
     * @return сообщение
     * @throws MessagingException
     */
    public Message getMessageByID(int id) throws MessagingException{
        return folder.getMessage(id);
    }

    /**
     * Получение кол-ва сообщений
     * @return возвращение числа сообщений (-1 если нет подключения)
     * @throws MessagingException
     */
    public int getMessagesCount() throws MessagingException{
        return folder.getMessageCount();
    }

    /**
     * Поиск сообщения с заданной темой
     * @param search
     * @return номер сообщения
     * @throws MessagingException
     */
    public int searchMessageBySubject(String search) throws MessagingException{
        if(messages == null)
            getMessagesList();
        for (int i = 0; i < messages.length; i++){
            String subject = messages[i].getSubject();
            if (subject != null) {
                if(subject.equals(search))
                    return i+1;
            }
        }

        return -1;
    }

    /**
     * Удаление всех email-сообщений в почтовом ящике
     * @throws MessagingException
     */
    public void deleteAllMessages() throws MessagingException{
        while(folder.getMessageCount() > 0){
            Flags flags = new Flags();
            flags.add(Flags.Flag.DELETED);
            folder.setFlags(1,
                    (folder.getMessageCount()>500) ? 500 : folder.getMessageCount(),
                    flags, true);
            folder.close(true);
            folder.open(Folder.READ_WRITE);
        }
    }

    /**
     * Выделение части письма заданного типа
     * @param mime_type тип выделяемой части
     * @return выделенная часть
     * @throws MessagingException
     * @throws IOException
     */
    public Part getMessagePartByType(Part p, String mime_type) throws MessagingException, IOException {
        if(p.isMimeType(mime_type)){
            return p;
        }
        if(p.isMimeType("multipart/*")){
            Multipart mp = (Multipart)p.getContent();
            for(int i1 = 0; i1 < mp.getCount(); i1++){
                Part subpart = mp.getBodyPart(i1);
                p = getMessagePartByType(subpart,mime_type);
                if(p != null){
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Очистка почтового ящика
     */
    @Step("Clear emailbox before enquiry")
    public EmailSteps clearEmail()  {
        try{
            client = new POP3Client(EMAIL, EMAIL_PASSWORD);
            openFolder("INBOX");
            deleteAllMessages();
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new AssertionError("Ошибка подключения к почтовому серверу");
        }
        return this;
    }

    @Step("Email: Close this service and terminate connection")
    public void closeConnection(){
        client.closeStore();
    }

    @Step("Check email Enquiry and go to status enquiry in profile")
    public EmailSteps checkEmailEnquiry(String adviser, String firstName){
        int msg_id = -1;
        try{
            for(int seconds=0;;seconds++){
                if(seconds>15) {
                    throw new AssertionError("Сообщение не получено в течении 5 минут");
                }
                msg_id = searchMessageBySubject("Your enquiry with " + adviser);
                if(msg_id > 0){
                    break;
                }
                Thread.sleep(20000);
                getMessagesList();
            }
            Message message = getMessageByID(msg_id);
            Part p = getMessagePartByType(message,"text/html");
            String content = (String)p.getContent();
            content = Jsoup.parse(content).text();

            String body = "Dear "+firstName+", Thank you for using unbiased.co.uk. We have sent your enquiry to test" +
                    " consumer 2507 and they should be in touch shortly. You can track the status of your" +
                    " enquiry at any time by logging into Unbiased." +
                    " Check my enquiry status   We’ll be in touch again soon." +
                    " The Unbiased team Connect with us: Registered Office: Unbiased Ltd, 12-14 Berry St, London, EC1V 0AU" +
                    " Please do not respond to this email. If you have any queries please email contact@unbiased.co.uk.";
            if(content.indexOf(body) == -1){
                throw new AssertionError("Incorrect content of email:" + body);
            }

            content = (String)p.getContent();
            Document doc = Jsoup.parse(content);
            Elements links = doc.select("a[href]");
            String test = links.get(1).attr("abs:href");
            open(test);
        } catch (MessagingException | InterruptedException | IOException e) {
            e.printStackTrace();
            throw new AssertionError("Exception: reading email!");
        }
        return this;
    }
}
