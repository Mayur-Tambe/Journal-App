package com.mayur.journalApp.cache;

import com.mayur.journalApp.entity.ConfigJournalApp;
import com.mayur.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;
    public Map<String, String> appCache;

    @PostConstruct // this method will execute evertime immediately after the bean is created.
    public void init(){
        appCache = new HashMap<>();
        List <ConfigJournalApp> all = configJournalAppRepository.findAll();
        for(ConfigJournalApp configJournalApp: all){
            appCache.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
