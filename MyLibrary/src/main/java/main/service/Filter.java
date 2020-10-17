package main.service;

import main.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Filter {

    public static List<Book> filtration(String title, String author, List<Book> incomingList){
        List<Book> outgoingList = new ArrayList<>();
        outgoingList.addAll(incomingList);

        if(title != null && !title.isEmpty()){
            for(Book book : incomingList){
                if(!book.getTitle().equals(title)){
                    outgoingList.remove(book);
                }
            }
        }

        if(author != null && !author.isEmpty()){
            for(Book book : incomingList){
                if(!book.getAuthor().equals(author)){
                    outgoingList.remove(book);
                }
            }
        }
        return outgoingList;
    }
}
