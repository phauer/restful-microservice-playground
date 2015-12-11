package de.philipphauer.musicianservice.rest;

import de.philipphauer.musicianservice.springDataMongo.Band;
import de.philipphauer.musicianservice.springDataMongo.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicianController {

    @Autowired
    private BandRepository bandRepo;

    @RequestMapping("/bands")
    private List<Band> getAllBands(@RequestParam(value="startingWith", required=false) String prefix) {
        if (prefix == null){
            List<Band> bands = bandRepo.findAll();
            return bands;
        }
        List<Band> bands = bandRepo.findStartingWith(prefix);
        return bands;
    }

    @RequestMapping(value = "/bands", method = RequestMethod.POST)
    private void createBand() {
        bandRepo.save(new Band("Ärzte", 1982));
    }


}