package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JouranlEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JouranlEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User byUserName= userService.findByUserName(userName);
      List<JouranlEntry> list= byUserName.getJouranlEntries();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JouranlEntry> createEntry(@RequestBody JouranlEntry myEntry, @PathVariable String userName) {

        try {

            journalEntryService.saveEntry(myEntry, userName);
            return  new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JouranlEntry> getJournalEntryById(@PathVariable String myId) {
        Optional<JouranlEntry> jouran= journalEntryService.findById(myId);
        if(jouran.isPresent()){
            return new ResponseEntity<>(jouran.get(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}/{userName}")
    public ResponseEntity<JouranlEntry> deleteJournalEntryById(@PathVariable String myId,@PathVariable String userName) {
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public JouranlEntry updateJournalById(@PathVariable String id, @RequestBody JouranlEntry myEntry) {
        return null;
    }


}
