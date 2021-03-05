package com.simplerest.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplerest.server.models.Adresse;
import com.simplerest.server.models.Forfatter;
import com.simplerest.server.services.AdresseService;
import com.simplerest.server.services.BokService;
import com.simplerest.server.services.ForfatterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@WebMvcTest(RestControllerDao.class)
class ServerApplicationTests {

    String endpoint = "http://localhost:8080/dao/";
    @MockBean
    AdresseService adresseService;
    @MockBean
    ForfatterService forfatterService;
    @MockBean
    BokService bokService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_created_forfatter() throws Exception {
        Forfatter forfatter = new Forfatter(2000,
                "fnavn",
                "enavn",
                new Adresse(),
                new ArrayList<>());
        when(forfatterService.save(forfatter))
                .thenReturn(forfatter);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(endpoint+"forfatter")
                .content(asJsonString(forfatter))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void should_get_all_forfatter() throws Exception {
        Forfatter forfatter = new Forfatter(2000,
                "fnavn",
                "enavn",
                new Adresse(),
                new ArrayList<>());
        when(forfatterService.getAll())
                .thenReturn(Stream.of(forfatter, new Forfatter()).collect(Collectors.toList()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(endpoint+"forfatter")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateForfatter() throws Exception {
        Forfatter forfatter = new Forfatter(2000,
                "updatedFnavn",
                "updatedEnavn",
                new Adresse(),
                new ArrayList<>());
        forfatter.setId(2);
        when(forfatterService.update(forfatter))
                .thenReturn(forfatter);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(endpoint+"forfatter")
                .content(asJsonString(forfatter))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
