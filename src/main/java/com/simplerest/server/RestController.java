package com.simplerest.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    List<Forfatter> forfatterList = new ArrayList<>();
    List<Adresse> adresseList = new ArrayList<>();
    List<Bok> bokList = new ArrayList<>();
    static int forfatterIdCounter = 0;
    static int bokIdCounter = 0;
    static int adresseIdCounter = 0;
    Logger logger = LoggerFactory.getLogger(RestController.class);

    /**
     * Legg til forfatter, adresse må eksistere først
     *
     * @param forfatter (fodt_ar, fornavn, etternavn)
     * @param adresseId integer
     * @return forfatter
     */
    @PostMapping("/leggTilForfatter") //?adresseId=2
    public Forfatter leggTilForfatter(@RequestBody Forfatter forfatter, @RequestParam("adresseId") int adresseId) {
        Adresse adr = findAdresse(adresseId);
        if (adr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "bad req");
        forfatter.setId(forfatterIdCounter);
        forfatter.setAdresse(adr);
        forfatterIdCounter++;
        forfatterList.add(forfatter);
        logger.info("Ny forfatter lagt til: " + forfatter.toString());
        return forfatter;
    }

    /**
     * Legg til adresse
     *
     * @param adresse (gateadresse, hus_nr, post_nr, land, by)
     * @return adresse
     */
    @PostMapping("/leggTilAdresse")
    public Adresse leggTilAdresse(@RequestBody Adresse adresse) {
        adresse.id = adresseIdCounter;
        adresseIdCounter++;
        adresseList.add(adresse);
        logger.info("Ny adresse lagt til: " + adresse.toString());
        return adresse;
    }

    /**
     * Legg til bok, forfattere må eksistere først
     *
     * @param bok          Bok(ar, tittel)
     * @param forfatterIds forfatter id liste
     * @return bok
     */
    @PostMapping("/leggTilBok") //?forfatterIds=[1,2,3]
    public Bok leggTilBok(@RequestBody Bok bok, @RequestParam("forfatterIds") List<Integer> forfatterIds) {
        addForfatters(bok, forfatterIds);
        bok.id = bokIdCounter;
        bokIdCounter++;
        bokList.add(bok);
        logger.info("Ny bok lagt til: " + bok.toString());
        return bok;
    }

    @PutMapping("/oppdaterForfatter/{id}") //?adresseId=0
    public Forfatter oppdaterForfatter(@RequestBody Forfatter forfatter, @PathVariable("id") int id, @RequestParam("adresseId") int adrId) {
        Adresse adr = findAdresse(adrId);
        if (adr == null) {
            logger.error("adresse not found for forfatter update");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        forfatter.setAdresse(adr);
        forfatter.setId(id);
        //O(n)
        for (int i = 0; i < forfatterList.size(); i++) {
            if (forfatterList.get(i).getId() == id) {
                forfatterList.set(i, forfatter);
                break;
            }
        }
        //cascade O(nm)
        for (int i = 0; i < bokList.size(); i++) {
            for (int j = 0; j < bokList.get(i).getForfattere().size(); j++) {
                if (bokList.get(i).getForfattere().get(j).getId() == id) {
                    bokList.get(i).getForfattere().set(j, forfatter);
                }
            }
        }
        logger.info(forfatter + " is updated");
        return forfatter;
    }

    @PutMapping("/oppdaterAdresse/{id}")
    public Adresse oppdaterAdresse(@RequestBody Adresse adresse, @PathVariable("id") int id) {
        // update address list
        for (int i = 0; i < adresseList.size(); i++) {
            if (adresseList.get(i).getId() == id) {
                adresseList.set(i, adresse);
                break;
            }
        }
        //cascade for all forfatters, which also handles all books
        for (int i = 0; i < forfatterList.size(); i++) {
            if (forfatterList.get(i).getAdresse().id == id) {
                oppdaterForfatter(forfatterList.get(i), forfatterList.get(i).getId(), id);
                break; //assuming adresses are unique
            }
        }
        logger.info(adresse + " is updated");
        return adresse;
    }

    @PutMapping("/oppdaterBok/{id}") //?forfatterIds=1,2,3
    public Bok oppdaterBok(@RequestBody Bok bok, @PathVariable("id") int id, @RequestParam("forfatterIds") List<Integer> forfatterIds) {
        addForfatters(bok, forfatterIds);
        bok.setId(id);
        for (int i = 0; i < bokList.size(); i++) {
            if (bokList.get(i).getId() == id) {
                bokList.set(i, bok);
                break;
            }
        }
        logger.info(bok + " updated");
        return bok;
    }

    @DeleteMapping("/deleteBok/{id}")
    public Bok deleteBok(@PathVariable("id") int id) {
        for (int i = 0; i < bokList.size(); i++) {
            if (bokList.get(i).getId() == id) {
                logger.info(bokList.get(i) + " is deleted");
                return bokList.remove(i);
            }
        }
        logger.warn("no books found upon deletion");
        return null;
    }

    @DeleteMapping("/deleteForfatter/{id}")
    public Forfatter deleteForfatter(@PathVariable("id") int id) {
        for (int i = 0; i < bokList.size(); i++) {
            for (int j = 0; j < bokList.get(i).getForfattere().size(); j++) {
                if (bokList.get(i).getForfattere().get(j).getId() == id) {
                    bokList.get(i).getForfattere().remove(j);
                    j--;
                }
            }
            if (bokList.get(i).getForfattere().size() == 0) {
                bokList.remove(i); // remove book if there is no author left
                i--;
            }
        }
        //remove author from list
        for (int i = 0; i < forfatterList.size(); i++) {
            if (forfatterList.get(i).getId() == id) {
                logger.info(forfatterList.get(i) + " is deleted");
                return forfatterList.remove(i);
            }
        }
        logger.warn("no forfatter found upon deletion");
        return null;
    }

    @DeleteMapping("/deleteAdresse/{id}")
    public Adresse deleteAdresse(@PathVariable("id") int id) {
        //delete forfatter if address is removed, forfatter cannot exist without address
        for (int i = 0; i < forfatterList.size(); i++) {
            if (forfatterList.get(i).getAdresse().getId() == id) {
                deleteForfatter(forfatterList.get(i).getId());
                i--;
            }
        }
        for (int i = 0; i < adresseList.size(); i++) {
            if (adresseList.get(i).getId() == id) {
                logger.info(adresseList.get(i) + " is deleted along with its forfatters");
                return adresseList.remove(i);
            }
        }
        logger.warn("no adresse found upon deletion");
        return null;
    }

    @GetMapping("/getForfattere/{lastname}")
    public List<Forfatter> getForfattere(@PathVariable("lastname") String lastname) {
        logger.info("A search for forfatters on lastname:{" + lastname + "}is being processed");
        return forfatterList.stream()
                .filter(x -> x.getEtternavn().equalsIgnoreCase(lastname))
                .collect(Collectors.toList());
    }

    @GetMapping("/getBoker/{tittel}")
    public Bok getBokerOnTittel(@PathVariable("tittel") String tittel) {
        logger.info("A search for bok on tittel:{" + tittel + "}is being processed");
        for (int i = 0; i < bokList.size(); i++) {
            if (bokList.get(i).getTittel().equalsIgnoreCase(tittel)) {
                return bokList.get(i);
            }
        }
        return null;
    }

    @GetMapping("/getBoker/forfatter") //?forfatter=SomeName
    public List<Bok> getBokerOnForfatter(@RequestParam("forfatter") String lastname) {
        logger.info("A search for bok on forfatter:{" + lastname + "}is being processed");
        List<Bok> res = new ArrayList<>();
        for (Bok b : bokList
        ) {
            for (Forfatter f : b.getForfattere()
            ) {
                if (f.getEtternavn().equalsIgnoreCase(lastname)) {
                    res.add(b);
                }
            }
        }
        return res;
    }

    private void addForfatters(Bok bok, List<Integer> forfatterIds) {
        for (int id : forfatterIds
        ) {
            Forfatter f = findForfatter(id);
            if (f != null) {
                bok.addForfatter(f);
            } else {
                logger.warn("Forfatter: " + id + " var ikke funnet for bok: " + bok.id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping("/getForfattere")
    public List<Forfatter> getForfattere() {
        logger.info("/getForfattere");
        return forfatterList;
    }

    @GetMapping("/getAdresser")
    public List<Adresse> getAdresser() {
        logger.info("/getAdresser");
        return adresseList;
    }

    @GetMapping("/getBoker")
    public List<Bok> getBoker() {
        logger.info("/getBoker");
        return bokList;
    }

    /**
     * Find forfatter on id
     *
     * @param id
     * @return forfatter, and null if not found.
     */
    private Forfatter findForfatter(int id) {
        List<Forfatter> list = forfatterList.stream().filter(x -> x.id == id).collect(Collectors.toList());
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    /**
     * Find adresse on id
     *
     * @param id
     * @return adresse, and null if not found.
     */
    private Adresse findAdresse(int id) {
        List<Adresse> list = adresseList.stream().filter(x -> x.id == id).collect(Collectors.toList());
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    /**
     * Find bok on id
     *
     * @param id
     * @return bok, and null if not found.
     */
    private Bok findBok(int id) {
        List<Bok> list = bokList.stream().filter(x -> x.id == id).collect(Collectors.toList());
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

}
