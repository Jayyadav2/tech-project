package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JouranlEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JouranlEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JouranlEntry> getAll() {

        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public void createEntry(@RequestBody JouranlEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);

    }

    @GetMapping("id/{myId}")
    public JouranlEntry getJournalEntryById(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JouranlEntry deleteJournalEntryById(@PathVariable Long myId) {
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{id}")
    public JouranlEntry updateJournalById(@PathVariable Long id, @RequestBody JouranlEntry myEntry) {
        return journalEntries.put(id, myEntry);
    }

}
