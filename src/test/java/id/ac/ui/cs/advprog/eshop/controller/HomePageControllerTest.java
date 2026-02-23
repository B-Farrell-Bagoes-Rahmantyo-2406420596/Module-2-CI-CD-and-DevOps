package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class HomePageControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HomePageController homePageController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(homePageController).build();
    }

    @Test
    void testHomePageReturnsCorrectViewName() {
        String viewName = homePageController.home(model);
        assertEquals("homePage", viewName);
    }

    @Test
    void testHomePageAddsPageTitleAttribute() {
        homePageController.home(model);
        verify(model, times(1)).addAttribute("pageTitle", "ADV Shop");
        verifyNoMoreInteractions(model);
    }

    @Test
    void testHomePageReturnValueIsNotNull() {
        String result = homePageController.home(model);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testHomePageMockMvcCompleteFunctionality() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homePage"))
                .andExpect(model().attributeExists("pageTitle"))
                .andExpect(model().attribute("pageTitle", "ADV Shop"));
    }
}