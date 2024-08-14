package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JouranlEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JouranlEntry jouranlEntry, String userName){
        User user=userService.findByUserName(userName);
       JouranlEntry saved= journalEntryRepository.save(jouranlEntry);
       user.getJouranlEntries().add(saved);
       userService.saveEntry(user);
    }
    public List<JouranlEntry> getEntry() {
        List<JouranlEntry> list = journalEntryRepository.findAll();
        return list;
    }
    public Optional<JouranlEntry> findById(String myId){
        return  journalEntryRepository.findById(myId);

    }

    @Transactional
    public  void deleteById(String myId, String userName){
        User user=userService.findByUserName(userName);
        user.getJouranlEntries().removeIf(x ->x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }
    public void updateById(String myId){


    }
}
