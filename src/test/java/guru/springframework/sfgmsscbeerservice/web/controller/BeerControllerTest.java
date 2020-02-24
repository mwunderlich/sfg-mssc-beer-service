package guru.springframework.sfgmsscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import guru.springframework.sfgmsscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "guru.springframework.sfgmsscbeerservice.web.mappers")
class BeerControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @Test
    void getBeerById() throws Exception {
        mockmvc.perform(get("/api/v1/beer/{beerId}" + UUID.randomUUID().toString())
                .param("iscold", "yes")     // request parameter
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer",
                        pathParameters(
                            parameterWithName("beerId").description("UUID of beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("beer is cold")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of beer"),
                                fieldWithPath("version").description("Version of the beer."),
                                fieldWithPath("createdDate").description("Creation of the entry"),
                                fieldWithPath("lastModifiedDate").description("Last modification"),
                                fieldWithPath("beerName").description("Name of the beer."),
                                fieldWithPath("beerStyle").description("Style of the beer."),
                                fieldWithPath("upc").description("UPC of the beer."),
                                fieldWithPath("price").description("Price of the beer."),
                                fieldWithPath("quantityOnHand").description("Quantity on hand.")
                        )
                ));
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockmvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer",
                        requestFields(
                                fieldWithPath("id").ignored(),
                                fieldWithPath("version").ignored(),
                                fieldWithPath("createdDate").ignored(),
                                fieldWithPath("lastModifiedDate").ignored(),
                                fieldWithPath("beerName").description("Name of the beer."),
                                fieldWithPath("beerStyle").description("Style of the beer."),
                                fieldWithPath("upc").description("UPC of the beer."),
                                fieldWithPath("price").description("Price of the beer."),
                                fieldWithPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updateBeer() {
    }

    private BeerDto getValidBeerDto() {
        BeerDto validBeer = BeerDto.builder()
                .beerName("Beer1")
                .beerStyleEnum(BeerStyleEnum.STOUT)
                .price(new BigDecimal("12.99"))
                .quantityOnHand(4)
                .upc(123456l)
                .build();

        return validBeer;
    }
}