package de.philipphauer.musicianservice.rest;

import com.google.common.collect.Lists;
import de.philipphauer.musicianservice.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicianController {

    @Autowired
    private MusicianRepository repo;

    @RequestMapping("/")
    private List<MusicianResponse> getAllMusicians(@RequestParam(value="name", required=false) String name) {
        System.out.println(name);
        List<MusicianResponse> musicians = Lists.newArrayList(new MusicianResponse("Die Ärzte"), new MusicianResponse("Alligatoah"));
        return musicians;
    }

}