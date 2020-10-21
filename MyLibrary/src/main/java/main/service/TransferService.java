package main.service;

import main.model.Book;
import main.model.User;
import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinished;
import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinishedKey;
import main.model.auxiliaryEntities.BookWhichUserIsReading.BookWhichUserIsReading;
import main.model.auxiliaryEntities.BookWhichUserIsReading.BookWhichUserIsReadingKey;
import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToRead;
import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToReadKey;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.auxiliaryRepository.BookWhichUserFinishedRepository;
import main.repository.auxiliaryRepository.BookWhichUserIsReadingRepository;
import main.repository.auxiliaryRepository.BookWhichUserWantToReadRepository;
import main.repository.auxiliaryRepository.UsersBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UsersBookRepository usersBookRepository;
    @Autowired
    private BookWhichUserIsReadingRepository bookWhichUserIsReadingRepository;
    @Autowired
    private BookWhichUserFinishedRepository bookWhichUserFinishedRepository;
    @Autowired
    private BookWhichUserWantToReadRepository bookWhichUserWantToReadRepository;

    @Transactional
    public void transferToAnotherList(Book book, User user, String oldList, String newList){
        removeFromList(book, user, oldList);
        saveToList(book, user, newList);
    }

    private void saveToList(Book book, User user, String newList){
        switch (newList){
            case "finished":
                bookWhichUserFinishedRepository.save(new BookWhichUserFinished(new BookWhichUserFinishedKey(book, user)));
                break;
            case "isReading":
                bookWhichUserIsReadingRepository.save(new BookWhichUserIsReading(new BookWhichUserIsReadingKey(book, user)));
                break;
            case "wantToRead":
                bookWhichUserWantToReadRepository.save(new BookWhichUserWantToRead(new BookWhichUserWantToReadKey(book, user)));
                break;
        }
    }

    public void removeFromList(Book book, User user, String oldList){
        switch(oldList){
            case "finished":
                bookWhichUserFinishedRepository.delete(new BookWhichUserFinished(new BookWhichUserFinishedKey(book, user)));
                break;
            case "isReading":
                bookWhichUserIsReadingRepository.delete(new BookWhichUserIsReading(new BookWhichUserIsReadingKey(book, user)));
                break;
            case "wantToRead":
                bookWhichUserWantToReadRepository.delete(new BookWhichUserWantToRead(new BookWhichUserWantToReadKey(book, user)));
                break;
            default:
                break;
        }
    }
}
