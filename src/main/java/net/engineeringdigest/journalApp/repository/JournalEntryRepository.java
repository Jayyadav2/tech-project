package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JouranlEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JouranlEntry, String>  {
}
