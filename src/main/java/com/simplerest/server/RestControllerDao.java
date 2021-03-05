package com.simplerest.server;

import com.simplerest.server.models.*;
import com.simplerest.server.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dao")
public class RestControllerDao {

    @Autowired
    private BokService bokService;
    @Autowired
    private ForfatterService forfatterService;
    @Autowired
    private AdresseService adresseService;
    private Logger logger = LoggerFactory.getLogger(RestControllerDao.class);

    /**
     * Legg til forfatter
     *
     * @param forfatter (fodt_ar, fornavn, etternavn, adresse, bokSet)
     * @return forfatter
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/forfatter") //?adresseId=2
    public Forfatter leggTilForfatter(@RequestBody Forfatter forfatter) {
        logger.info("Forfatter lagt til "+forfatter);
        return forfatterService.save(forfatter);
    }

    /**
     * Legg til adresse
     *
     * @param adresse (gateadresse, hus_nr, post_nr, land, by)
     * @return adresse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/adresse")
    public Adresse leggTilAdresse(@RequestBody Adresse adresse) {
        logger.info("Ny adresse lagt til: " + adresse.toString());
        return adresseService.save(adresse);
    }

    /**
     * Legg til bok
     *
     * @param bok Bok(ar, tittel, forfatterSet)
     * @return bok
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bok") //?forfatterIds=[1,2,3]
    public Bok leggTilBok(@RequestBody Bok bok) {
        logger.info("Bok lagt til "+bok);
        return bokService.save(bok);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/forfatter") //?adresseId=0
    public Forfatter oppdaterForfatter(@RequestBody Forfatter forfatter) {
        return forfatterService.update(forfatter);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/adresse")
    public Adresse oppdaterAdresse(@RequestBody Adresse adresse) {
        return adresseService.update(adresse);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/bok") //?forfatterIds=1,2,3
    public Bok oppdaterBok(@RequestBody Bok bok) {
        return bokService.update(bok);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/bok/{id}")
    public void deleteBok(@PathVariable("id") int id) {
        bokService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/forfatter/{id}")
    public void deleteForfatter(@PathVariable("id") int id) {
        forfatterService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/adresse/{id}")
    public void deleteAdresse(@PathVariable("id") int id) {
        adresseService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/forfatter/{lastname}")
    public List<Forfatter> getForfattere(@PathVariable("lastname") String lastname) {
        logger.info("A search for forfatters on lastname:{" + lastname + "}");
        return forfatterService.findByLastname(lastname);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bok/{tittel}")
    public Bok getBokerOnTittel(@PathVariable("tittel") String tittel) {
        logger.info("A search for bok on tittel:{" + tittel + "}");
        return bokService.findByTitle(tittel);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bok/forfatter/{forfatter_lastname}")
    public List<List<Bok>> getBokerOnForfatter(@PathVariable("forfatter_lastname") String lastname) {
        List<Forfatter> forfattersFound = forfatterService.findByLastname(lastname);
        List<List<Bok>> results = new ArrayList<>();
        for (Forfatter f : forfattersFound
        ) {
            results.add(f.getBokList());
        }
        return results;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/forfatter")
    public List<Forfatter> getForfattere() {
        logger.info("/GET forfatter");
        return forfatterService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/adresse")
    public List<Adresse> getAdresser() {
        logger.info("/GET adresse");
        return adresseService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bok")
    public List<Bok> getBoker() {
        logger.info("/GET bok");
        return bokService.getAll();
    }

}
