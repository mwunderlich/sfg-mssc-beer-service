package guru.springframework.sfgmsscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.brewery.events.BeerDto;
import guru.sfg.brewery.model.BeerStyleEnum;
import guru.springframework.sfgmsscbeerservice.exceptions.NotFoundException;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import guru.springframework.sfgmsscbeerservice.services.BeerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static guru.springframework.sfgmsscbeerservice.bootstrap.BeerLoader.BEER_UPC_1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "guru.springframework.sfgmsscbeerservice.web.mappers")
class BeerControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        final ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

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
                                fields.withPath("id").description("Id of beer"),
                                fields.withPath("version").description("Version of the beer."),
                                fields.withPath("createdDate").description("Creation of the entry"),
                                fields.withPath("lastModifiedDate").description("Last modification"),
                                fields.withPath("beerName").description("Name of the beer."),
                                fields.withPath("beerStyle").description("Style of the beer."),
                                fields.withPath("upc").description("UPC of the beer."),
                                fields.withPath("price").description("Price of the beer."),
                                fields.withPath("quantityOnHand").description("Quantity on hand.")
                        )
                ));
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        final ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockmvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of the beer."),
                                fields.withPath("beerStyle").description("Style of the beer."),
                                fields.withPath("upc").description("UPC of the beer."),
                                fields.withPath("price").description("Price of the beer."),
                                fields.withPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updateBeer() throws NotFoundException {
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());
    }

    private BeerDto getValidBeerDto() {
        BeerDto validBeer = BeerDto.builder()
                .beerName("Beer1")
                .beerStyle(BeerStyleEnum.LAGER)
                .price(new BigDecimal("12.99"))
                .quantityOnHand(4)
                .upc(BEER_UPC_1)
                .build();

        return validBeer;
    }


    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(final Class<?> input) {
            constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(final String path) {
            return fieldWithPath(path)
                    .attributes(
                            key("constraints")
                                    .value(
                                            StringUtils.collectionToDelimitedString(
                                                    constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}