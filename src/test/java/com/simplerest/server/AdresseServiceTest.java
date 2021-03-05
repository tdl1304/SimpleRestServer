package com.simplerest.server;

import com.simplerest.server.dao.AdresseDao;
import com.simplerest.server.model.Adresse;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.simplerest.server.services.AdresseService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdresseServiceTest {

    @Autowired
    AdresseService adresseService;

    @MockBean
    AdresseDao adresseDao;

    String gateadresse = "testgate";
    int hus_nr = 101;
    int post_nr = 2044;
    String land = "testland";
    String by = "testby";

    @Test
    public void getAll() {
        when(adresseDao.loadAll())
                .thenReturn(Stream.of(new Adresse(gateadresse, hus_nr, post_nr, land, by), new Adresse(gateadresse,hus_nr,post_nr,land,by))
                        .collect(Collectors.toList()));
        List<Adresse> adresseList = adresseService.getAll();
        assert adresseList.size() == 2 : "Adresses not found";
    }

    @Test
    public void findById() {
        int id = 0;
        when(adresseDao.load(id))
                .thenReturn(new Adresse(gateadresse, hus_nr, post_nr, land, by));
        Adresse found = adresseService.findById(id);
        assert found.getId() == id : "Not found";

    }

    @Test
    public void save() {
        Adresse adresse = new Adresse(gateadresse, hus_nr, post_nr, land, by);
        when(adresseDao.save(adresse))
                .thenReturn(adresse);
        Adresse saved = adresseService.save(adresse);
        assert saved.getId() == adresse.getId() : "Unable to save to db";
    }

    @Test
    public void deleteById() {
        int id = 0;
        when(adresseDao.delete(id))
                .thenReturn(new Adresse(gateadresse, hus_nr, post_nr, land, by));
        Adresse deleted = adresseService.deleteById(id);
        assert deleted.getId() == id : "Unable to delete";

    }

    @Test
    public void update() {
        Adresse adresse = new Adresse(gateadresse, hus_nr, post_nr, land, by);
        when(adresseDao.update(adresse))
                .thenReturn(adresse);
        Adresse updated = adresseService.update(adresse);
        assert updated.equals(adresse) : "Unable to update";
    }
}
