package com.example.petstore.controllers;

import com.example.petstore.PetStoreApplication;
import com.example.petstore.entities.Sex;
import com.example.petstore.models.directorModels.DirectorCreateModel;
import com.example.petstore.models.directorModels.DirectorUpdateModel;
import com.example.petstore.services.DirectorService;;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PetStoreApplication.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ DirectorController.class })
@AutoConfigureMockMvc
class DirectorControllerTest {
    @MockBean
    private DirectorService directorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkIfAccessible() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/director/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void checkReturningPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/director/getAll"))
                .andExpect(MockMvcResultMatchers.view().name("DirectorPages/DirectorViewPage"));
    }

    @Test
    void checkBadSaving() throws Exception {
        when(directorService.isUnique(anyString())).thenReturn("");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/director/create")
                .flashAttr("directorCreateModel", new DirectorCreateModel("a", Sex.Male, 20000)))
                .andExpect(MockMvcResultMatchers.view().name("DirectorPages/DirectorCreatePage"));
    }

    @Test
    void checkSaving() throws Exception {
        when(directorService.isUnique(anyString())).thenReturn("");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/director/create")
                        .flashAttr("directorCreateModel", new DirectorCreateModel("A A", Sex.Male, 20000)))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/director/getAll"));
    }

    @Test
    void checkBadUpdating() throws Exception {
        when(directorService.isUnique2(anyString(), any(UUID.class))).thenReturn("");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/director/update")
                        .flashAttr("director", new DirectorUpdateModel("a", Sex.Male, 20000, UUID.randomUUID())))
                .andExpect(MockMvcResultMatchers.view().name("DirectorPages/DirectorUpdatePage"));
    }

    @Test
    void checkUpdating() throws Exception {
        Mockito.when(directorService.isUnique2(anyString(), any(UUID.class))).thenReturn("");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/director/update")
                        .flashAttr("director", new DirectorUpdateModel("A A", Sex.Male, 20000, UUID.randomUUID())))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/director/getAll"));
    }

    @Test
    void checkDuplicatedSaving() throws Exception {
        when(directorService.isUnique("A A")).thenReturn("You can not use the same name twice");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/director/create")
                        .flashAttr("directorCreateModel", new DirectorCreateModel("A A", Sex.Male, 20000)))
                .andExpect(MockMvcResultMatchers.view().name("DirectorPages/DirectorCreatePage"));
    }

}